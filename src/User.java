///Accepts username and password, following which the role of the account is shown. 
abstract public class User implements Displayable {
    private String username;
    private String password;
    private String role;

    User() {
        this.username = "";
        this.password = "";
        this.role = "";
    }

    User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    User(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = "Patient";
    }

    public String auth(String username, String password) {
        if (username.equals(this.username)) {
            if (password.equals(this.password)) {
                return this.role;
            }
            System.out.println("Incorrect password");
            return "null";
        }
        return "null";
    }

    public void display() {
        System.out.println("Username: " + this.getUsername());
        System.out.println("Password: " + this.getPassword());
        System.out.println("Role: " + this.getRole());
    }

    public String getUsername() {
        return this.username;
    }

    public String getRole() {
        return this.role;
    }

    public String getPassword() {
        return this.password;
    }

}
