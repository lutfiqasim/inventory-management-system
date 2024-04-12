package bzu.api.inventoryManagmentSystem.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.annotations.CurrentTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InventoryDto {
    private Long id;
    @Positive
    @DecimalMin(value = "0.0", message = "Quantity must be positive")
    private Long quantity;

    @CurrentTimestamp
    private Timestamp lastUpdated;

    @NotNull
    private ProductDto productDto;

    public InventoryDto() {
    }

    public InventoryDto(Long id, Long quantity, Timestamp lastUpdated, ProductDto productId) {
        this.id = id;
        this.quantity = quantity;
        this.lastUpdated = lastUpdated;
        this.productDto = productId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public ProductDto getProductDto() {
        return productDto;
    }

    public void setProductDto(ProductDto productDto) {
        this.productDto = productDto;
    }


    private String getFormattedLastUpdated() {
        LocalDateTime lastUpdatedDateTime = lastUpdated.toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/M/d: H:mm");
        return lastUpdatedDateTime.format(formatter);
    }

    @Override
    public String toString() {
        return "InventoryDto{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", lastUpdated=" + getLastUpdated() +
                ", product=" + productDto +
                '}';
    }
}
