package neb;

class Review {
    private final String productid;
    private final String name;
    private final String email;
    private final String review;

    public Review(String productid, String name, String email, String review) {
        this.productid = productid;
        this.name = name;
        this.email = email;
        this.review = review;
    }

    public String getProductid() {
        return productid;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getReview() {
        return review;
    }

    @Override
    public String toString() {
        return "Review{" +
                "productid='" + productid + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", review='" + review + '\'' +
                '}';
    }
}