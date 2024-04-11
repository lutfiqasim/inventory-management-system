package bzu.api.inventoryManagmentSystem.dto;

import java.sql.Timestamp;

public class InventoryDto {
    private Long id;
    private Long quantity;

    private Timestamp lastUpdated;

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
        return "InventoryDto{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", lastUpdated=" + lastUpdated +
                ", productId=" + productDto +
                '}';
    }
}
