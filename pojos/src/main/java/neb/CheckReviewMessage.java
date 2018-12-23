package neb;

import java.util.UUID;

public class CheckReviewMessage {
    private UUID prId;
    private String email;
    private String review;

    public CheckReviewMessage(UUID prId, String email, String review) {
        this.prId = prId;
        this.email = email;
        this.review = review;
    }

    public CheckReviewMessage() {
    }

    public UUID getPrId() {
        return prId;
    }

    public void setPrId(UUID prId) {
        this.prId = prId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public String toString() {
        return "CheckReviewMessage{" +
                "prId=" + prId +
                ", email='" + email + '\'' +
                ", review='" + review + '\'' +
                '}';
    }
}
