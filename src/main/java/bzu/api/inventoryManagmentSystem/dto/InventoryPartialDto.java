package bzu.api.inventoryManagmentSystem.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Positive;
import org.hibernate.annotations.CurrentTimestamp;

import java.sql.Timestamp;

public class InventoryPartialDto {
    @Positive
    @DecimalMin(value = "0.0", message = "Quantity must be positive")
    private Long quantity;

    @CurrentTimestamp
    private Timestamp lastUpdated;

    private ProductDto productDto;

    public InventoryPartialDto() {
    }

    public InventoryPartialDto(Long quantity, ProductDto productDto) {
        this.quantity = quantity;
        this.productDto = productDto;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
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

    @Override
    public String toString() {
        return "InventoryPartialDto{" +
                "quantity=" + quantity +
                ", lastUpdated=" + lastUpdated +
                ", productDto=" + productDto +
                '}';
    }
}
