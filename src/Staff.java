public class Staff extends User {
    private int salary;
    private static int staffCount = 0;
    private boolean isDoctor;

    Staff(String username, String password) {
        ///@param username Stores username.
        ///@param password Stores password.
        super(username, password, "Staff");
        staffCount++;
    }

    Staff(String username, String password, int salary, boolean isDoctor) {
        super(username, password, "Staff");
        this.salary = salary;
        this.isDoctor = isDoctor;
        staffCount++;
    }

    @Override
    public void display() {
        System.out.println("Username: " + this.getUsername());
        System.out.println("Password: " + this.getPassword());
        System.out.println("Salary: " + this.getSalary());
        System.out
                .println("Doctor?: " + (this.getIsDoctor() ? "Yes" : "No"));
    }

    public static int getStaffCount() {
        return Staff.staffCount;
    }

    public int getSalary() {
        return this.salary;
    }

    public boolean getIsDoctor() {
        ///@returns Verification if the current account holder is a doctor or not.
        return this.isDoctor;
    }
}