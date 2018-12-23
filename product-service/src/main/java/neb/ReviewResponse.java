package neb;

import com.datastax.driver.core.utils.UUIDs;

public class ReviewResponse {
    private final Boolean success;
    private final String reviewID;

    public ReviewResponse(Boolean success, String reviewID) {
        this.success = success;
        this.reviewID = reviewID;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getReviewID() {
        return reviewID;
    }

    public static ReviewResponse of(ProductReview pr) {
        return new ReviewResponse(true ,pr.getId().toString());
    }

    public static ReviewResponse rejectOf(ProductReview pr) {
        return new ReviewResponse(false, UUIDs.timeBased().toString());
    }
}
