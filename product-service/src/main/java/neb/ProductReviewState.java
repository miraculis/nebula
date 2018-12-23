package neb;

import com.datastax.driver.core.utils.UUIDs;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table(value="productreviewstates")
public class ProductReviewState {
    @PrimaryKey
    private UUID id;
    private UUID productReviewId;
    private String state;

    public ProductReviewState(UUID id, UUID productReviewId, String state) {
        this.id = id;
        this.productReviewId = productReviewId;
        this.state = state;
    }

    public ProductReviewState(String state) {
        this.state = state;
    }

    public ProductReviewState() {
    }

    public static ProductReviewState of(ReviewProcessedMessage msg) {
        return new ProductReviewState(UUIDs.timeBased(), msg.getPrId(), msg.getAction().name());
    }

    public UUID getProductReviewId() {
        return productReviewId;
    }

    public void setProductReviewId(UUID productReviewId) {
        this.productReviewId = productReviewId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ProductReviewState{" +
                "id=" + id +
                ", productReviewId=" + productReviewId +
                ", state='" + state + '\'' +
                '}';
    }
}
