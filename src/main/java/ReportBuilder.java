import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.export.SimpleOdsExporterConfiguration;
import net.sf.jasperreports.export.SimpleOdsReportConfiguration;
import net.sf.jasperreports.export.SimpleXlsExporterConfiguration;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class ReportBuilder {

    private static int GetRowCount(ResultSet rs) throws SQLException {
        if(!rs.next()) System.out.println("В ResultSet нет данных");
        rs.beforeFirst();
        int count = 0;
        while(rs.next())
        {
            count++;
        }
        rs.beforeFirst();
        return count;
    }

    public static void main(String[] args) {
        Connection DataBase;

        try {
/////////////////////////////////////////////////////////////////SQL CONNECT CODE/////////////////////////////////////////////////////////////////////////////////////////////////
            DataBase = DriverManager.getConnection("jdbc:postgresql://26.139.88.129:5432/postgres", "postgres", "");
            System.out.println("База данных подключена");
            Statement sql = DataBase.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

/////////////////////////////////////////////////////////////////SQL QUERY CODE///////////////////////////////////////////////////////////////////////////////////////////////////

            ResultSet res = sql.executeQuery("SELECT \"main\".\"Worker\".\"UserName\", " +
                    "\"main\".\"EmployeeEquipment\".\"WorkerID\", " +
                    "\"main\".\"Equipment\".\"EquipmentName\", " +
                    "\"main\".\"EmployeeEquipment\".\"EquipmentID\", " +
                    "\"main\".\"EmployeeEquipment\".\"WorkTime\"" +
                    "FROM \"main\".\"Worker\" " +
                    "INNER JOIN \"main\".\"EmployeeEquipment\" ON \"main\".\"Worker\".\"ID\"= \"main\".\"EmployeeEquipment\".\"WorkerID\" " +
                    "INNER JOIN \"main\".\"Equipment\" ON \"main\".\"Equipment\".\"ID\" = \"main\".\"EmployeeEquipment\".\"EquipmentID\""); //Запрос к базе данных

            int RowCount = GetRowCount(res); // Количество строк полученные от базы данных

/////////////////////////////////////////////////////////////////DATA PREPARE CODE/////////////////////////////////////////////////////////////////////////////////////////////////

            DataCollectionCreator Collection = new DataCollectionCreator(); //Объект для сборки данных в таблицу
            for(int i = 0; i  < RowCount; i++)
            {
                res.next();
                Integer[] WorkTime = (Integer[]) res.getArray("WorkTime").getArray();//WorkTime поле типа int[] в таблице EmployeeEquipment. Перевожу из sql Array в Integer Array
                Collection.add(new DataBeanWorkerEquipments(res.getString("UserName") + " [ID: " + res.getInt("WorkerID") + "]", //Собираем коллекции для каждой строки с учетом подколлекции для подтаблицы
                        res.getString("EquipmentName") + " [ID: " + res.getInt("EquipmentID") + "]",
                        new DataBeanWorkTime(WorkTime[0].toString()+" ч", WorkTime[1].toString()+" ч", WorkTime[2].toString()+" ч", WorkTime[3].toString()+" ч", WorkTime[4].toString()+" ч")));
            }

/////////////////////////////////////////////////////////////////REPORT COMPILER CODE////////////////////////////////////////////////////////////////////////////////////////////////

            JasperReport Report = JasperCompileManager.compileReport("src/main/resources/jrxml/WorkerEquipments.jrxml"); //Компилируем шаблон таблицы с оборудованием и работниками
            JasperReport SubReport = JasperCompileManager.compileReport("src/main/resources/jrxml/TimeWork.jrxml"); //Компилируем шаблон подтаблицы со временем работы

/////////////////////////////////////////////////////////////////DATA FILL CODE///////////////////////////////////////////////////////////////////////////////////////////////////////

            HashMap<String, Object> Parametrs = new HashMap<String, Object>();//Мапа с параметрами для таблицы
            SimpleDateFormat DateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//Паттерн для форматирования даты
            Parametrs.put("Date", DateFormater.format(new Date()));// Добавление параметра отвечающего за дату в левом нижнем углу страницы
            Parametrs.put("SubReport", SubReport);// Параметр отвечающий за передачу скомпилированного объекта подтаблицы в основную таблицу

            JasperPrint Print = JasperFillManager.fillReport(Report, Parametrs, Collection.getCollection()); //Заполняем таблицу параметрами и данными из коллекции

/////////////////////////////////////////////////////////////////EXPORT CODE//////////////////////////////////////////////////////////////////////////////////////////////////////////
            ReportExportManager ExportManager = new ReportExportManager(); // Объект менеджера экспорта таблиц в различные форматы файлов

            ExportManager.ExportToPdf("Отчет.pdf", Print); // Экспорт в PDF формат
            
            ExportManager.ExportToRtf("Отчет.rtf", Print); // Экспорт в RTF формат

            SimpleXlsReportConfiguration XlsConfig = new SimpleXlsReportConfiguration(); // Объект конфигурации XLS отчета
            XlsConfig.setWhitePageBackground(false); // Отключаем белый фон для клеток, оставляя их прозрачными
            XlsConfig.setOnePagePerSheet(true); // Для каждой новой таблицы создаем новую страницу в XLS документе
            ExportManager.ExportToXls("Отчет.xls", Print, new SimpleXlsExporterConfiguration(), XlsConfig); // Экспорт в XLS формат c передачей объектов для конфигурирования XLS документа

            ExportManager.ExportToOdt("Отчет.odt", Print); // Экспорт в ODT формат(LibreOffice)

            SimpleOdsReportConfiguration OdsConfig = new SimpleOdsReportConfiguration(); // Объект конфигурации ODS отчета
            OdsConfig.setWhitePageBackground(false); // Отключаем белый фон для клеток, оставляя их прозрачными
            OdsConfig.setOnePagePerSheet(true); // Для каждой новой таблицы создаем новую страницу в ODS документе

            ExportManager.ExportToOds("Отчет.ods", Print, new SimpleOdsExporterConfiguration(), OdsConfig); // Экспорт в ODS формат(LibreOffice) c передачей объектов для конфигурирования ODS документа
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        }
        catch (SQLException e)
        {
            System.out.println("SQL Error " + e);
        }
        catch (JRException e)
        {
            System.out.println("JasperReport Error " + e);
        }

        System.out.println("Формирование отчетов завершено");
    }
}
