package local.user;

public class LoginRequester {
    private String name;
    private String password;

    public LoginRequester(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
