///Stores patient information.
public class Patient extends User {
    private Appointment _appointment;
    private double _totalExpenditure;
    private Date _dateOfRegistration;
    private long _aadharNumber;
    private String _gender;
    private int _height;
    private int _weight;
    private static int _patientCount = 0;
    private Ward _ward = null;

    Patient() {
        super();
    }

    Patient(String username, String password, long aadharNumber, String gender,
            int height, int weight, Date dateOfRegistration) {
        super(username, password);
        this._aadharNumber = aadharNumber;
        this._gender = gender;
        this._height = height;
        this._weight = weight;
        this._dateOfRegistration = dateOfRegistration;
        _patientCount++;
    }

    public static int get_patientCount() {
        return _patientCount;
    }

    public long get_aadharNumber() {
        return this._aadharNumber;
    }

    public int get_height() {
        return this._height;
    }

    public int get_weight() {
        return this._weight;
    }

    public String get_gender() {
        return this._gender;
    }

    public String getDOR() {
        ///@returns Date of release
        return this._dateOfRegistration.getDDMMYYYY();
    }

    public void setWard(Ward ward) {
        this._ward = ward;
    }

    public Ward getWard() {
        return this._ward;
    }

    public void bookAppointment() {
        this._totalExpenditure += 50;
    }

    public Appointment getAppointment() {
        return this._appointment;
    }

    public double get_totalExpenditure() {
        return this._totalExpenditure;
    }

    @Override
    public void display() {
        System.out.println("Username: " + this.getUsername());
        System.out.println("Password: " + this.getPassword());
        System.out.println("Aadhar Number: " + this.get_aadharNumber());
        System.out.println("Gender: " + this.get_gender());
        System.out.println("Height: " + this.get_height());
        System.out.println("Weight: " + this.get_weight());
        System.out.println("Date of registration: " + this.getDOR());
        System.out.println("Total expenditure: " + this.get_totalExpenditure());
    }

    public void setAppointment(Appointment appointment) {
        this._appointment = appointment;

    }

    public void getAdmitted() {
        this._totalExpenditure += 150;
    }

    public static void decrementPatientCount() {
        Patient._patientCount -= 1;
    }
}