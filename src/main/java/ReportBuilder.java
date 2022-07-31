import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.export.*;

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
        Driver driver;

        try {
/////////////////////////////////////////////////////////////////SQL CONNECT CODE/////////////////////////////////////////////////////////////////////////////////////////////////
            DataBase = DriverManager.getConnection("jdbc:postgresql://26.33.112.138:5432/postgres", "postgres", "");
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
                    "INNER JOIN \"main\".\"Equipment\" ON \"main\".\"Equipment\".\"ID\" = \"main\".\"EmployeeEquipment\".\"EquipmentID\"");

            int RowCount = GetRowCount(res);

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
            Parametrs.put("SubReport", SubReport);// Параметр отвечающий за передачу скомпилированной подтаблицы в основную таблицу

            JasperPrint Print = JasperFillManager.fillReport(Report, Parametrs, Collection.getCollection()); //Заполняем таблицу параметрами и данными из коллекции

/////////////////////////////////////////////////////////////////EXPORT CODE//////////////////////////////////////////////////////////////////////////////////////////////////////////
            JasperExportManager.exportReportToPdfFile(Print, "Report.pdf"); //Экспортируем таблицу в PDF форма

            JRRtfExporter RtfExporter = new JRRtfExporter(); //Объект экспортера в RTF формат
            RtfExporter.setExporterInput(new SimpleExporterInput(Print)); //Передаем входные данные Print(заполненный отчет) экспортеру, в обертке SimpleExporterInput
            RtfExporter.setExporterOutput(new SimpleWriterExporterOutput("Report.rtf")); //Передаем выходные данные экспортеру, в обертке SimpleWriterExporterOutput
            RtfExporter.exportReport(); //Экспортируем таблицу в RTF формат

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


    }
}
