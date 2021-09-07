package user.pojos;

public class LoginRequester {
    private String username;
    private String password;

    public LoginRequester(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private LoginRequester() {
        this.username = "";
        this.password = "";
    }

    public static LoginRequester createEmptyLoginRequester() {
        return new LoginRequester();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
