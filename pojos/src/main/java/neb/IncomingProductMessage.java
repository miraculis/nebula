package neb;

import java.util.UUID;

public class IncomingProductMessage {
    private UUID productId;
    private String name;

    public IncomingProductMessage(UUID productId, String name) {
        this.productId = productId;
        this.name = name;
    }

    public IncomingProductMessage() {
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "IncomingProductMessage{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                '}';
    }
}
