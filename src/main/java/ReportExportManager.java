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
    File InitFile(String FileName) //��������� �������� ������, ������ ���������� � ������ ���� ���� ��� ����������, ���������� ������ ����� ��� �������� ���������� � null � ������ ����������
    {
        File ReportFile = new File(FileName); //������� ������ �����

        if(ReportFile.exists() == true)
        {
            ReportFile.delete(); //������� ���� ���� �� ��� ����������
            try
            {
                ReportFile.createNewFile(); //������� ����� ���� ����� �������� ������ ������
            }
            catch (IOException e)
            {
                e.printStackTrace();
                return null; //��������� null � ������ ����������
            }
        }
        return ReportFile; //��������� ������ ���������� �����
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////PDF EXPORT/////////////////////////////////////////////////////////
    //�������� ����� ������ ��� ������ ���� �������, ��������� ��� ��������� ������� ���������� �� ����������� �������� � ������� ������� ��������� �������
    void ExportToPdf(String FileName, JasperPrint Report) //���������� ������� ��������, ��������� ��� ��������� ����� ������� ����� ������ � �������������� ����� ��� ���������� �����
    {
        File ReportFile = InitFile(FileName); //������� ��� ����������� ���� � ������ ���� �� ��� ������
        if(ReportFile == null) //���������, ��� ���� ��� ������� ������
        {
            System.out.println("������ �������� PDF �����");
            return;
        }

        JRPdfExporter Exporter = new JRPdfExporter(); //������� ������ ��������� PDF �������
        Exporter.setExporterInput(new SimpleExporterInput(Report)); //������������� ������� �������� ��� ����������, � ������ ������ ����������� �����
        Exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(ReportFile)); //������������� �������� �������� ��� ����������, � ������ ������ ��� ����� ��������� ����
        
        try
        {
            Exporter.exportReport(); //���������� ������� ������ � ����
            ReportFile.setReadOnly(); //������������� ����� ������� "������ ��� ������"
        }
        catch (JRException e)
        {
            e.printStackTrace();
        }
    }
    
    void ExportToPdf(String FileName, JasperPrint Report, SimplePdfExporterConfiguration ExportConfig, SimplePdfReportConfiguration ReportConfig) //������������ ������� ��������, ��������� ��� ��������� ����� ������� ����� ������, �������������� ����� ��� ���������� �����, ������ ������������ ��� ���������� � ������ ������������ ��� ��������� ������
    {
        File ReportFile = InitFile(FileName); //������� ��� ����������� ���� � ������ ���� �� ��� ������
        if(ReportFile == null) //���������, ��� ���� ��� ������� ������
        {
            System.out.println("������ �������� PDF �����");
            return;
        }

        JRPdfExporter Exporter = new JRPdfExporter(); //������� ������ ��������� PDF �������
        Exporter.setConfiguration(ExportConfig); // ����������� ������ ��������������� ������� ������ � ����
        Exporter.setConfiguration(ReportConfig); // ����������� ������ ��������������� ��� ���������������� �����
        Exporter.setExporterInput(new SimpleExporterInput(Report)); //������������� ������� �������� ��� ����������, � ������ ������ ����������� �����
        Exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(ReportFile)); //������������� �������� �������� ��� ����������, � ������ ������ ��� ����� ��������� ����
        
        try
        {
            Exporter.exportReport(); //���������� ������� ������ � ����
            ReportFile.setReadOnly(); //������������� ����� ������� "������ ��� ������"
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
            System.out.println("������ �������� XLS �����");
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
            System.out.println("������ �������� XLS �����");
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
            System.out.println("������ �������� ODT �����");
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
            System.out.println("������ �������� ODT �����");
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
            System.out.println("������ �������� ODS �����");
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
            System.out.println("������ �������� ODS �����");
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
            System.out.println("������ �������� RTF �����");
            return;
        }

        JRRtfExporter Exporter = new JRRtfExporter();
        Exporter.setExporterInput(new SimpleExporterInput(Report));
        Exporter.setExporterOutput(new SimpleWriterExporterOutput(ReportFile)); //��������� RTF ������� �������� ��������� ��������� � ���� ������� SimpleWriterExporterOutput ������ SimpleOutputStreamExporterOutput, � ��������� ���������� ����� �� ��� � ���� ������ �������
        
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
            System.out.println("������ �������� RTF �����");
            return;
        }

        JRRtfExporter Exporter = new JRRtfExporter();
        Exporter.setConfiguration(ExportConfig);
        Exporter.setConfiguration(ReportConfig);
        Exporter.setExporterInput(new SimpleExporterInput(Report));
        Exporter.setExporterOutput(new SimpleWriterExporterOutput(ReportFile)); //��������� RTF ������� �������� ��������� ��������� � ���� ������� SimpleWriterExporterOutput ������ SimpleOutputStreamExporterOutput, � ��������� ���������� ����� �� ��� � ���� ������ �������
        
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
