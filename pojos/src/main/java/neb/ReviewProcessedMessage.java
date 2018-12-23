package neb;

import java.util.UUID;

public class ReviewProcessedMessage {
    public enum Action {
        ACCEPTED, REJECTED, ACCEPTED_NOTIFIED, REJECTED_NOTIFIED
    }

    private UUID prId;
    private Action action;

    public static ReviewProcessedMessage acceptedOf(UUID prId) {
        return new ReviewProcessedMessage(prId, Action.ACCEPTED);
    }

    public static ReviewProcessedMessage rejectedOf(UUID prId) {
        return new ReviewProcessedMessage(prId, Action.REJECTED);
    }

    public static ReviewProcessedMessage acceptedNtfOf(UUID prId) {
        return new ReviewProcessedMessage(prId, Action.ACCEPTED_NOTIFIED);
    }

    public static ReviewProcessedMessage rejectedNtfOf(UUID prId) {
        return new ReviewProcessedMessage(prId, Action.REJECTED_NOTIFIED);
    }

    public ReviewProcessedMessage(UUID prId, Action action) {
        this.prId = prId;
        this.action = action;
    }

    public ReviewProcessedMessage() {
    }

    public UUID getPrId() {
        return prId;
    }

    public void setPrId(UUID prId) {
        this.prId = prId;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "ReviewProcessedMessage{" +
                "prId=" + prId +
                ", action=" + action +
                '}';
    }
}
