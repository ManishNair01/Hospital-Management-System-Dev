public class Date {
    private String day;
    private String month;
    private String year;

    Date(String day, String month, String year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public String getDay() {
        return this.day;
    }

    public String getMonth() {
        return this.month;
    }

    public String getYear() {
        return this.year;
    }

    public String getDDMMYYYY() {
        return (this.day + this.month + this.year);
    }
}
