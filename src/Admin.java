///Sets up admin username and password.
public class Admin extends User {

    Admin(String username, String password) {
        super(username, password, "Admin");
    }

}
