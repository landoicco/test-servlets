package local.user;

public class RegisterRequester {

    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String age;

    public RegisterRequester(String username, String firstName, String lastName,
                             String password, String age) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getAge() {
        return age;
    }
}
