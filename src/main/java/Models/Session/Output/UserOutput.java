package Models.Session.Output;

public class UserOutput {
    private String username;
    private String token;
    private String type;
    private String expirationTime;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(String expirationTime) {
        this.expirationTime = expirationTime;
    }

    @Override
    public String toString() {
        return "UserOutput{" +
                "username='" + username + '\'' +
                ", token='" + token + '\'' +
                ", type='" + type + '\'' +
                ", expirationTime='" + expirationTime + '\'' +
                '}';
    }
}

