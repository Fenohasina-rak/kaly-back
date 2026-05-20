package Models.Session.Input;

public class LogoutInput {
    private String username;

    public LogoutInput() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "LogoutInput{" +
                "userId='" + username + '\'' +
                '}';
    }
}
