import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class DataBeanWorkerEquipments implements DataBean{ //Реализует DataBean для таблицы под коллекцию JasperReports
    private String worker; //имя сотрудника
    private String equipmentsName; //имя оборудования
    private DataCollectionCreator subReportCollection; //Объект сборки коллекции для подтаблицы

    public DataBeanWorkerEquipments(){

    }

    public DataBeanWorkerEquipments(String worker, String equipmentsName, DataBean subReportData) {
        this.worker = worker;
        this.equipmentsName = equipmentsName;
        subReportCollection = new DataCollectionCreator();
        subReportCollection.add(subReportData);
    }

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public String getEquipmentsName() {
        return equipmentsName;
    }

    public void setEquipmentsName(String equipmentsName) {
        this.equipmentsName = equipmentsName;
    }

    public JRBeanCollectionDataSource getSubReportCollection() {
        return subReportCollection.getCollection();
    }
}
