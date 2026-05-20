package Models.Session.Output;

public class LogoutOutput {
    private String isLoggedOut;
    private String comment;

    public LogoutOutput() {
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getIsLoggedOut() {
        return isLoggedOut;
    }

    public void setIsLoggedOut(String isLoggedOut) {
        this.isLoggedOut = isLoggedOut;
    }

    @Override
    public String toString() {
        return "LogoutOutput{" +
                "isLoggedOut='" + isLoggedOut + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
