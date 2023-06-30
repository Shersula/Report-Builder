import java.io.File;
import java.io.IOException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.export.*;

public class ReportExportManager
{
    /////////////////////////////////////////////////////UNIVERSAL FUNCTIONS/////////////////////////////////////////////////////
    File InitFile(String FileName) //Реализует создание файлов, делает перезапись в случае если файл уже существует, возвращает объект файла при успешном выполнении и null в случае исключения
    {
        File ReportFile = new File(FileName); //Создаем объект файла

        if(ReportFile.exists() == true)
        {
            ReportFile.delete(); //Удаляем файл если он уже существует
            try
            {
                ReportFile.createNewFile(); //Создаем новый файл после удаления старой версии
            }
            catch (IOException e)
            {
                e.printStackTrace();
                return null; //Возвращем null в случае исключения
            }
        }
        return ReportFile; //Возвращем объект созданного файла
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////PDF EXPORT/////////////////////////////////////////////////////////
    //Описание будет только для первых двух функций, поскольку все остальные фарматы аналогичны за исключением различий в классах которые реализуют экспорт
    void ExportToPdf(String FileName, JasperPrint Report) //Упрощенный вариант экспорта, принимает имя выходного файла который будет создан и сформированный отчет для запонления файла
    {
        File ReportFile = InitFile(FileName); //Создает или пересоздает файл в случае если он уже создан
        if(ReportFile == null) //Проверяем, что файл был успешно создан
        {
            System.out.println("Ошибка создания PDF файла");
            return;
        }

        JRPdfExporter Exporter = new JRPdfExporter(); //Создаем объект экспортер PDF формата
        Exporter.setExporterInput(new SimpleExporterInput(Report)); //Устанавливаем входной параметр для экспортера, в данном случае заполненный отчет
        Exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(ReportFile)); //Устанавливаем выходной параметр для экспортера, в данном случае это ранее созданный файл
        
        try
        {
            Exporter.exportReport(); //Производим экспорт отчета в файл
            ReportFile.setReadOnly(); //Устанавливаем файлу атрибут "Только для чтения"
        }
        catch (JRException e)
        {
            e.printStackTrace();
        }
    }
    
    void ExportToPdf(String FileName, JasperPrint Report, SimplePdfExporterConfiguration ExportConfig, SimplePdfReportConfiguration ReportConfig) //Расиширенный вариант экспорта, принимает имя выходного файла который будет создан, сформированный отчет для запонления файла, объект конфигурации для экспортера и объект конфигурации для конечного отчета
    {
        File ReportFile = InitFile(FileName); //Создает или пересоздает файл в случае если он уже создан
        if(ReportFile == null) //Проверяем, что файл был успешно создан
        {
            System.out.println("Ошибка создания PDF файла");
            return;
        }

        JRPdfExporter Exporter = new JRPdfExporter(); //Создаем объект экспортер PDF формата
        Exporter.setConfiguration(ExportConfig); // Привязываем объект конфигурирующий экспорт отчета в файл
        Exporter.setConfiguration(ReportConfig); // Привязываем объект конфигурирующий сам экспортированный отчет
        Exporter.setExporterInput(new SimpleExporterInput(Report)); //Устанавливаем входной параметр для экспортера, в данном случае заполненный отчет
        Exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(ReportFile)); //Устанавливаем выходной параметр для экспортера, в данном случае это ранее созданный файл
        
        try
        {
            Exporter.exportReport(); //Производим экспорт отчета в файл
            ReportFile.setReadOnly(); //Устанавливаем файлу атрибут "Только для чтения"
        }
        catch (JRException e)
        {
            e.printStackTrace();
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////XLS EXPORT/////////////////////////////////////////////////////////
    void ExportToXls(String FileName, JasperPrint Report)
    {
        File ReportFile = InitFile(FileName);
        if(ReportFile == null)
        {
            System.out.println("Ошибка создания XLS файла");
            return;
        }

        JRXlsExporter Exporter = new JRXlsExporter();
        Exporter.setExporterInput(new SimpleExporterInput(Report));
        Exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(ReportFile));
        
        try
        {
            Exporter.exportReport();
            ReportFile.setReadOnly();
        }
        catch (JRException e)
        {
            e.printStackTrace();
        }
    }

    void ExportToXls(String FileName, JasperPrint Report, SimpleXlsExporterConfiguration ExportConfig, SimpleXlsReportConfiguration ReportConfig)
    {
        File ReportFile = InitFile(FileName);
        if(ReportFile == null)
        {
            System.out.println("Ошибка создания XLS файла");
            return;
        }

        JRXlsExporter Exporter = new JRXlsExporter();
        Exporter.setConfiguration(ExportConfig);
        Exporter.setConfiguration(ReportConfig);
        Exporter.setExporterInput(new SimpleExporterInput(Report));
        Exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(ReportFile));
        
