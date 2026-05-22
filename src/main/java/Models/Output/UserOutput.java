package Models.Output;

public class UserOutput {
    private String username;
    private String token;
    private String type;
    private Integer expirationTime;
    private String refreshToken;

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

    public Integer getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Integer expirationTime) {
        this.expirationTime = expirationTime;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public String toString() {
        return "UserOutput{" +
                "username='" + username + '\'' +
                ", token='" + token + '\'' +
                ", type='" + type + '\'' +
                ", expirationTime=" + expirationTime +
                ", refreshToken='" + refreshToken + '\'' +
                '}';
    }
}

