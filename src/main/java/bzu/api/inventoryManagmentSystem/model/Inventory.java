package bzu.api.inventoryManagmentSystem.model;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long quantity;

    @Column(name = "last_updated", nullable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp lastUpdated;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    public Inventory() {
    }

    public Inventory(Long quantity, Timestamp lastUpdated, Product product) {
        this.quantity = quantity;
        this.lastUpdated = lastUpdated;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getLastUpdated() {
        return getFormattedLastUpdated();
    }

    private String getFormattedLastUpdated() {
        LocalDateTime lastUpdatedDateTime = lastUpdated.toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/M/d: H:mm");
        return lastUpdatedDateTime.format(formatter);
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", lastUpdated=" + getLastUpdated() +
                ", product=" + product +
                '}';
    }
}