        try
        {
            Exporter.exportReport();
            ReportFile.setReadOnly();
        }
        catch (JRException e)
        {
            e.printStackTrace();
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////ODT EXPORT/////////////////////////////////////////////////////////
    void ExportToOdt(String FileName, JasperPrint Report)
    {
        File ReportFile = InitFile(FileName);
        if(ReportFile == null)
        {
            System.out.println("Ошибка создания ODT файла");
            return;
        }

        JROdtExporter Exporter = new JROdtExporter();
        Exporter.setExporterInput(new SimpleExporterInput(Report));
        Exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(ReportFile));
        
        try
        {
            Exporter.exportReport();
            ReportFile.setReadOnly();
        }
        catch (JRException e)
        {
            e.printStackTrace();
        }
    }

    void ExportToOdt(String FileName, JasperPrint Report, SimpleOdtExporterConfiguration ExportConfig, SimpleOdtReportConfiguration ReportConfig)
    {
        File ReportFile = InitFile(FileName);
        if(ReportFile == null)
        {
            System.out.println("Ошибка создания ODT файла");
            return;
        }

        JROdtExporter Exporter = new JROdtExporter();
        Exporter.setConfiguration(ExportConfig);
        Exporter.setConfiguration(ReportConfig);
        Exporter.setExporterInput(new SimpleExporterInput(Report));
        Exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(ReportFile));
        
        try
        {
            Exporter.exportReport();
            ReportFile.setReadOnly();
        }
        catch (JRException e)
        {
            e.printStackTrace();
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////ODS EXPORT/////////////////////////////////////////////////////////
    void ExportToOds(String FileName, JasperPrint Report)
    {
        File ReportFile = InitFile(FileName);
        if(ReportFile == null)
        {
            System.out.println("Ошибка создания ODS файла");
            return;
        }

        JROdsExporter Exporter = new JROdsExporter();
        Exporter.setExporterInput(new SimpleExporterInput(Report));
        Exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(ReportFile));
        
        try
        {
            Exporter.exportReport();
            ReportFile.setReadOnly();
        }
        catch (JRException e)
        {
            e.printStackTrace();
        }
    }

    void ExportToOds(String FileName, JasperPrint Report, SimpleOdsExporterConfiguration ExportConfig, SimpleOdsReportConfiguration ReportConfig)
    {
        File ReportFile = InitFile(FileName);
        if(ReportFile == null)
        {
            System.out.println("Ошибка создания ODS файла");
            return;
        }

        JROdsExporter Exporter = new JROdsExporter();
        Exporter.setConfiguration(ExportConfig);
        Exporter.setConfiguration(ReportConfig);
        Exporter.setExporterInput(new SimpleExporterInput(Report));
        Exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(ReportFile));
        
        try
        {
            Exporter.exportReport();
            ReportFile.setReadOnly();
        }
        catch (JRException e)
        {
            e.printStackTrace();
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////RTF EXPORT/////////////////////////////////////////////////////////
    void ExportToRtf(String FileName, JasperPrint Report)
    {
        File ReportFile = InitFile(FileName);
        if(ReportFile == null)
        {
            System.out.println("Ошибка создания RTF файла");
            return;
        }

        JRRtfExporter Exporter = new JRRtfExporter();
        Exporter.setExporterInput(new SimpleExporterInput(Report));
        Exporter.setExporterOutput(new SimpleWriterExporterOutput(ReportFile)); //Экспортер RTF требует передачи выходного параметра в виде объекта SimpleWriterExporterOutput вместо SimpleOutputStreamExporterOutput, в остальном реализация такая же как и всех прочих функций
        
        try
        {
            Exporter.exportReport();
            ReportFile.setReadOnly();
        }
        catch (JRException e)
        {
            e.printStackTrace();
        }
    }

    void ExportToRtf(String FileName, JasperPrint Report, SimpleRtfExporterConfiguration ExportConfig, SimpleRtfReportConfiguration ReportConfig)
    {
        File ReportFile = InitFile(FileName);
        if(ReportFile == null)
        {
            System.out.println("Ошибка создания RTF файла");
            return;
        }

        JRRtfExporter Exporter = new JRRtfExporter();
        Exporter.setConfiguration(ExportConfig);
        Exporter.setConfiguration(ReportConfig);
        Exporter.setExporterInput(new SimpleExporterInput(Report));
        Exporter.setExporterOutput(new SimpleWriterExporterOutput(ReportFile)); //Экспортер RTF требует передачи выходного параметра в виде объекта SimpleWriterExporterOutput вместо SimpleOutputStreamExporterOutput, в остальном реализация такая же как и всех прочих функций
        
        try
        {
            Exporter.exportReport();
            ReportFile.setReadOnly();
        }
        catch (JRException e)
        {
            e.printStackTrace();
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
