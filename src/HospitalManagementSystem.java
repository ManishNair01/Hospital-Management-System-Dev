import java.util.Scanner;
import java.util.ArrayList;

public class HospitalManagementSystem {

    private final String _name;
    private ArrayList<Patient> _patientList = new ArrayList<Patient>();
    private ArrayList<Appointment> _appointmentList = new ArrayList<Appointment>();
    private ArrayList<Staff> _staffList = new ArrayList<Staff>();
    private ArrayList<Admin> _adminList = new ArrayList<Admin>();
    private ArrayList<Ward> _wardList = new ArrayList<>();
    private int _accountBalance = 0;

    private final static Scanner _scanner = new Scanner(System.in);

    public HospitalManagementSystem(String name) {
        this._name = name;
        Admin defaultAdmin = new Admin("admin", "admin123");
        _adminList.add(defaultAdmin);
    }

    private Patient _findPatient(String patientUsername, String patientPassword) {
        Patient patient = new Patient();

        for (Patient patientinList : this._patientList) {
            if (patientinList.auth(patientUsername, patientPassword) != "null") {
                return patientinList;
            }
        }

        return patient;
    }

    private User _login(String username, String password) {
        for (Patient patient : this._patientList) {
            if (patient.auth(username, password) != "null") {
                return patient;
            }
        }

        for (Staff staff : this._staffList) {
            if (staff.auth(username, password) != "null") {
                return staff;
            }
        }

        for (Admin admin : this._adminList) {
            if (admin.auth(username, password) != "null") {
                return admin;
            }
        }

        return null;
    }

