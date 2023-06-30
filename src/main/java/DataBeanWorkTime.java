public class DataBeanWorkTime implements DataBean{ //Реализует DataBean для подтаблицы под коллекцию JasperReports
    private String monday; //Рабочие часы понедельник
    private String tuesday; //Рабочие часы вторник
    private String wednesday; //Рабочие часы среда
    private String thursday; //Рабочие часы четверг
    private String friday; //Рабочие часы пятница

    public DataBeanWorkTime(){}

    public DataBeanWorkTime(String monday, String tuesday, String wednesday, String thursday, String friday) {
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
    }

    public String getMonday() {
        return monday;
    }

    public void setMonday(String monday) {
        this.monday = monday;
    }

    public String getTuesday() {
        return tuesday;
    }

    public void setTuesday(String tuesday) {
        this.tuesday = tuesday;
    }

    public String getWednesday() {
        return wednesday;
    }

    public void setWednesday(String wednesday) {
        this.wednesday = wednesday;
    }

    public String getThursday() {
        return thursday;
    }

    public void setThursday(String thursday) {
        this.thursday = thursday;
    }

    public String getFriday() {
        return friday;
    }

    public void setFriday(String friday) {
        this.friday = friday;
    }

}
