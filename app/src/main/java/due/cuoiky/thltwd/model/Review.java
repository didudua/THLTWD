package due.cuoiky.thltwd.model;

public class Review {
    private String user;
    private String country;
    private String comment;
    private String date;

    public Review() {
    }

    public Review(String user, String country, String comment, String date) {
        this.user = user;
        this.country = country;
        this.comment = comment;
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}