    public void start() {
        boolean running = true;
        User currentUser = null;
        boolean isLoggedIn = false;
        boolean isInitialCall = true;

        while (running) {
            if (currentUser == null) {
                System.out.println();
                System.out.println("Welcome to " + this._getName() + ". Please login or register to continue.");

                System.out.println("1. Login");
                System.out.println("2. Register");
                System.out.println();
                if (!isInitialCall) {
                    _scanner.nextLine();
                }
                System.out.print("Enter your choice: ");
                String mainChoice = _scanner.nextLine();

                switch (mainChoice) {

                    case ("1"):
                        if (!isInitialCall) {
                            // _scanner.nextLine();
                        }

                        System.out.print("Username: ");
                        String username = _scanner.nextLine();
                        System.out.print("Password: ");
                        String password = _scanner.nextLine();

                        currentUser = _login(username, password);
                        break;
                    case ("2"):
                        System.out.print("Enter username for new patient: ");
                        String newPatientUsername = _scanner.nextLine();
                        System.out.print("Enter password: ");
                        String newPatientPassword = _scanner.nextLine();
                        System.out.print("Enter aadhar number: ");
                        long newPatientAadhar = _scanner.nextLong();
                        _scanner.nextLine();
                        System.out.print("Enter date of registration in DDMMYYYY: ");
                        String newPatientDOR = _scanner.nextLine(); // Date of registration
                        Date newPatientDate = new Date(newPatientDOR.substring(0, 2),
                                newPatientDOR.substring(2, 4),
                                newPatientDOR.substring(4, 8));
                        System.out.print("Enter height in cm: ");
                        int newPatientHeight = _scanner.nextInt();
                        _scanner.nextLine();
                        System.out.print("Enter weight in kg: ");
                        int newPatientWeight = _scanner.nextInt();
                        _scanner.nextLine();
                        System.out.print("Patient sex? [M/F]: ");
                        String newPatientSexChoice = _scanner.nextLine();

                        Patient newPatient = new Patient(newPatientUsername, newPatientPassword,
                                newPatientAadhar, newPatientSexChoice, newPatientHeight,
                                newPatientWeight, newPatientDate);
                        _patientList.add(newPatient);
                        System.out.println("Patient succesfully added!");
                        currentUser = newPatient;
                        break;
                    default:
                        System.out.println("Invalid choice. Try again: ");
                }
                if (currentUser == null) {
                    System.out.println("Invalid credentials. Please try again.");
                } else {

                    System.out.println();
                    System.out.println(
                            "Logged in as " + currentUser.getRole() +
                                    ". Welcome, " + currentUser.getUsername() + "!");
                    isLoggedIn = true;
                    isInitialCall = false;

                    if (currentUser.getRole() == "Patient") {
                        System.out.println();
                        System.out.println("----Menu----");
                        System.out.println("Select one of the options to continue: ");
                        System.out.println("1. View appointment");
                        System.out.println("2. View total expenditure");
                        System.out.println("3. Book an appointment");
                        System.out.println("4. Log out");

                        System.out.println();

                        Patient patient = this._findPatient(currentUser.getUsername(), currentUser.getPassword());
                        boolean patientRunning = true;
                        while (patientRunning) {
                            System.out.print("Enter your choice: ");
                            String patientChoice = _scanner.next();
                            switch (patientChoice) {
                                case ("1"):
                                    try {
                                        System.out
                                                .println(
                                                        "Your token number with "
                                                                + patient.getAppointment().getDoctor().getUsername()
                                                                + " is: " + patient.getAppointment().getTokenNumber());
                                    } catch (Throwable t) {
                                        System.out.println(
                                                "Could not find appointment. Make sure you book one beforehand.");
                                    }
                                    break;
                                case ("2"):
                                    System.out.println("Total expenditure: " + patient.get_totalExpenditure());
                                    break;
                                case ("3"):
                                    patient.bookAppointment();
                                    System.out.println("Available doctors: ");
                                    Integer i = 1;
                                    for (Staff staff : this._staffList) {
                                        if (staff.getIsDoctor()) {
                                            System.out.println(i.toString() + ". " + staff.getUsername());
                                        }
                                        i++;
                                    }
                                    System.out.print("Choose a doctor: ");

                                    int choice = _scanner.nextInt() - 1;

                                    Appointment appointment = new Appointment(patient, _staffList.get(choice));
                                    this.patientAppointmentMONI();
                                    System.out.println("Appointment added!");
                                    patient.setAppointment(appointment);
                                    break;
                                case ("4"):
                                    currentUser = null;
                                    patientRunning = false;
                                    break;
                            }
                        }

                    } else if (currentUser.getRole() == "Staff") {
                        isInitialCall = false;
                        System.out.println();
                        System.out.println("1. Bookings");
                        System.out.println("2. Patients");
                        System.out.println("3. Show wards");
                        System.out.println("4. Logout");
                        System.out.println();
                        System.out.print("Enter your choice: ");
                        String staffChoice = _scanner.next();

                        switch (staffChoice) {
                            case ("1"):
                                System.out.println();
                                System.out.println("1. View bookings");
                                System.out.println("2. Clear all bookings");

                                System.out.print("Enter your choice: ");
                                String innerPatientChoiceOne = _scanner.next();

                                switch (innerPatientChoiceOne) {
                                    case ("1"):
                                        for (Appointment appointment : this._appointmentList) {
                                            System.out.println("Patient " + appointment.getPatient()
                                                    + " has an appointment with " + appointment.getDoctor()
                                                    + " and token number is " + appointment.getTokenNumber());
                                        }
                                        break;
                                    case ("2"):
                                        this._appointmentList.clear();
                                        break;
                                }
                                break;
                            case ("2"):
                                System.out.println("1. View patient details");
                                System.out.println("2. Admit patient");
                                System.out.println("3. Discharge patient");

                                System.out.print("Enter your choice: ");

                                String choice = _scanner.nextLine();

                                switch (choice) {
                                    case ("1"):
                                        Integer i = 1;
                                        for (Patient patient : this._patientList) {
                                            System.out.println(i.toString() + patient.getUsername());
                                        }
                                        System.out.println("Enter your choice: ");

                                        int innerChoice = _scanner.nextInt();

                                        Patient patient = this._patientList.get(innerChoice);
                                        patient.display();
                                        break;
                                    case ("2"):
                                        Integer j = 1;
                                        for (Patient patientJ : this._patientList) {
                                            System.out.println(j.toString() + patientJ.getUsername());
                                        }
                                        System.out.println("Enter your choice: ");

                                        int innerChoice1 = _scanner.nextInt();

                                        Patient patientJ = this._patientList.get(innerChoice1);
                                        Ward ward = new Ward(patientJ);
                                        patientJ.setWard(ward);
                                        patientJ.getAdmittedLMAO();
                                        this.patientAdmittedMONI();
                                        System.out.println("Patient succesfully admitted.");
                                        break;
                                    case ("3"):
                                        Integer k = 1;
                                        for (Patient patientK : this._patientList) {
                                            System.out.println(k.toString() + patientK.getUsername());
                                        }
                                        System.out.println("Enter your choice: ");

                                        int innerChoice2 = _scanner.nextInt();

                                        Patient patientK = this._patientList.get(innerChoice2);
                                        Ward ward3 = patientK.getWard();
                                        for (Ward ward4 : this._wardList) {
                                            if (ward4.equals(ward3)) {
                                                this._wardList.remove(ward4);
                                            }
                                        }
                                        System.out.println("Patient succesfully discharged.");
                                        break;
                                }
                                break;
                            case ("3"):
                                for (Ward ward : _wardList) {
                                    if (ward.isOccupied()) {
                                        System.out.println(
                                                "Ward number " + ward.getWardNumber() + " is occupied by "
                                                        + ward.getPatient().getUsername());
                                    }
                                }
                            case ("4"):
                                break;
                        }
                    } else if (currentUser.getRole() == "Admin") {
                        isInitialCall = false;
                        while (isLoggedIn) {

                            System.out.println();
                            System.out.println("----Menu----");
                            System.out.println("Select one of the options to continue: ");
                            System.out.println("1. View Staff List");
                            System.out.println("2. View Patient List");
                            System.out.println("3. View hospital income");
                            System.out.println("4. Logout");

                            System.out.println();
                            System.out.print("Enter your choice: ");
                            String choice = _scanner.next();

                            System.out.println();
                            switch (choice) {
                                case ("1"):

                                    System.out.println("---");
                                    _viewStaff();
                                    System.out.println("Total number of staff: " + Staff.getStaffCount());
                                    System.out.println("---");

                                    System.out.println();

                                    System.out.println("----Menu----");
                                    System.out.println("1. Add staff");
                                    System.out.println("2. Fire staff");
                                    System.out.println("3. Inspect employee");
                                    System.out.println("4. Go back to main menu.");

                                    System.out.println();
                                    System.out.print("Enter your choice:");
                                    String innerChoice = _scanner.next();

                                    switch (innerChoice) {
                                        case ("1"):

                                            System.out.println("---");

                                            System.out.print("Enter username for new employee: ");

                                            _scanner.nextLine();

                                            String newStaffUsername = _scanner.nextLine();
                                            System.out.print("Enter password: ");
                                            String newStaffPassword = _scanner.nextLine();
                                            System.out.print("Enter salary: ");
                                            int newStaffSalary = _scanner.nextInt();
                                            System.out.print("Is the staff a doctor? [Y/N]: ");
                                            String newStaffIsDoctor = _scanner.next();

                                            if (newStaffIsDoctor.equals("Y")) {
                                                Staff newStaff = new Staff(
                                                        newStaffUsername,
                                                        newStaffPassword,
                                                        newStaffSalary,
                                                        true);
                                                _staffList.add(newStaff);
                                                System.out.println("New employee added successfully!");
                                            } else {
                                                Staff newStaff = new Staff(
                                                        newStaffUsername,
                                                        newStaffPassword,
                                                        newStaffSalary,
                                                        false);
                                                _staffList.add(newStaff);
                                                System.out.println("New employee added successfully!");
                                            }

                                            break;
                                        case ("2"):

                                            System.out.println();
                                            System.out.println("Enter serial number of employee to fire: ");
                                            int serialN = _scanner.nextInt();
                                            System.out.println(
                                                    _staffList.get(serialN - 1).getUsername()
                                                            + " was succesfully fired.");
                                            _staffList.remove(serialN - 1);

                                            break;
                                        case ("3"):

                                            System.out.println();
                                            System.out.print("Enter serial number of employee to inspect: ");
                                            int inspectingSerialN = _scanner.nextInt();

                                            System.out.println("----");
                                            Staff inspectStaff = _staffList.get(inspectingSerialN - 1);
                                            inspectStaff.display();

                                            break;
                                        case ("4"):
                                            break;
                                        default:
                                            System.out.println();
                                            System.out.println("Invalid choice. Try again:");
                                    }
                                    break;
                                case ("2"):

                                    System.out.println("---");
                                    _viewPatients();
                                    System.out.println("Total number of patients: " + Patient.get_patientCount());

                                    System.out.println("----Menu----");
                                    System.out.println("1. Add patient");
                                    System.out.println("2. Remove patient");
                                    System.out.println("3. Inspect patient");
                                    System.out.println("4. Go back to main menu.");

                                    System.out.print("Enter your choice:");
                                    String innerChoicePatient = _scanner.next();

                                    switch (innerChoicePatient) {
                                        case ("1"):

                                            System.out.print("Enter username for new patient: ");
                                            _scanner.nextLine();
                                            String newPatientUsername = _scanner.nextLine();
                                            System.out.print("Enter password: ");
                                            String newPatientPassword = _scanner.nextLine();
                                            System.out.print("Enter aadhar number: ");
                                            long newPatientAadhar = _scanner.nextLong();
                                            _scanner.nextLine();
                                            System.out.print("Enter date of registration in DDMMYYYY: ");
                                            String newPatientDOR = _scanner.nextLine(); // Date of registration
                                            Date newPatientDate = new Date(newPatientDOR.substring(0, 2),
                                                    newPatientDOR.substring(2, 4),
                                                    newPatientDOR.substring(4, 8));
                                            System.out.print("Enter height in cm: ");
                                            int newPatientHeight = _scanner.nextInt();
                                            _scanner.nextLine();
                                            System.out.print("Enter weight in kg: ");
                                            int newPatientWeight = _scanner.nextInt();
                                            _scanner.nextLine();
                                            System.out.print("Patient sex? [M/F]: ");
                                            String newPatientSexChoice = _scanner.nextLine();

                                            Patient newPatient = new Patient(newPatientUsername, newPatientPassword,
                                                    newPatientAadhar, newPatientSexChoice, newPatientHeight,
                                                    newPatientWeight, newPatientDate);
                                            _patientList.add(newPatient);
                                            System.out.println("Patient succesfully added!");

                                            break;
                                        case ("2"):

                                            _viewPatients();
                                            System.out.print("Enter serial number of patient to remove: ");
                                            int serialN = _scanner.nextInt();
                                            System.out.println(
                                                    _patientList.get(serialN - 1).getUsername()
                                                            + " succesfully removed.");
                                            _patientList.remove(serialN - 1);
                                            Patient.decrementPatientCount();

                                            break;
                                        case ("3"):

                                            _viewPatients();
                                            System.out.print("Enter serial number of patient to inspect:");
                                            int inspectPatientSerial = _scanner.nextInt();

                                            System.out.println("----");
                                            Patient inspectPatient = _patientList.get(inspectPatientSerial - 1);
                                            inspectPatient.display();

                                            break;
                                        case ("4"):
                                            break;
                                        default:
                                            System.out.println("Invalid choice. Try again:");
                                    }
                                    break;
                                case ("3"):
                                    System.out.println("Current balance: " + this._getAccountBalance());
                                    break;
                                case ("4"):
                                    currentUser = null;
                                    isLoggedIn = false;
                                    break;
                                default:
                                    System.out.println("Invalid choice.");
                                    break;
                            }

                        }
                    }
                }
            }
        }

    }

    private int _getAccountBalance() {
        return this._accountBalance;
    }

    private void patientAppointmentMONI() {
        this._accountBalance += 50;
    }

    private void patientAdmittedMONI() {
        this._accountBalance += 150;
    }

    private String _getName() {
        return this._name;
    }

    private void _viewPatients() {
        int serialN = 1;
        for (Patient patient : this._patientList) {
            System.out.println(serialN + ". " + patient.getUsername());
            serialN++;
        }
    }

    private void _viewStaff() {
        int serialN = 1;
        for (Staff staff : this._staffList) {
            System.out.println(serialN + ". " + staff.getUsername());
            serialN++;
        }
    }

    public static void main(String[] args) {
        System.out.print("Enter hospital name: ");
        String hospitalName = _scanner.nextLine();
        HospitalManagementSystem hms = new HospitalManagementSystem(hospitalName);
        hms.start();
    }
}