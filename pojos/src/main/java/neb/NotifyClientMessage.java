package neb;

import java.util.UUID;

public class NotifyClientMessage {
    public enum Type {REJECT, ACCEPT}

    private UUID prId;
    private String email;
    private Type action;

    public static NotifyClientMessage of(CheckReviewMessage crm, ReviewProcessedMessage rpm) {
        return new NotifyClientMessage(crm.getPrId(), crm.getEmail(),
                rpm.getAction() == ReviewProcessedMessage.Action.ACCEPTED ? Type.ACCEPT : Type.REJECT);
    }

    public NotifyClientMessage(UUID prId, String email, Type action) {
        this.prId = prId;
        this.email = email;
        this.action = action;
    }

    public NotifyClientMessage() {
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

    public Type getAction() {
        return action;
    }

    public void setAction(Type action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "NotifyClientMessage{" +
                "prId=" + prId +
                ", email='" + email + '\'' +
                ", action=" + action +
                '}';
    }
}
