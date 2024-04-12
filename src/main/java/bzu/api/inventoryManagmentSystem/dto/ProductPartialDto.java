package bzu.api.inventoryManagmentSystem.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;

public class ProductPartialDto {

    @Size(min = 3, max = 50)
    private String productName;
    @Size(min = 0, max = 50)
    private String prodDescription;

    @DecimalMin(value = "0.1", message = "Price must be greater than 0")
    private float price;

    private SupplierDto supplier;

    private CategoryDto category;

    public ProductPartialDto() {
    }

    public ProductPartialDto(String productName, String prodDescription, float price, SupplierDto supplier, CategoryDto category) {
        this.productName = productName;
        this.prodDescription = prodDescription;
        this.price = price;
        this.supplier = supplier;
        this.category = category;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProdDescription() {
        return prodDescription;
    }

    public void setProdDescription(String prodDescription) {
        this.prodDescription = prodDescription;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public SupplierDto getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierDto supplier) {
        this.supplier = supplier;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ProductPartialDto{" +
                "productName='" + productName + '\'' +
                ", prodDescription='" + prodDescription + '\'' +
                ", price=" + price +
                ", supplier=" + supplier +
                ", category=" + category +
                '}';
    }
}
