package Models.Input;

public class AuthenticationInput {
    private  String username;
    private String password;

    public AuthenticationInput() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AuthenticationInput{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
