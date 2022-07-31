import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.ArrayList;

public class DataCollectionCreator { //Реализует сборку и генерацию коллекции данных JasperReports
    private ArrayList<DataBean> dataBeanList = new ArrayList<DataBean>();

    public void add(DataBean obj)
    {
        dataBeanList.add(obj);
    }

    public void clear()
    {
        dataBeanList.clear();
    }

    public JRBeanCollectionDataSource getCollection()
    {
        return new JRBeanCollectionDataSource(dataBeanList);
    }
}
