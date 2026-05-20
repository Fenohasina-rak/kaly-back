package Models.Session.Output;

public class AuthenticationOutput {
    private Boolean isAuthenticated;
    private String comment;
    private UserOutput user;


    public Boolean getAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        isAuthenticated = authenticated;
    }

    public UserOutput getUser() {
        return user;
    }

    public void setUser(UserOutput user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "AuthenticationOutput{" +
                "isAuthenticated=" + isAuthenticated +
                ", user=" + user +
                '}';
    }
}
