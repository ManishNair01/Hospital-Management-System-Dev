///Stores the date.
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
        ///@returns Returns the day.
        return this.day;
    }

    public String getMonth() {
        ///@returns Returns the month.
        return this.month;
    }

    public String getYear() {
        ///@returns Returns the year.
        return this.year;
    }

    public String getDDMMYYYY() {
        ///@returns Returns the date.
        return (this.day + this.month + this.year);
    }
}
