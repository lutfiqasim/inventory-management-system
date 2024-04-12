package bzu.api.inventoryManagmentSystem.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductDto {
    private Long productId;
    @NotNull
    @Size(min = 3, max = 50)
    private String productName;
    @Size(min = 0, max = 50)
    private String prodDescription;
    @NotNull
    @DecimalMin(value = "0.1", message = "Price must be greater than 0")
    private float price;
    private SupplierDto supplierDto;
    @NotNull
    private CategoryDto categoryDto;


    public ProductDto() {
    }

    public ProductDto(Long productId, String productName, String prodDescription, float price, SupplierDto supplierDto, CategoryDto categoryDto) {
        this.productId = productId;
        this.productName = productName;
        this.prodDescription = prodDescription;
        this.price = price;
        System.out.println("-------------------------");
        System.out.println(supplierDto.toString());
        System.out.println("-------------------------");
        this.supplierDto = supplierDto;
        this.categoryDto = categoryDto;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public SupplierDto getSupplierDto() {
        return supplierDto;
    }

    public void setSupplierDto(SupplierDto supplierDto) {
        this.supplierDto = supplierDto;
    }

    public CategoryDto getCategoryDto() {
        return categoryDto;
    }

    public void setCategoryDto(CategoryDto categoryDto) {
        this.categoryDto = categoryDto;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", prodDescription='" + prodDescription + '\'' +
                ", price=" + price +
                ", supplierDto= " + supplierDto +
                ", categoryDto= " + categoryDto +
                '}';
    }
}
