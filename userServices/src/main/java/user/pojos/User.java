package user.pojos;

public class User {

    private int id_user;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private int age;

    public User(String username, String firstName, String lastName, String password, int age) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.age = age;
    }

    public User(int id_user, String username, String firstName, String lastName, String password, int age) {
        this.id_user = id_user;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.age = age;
    }

    private User() {
        this.id_user = 0;
        this.username = "";
        this.firstName = "";
        this.lastName = "";
        this.password = "";
        this.age = 0;
    }

    public static User createEmptyUser() {
        return new User();
    }

    public String getUsername() {
        return username;
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
