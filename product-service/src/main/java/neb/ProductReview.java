package neb;


import com.datastax.driver.core.utils.UUIDs;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table(value = "productreviews")
public class ProductReview {
    @PrimaryKey
    private UUID id;
    private UUID productId;
    private String reviewerName;
    private String email;
    private int rating;
    private String comments;

    private ProductReview(UUID prId, UUID pId, String rn, String ea, int r, String c) {
        this.id = prId;
        this.productId = pId;
        this.reviewerName = rn;
        this.email = ea;
        this.rating = r;
        this.comments = c;
    }

    public static ProductReview of(Review r) {
        return new ProductReview(UUIDs.timeBased(), UUID.fromString(r.getProductid()), r.getName(),
                r.getEmail(), 5, r.getReview());
    }

    public CheckReviewMessage toCheckMsg() {
        return new CheckReviewMessage(this.id, this.email, this.comments);
    }

    public UUID getId() {
        return id;
    }

    public UUID getProductId() {
        return productId;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public String getEmail() {
        return email;
    }

    public int getRating() {
        return rating;
    }

    public String getComments() {
        return comments;
    }

    @Override
    public String toString() {
        return "ProductReview{" +
                "id=" + id +
                ", productId=" + productId +
                ", reviewerName='" + reviewerName + '\'' +
                ", email='" + email + '\'' +
                ", rating=" + rating +
                ", comments='" + comments + '\'' +
                '}';
    }
}
