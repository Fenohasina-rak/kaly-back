package Models.Output;

public class CheckAuthenticationOutput {
    private Boolean isAuthenticated;
    private String username;

    public CheckAuthenticationOutput() {
    }

    public Boolean getIsAuthenticated() {
        return isAuthenticated;
    }

    public void setIsAuthenticated(Boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
