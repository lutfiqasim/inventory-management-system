package bzu.api.inventoryManagmentSystem.dto;

public class ProductDto {
    private Long productId;
    private String productName;
    private String prodDescription;
    private float price;
    private SupplierDto supplierDto;

    public ProductDto() {
    }

    public ProductDto(Long productId, String productName, String prodDescription, float price, SupplierDto supplierId) {
        this.productId = productId;
        this.productName = productName;
        this.prodDescription = prodDescription;
        this.price = price;
        this.supplierDto = supplierId;
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

    @Override
    public String toString() {
        return "ProductDto{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", prodDescription='" + prodDescription + '\'' +
                ", price=" + price +
                ", supplierId=" + supplierDto +
                '}';
    }
}
