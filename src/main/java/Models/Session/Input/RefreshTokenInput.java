package Models.Session.Input;

public class RefreshTokenInput {
    private  String refreshToken;

    public RefreshTokenInput(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public String toString() {
        return "RefreshTokenInput{" +
                "refreshToken='" + refreshToken + '\'' +
                '}';
    }
}
