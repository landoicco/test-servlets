package database.user;

public class UserDTO {

    private int id_user;
    private String firstName;
    private String lastName;
    private String password;
    private int age;

    public UserDTO(String firstName, String lastName, String password, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPassword() {
        return password;
    }

    public int getAge() {
        return age;
    }

    public int getId_user() {
        return id_user;
    }

    public String getLastName() {
        return lastName;
    }
}
