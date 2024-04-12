package bzu.api.inventoryManagmentSystem.model;

import bzu.api.inventoryManagmentSystem.dto.ProductDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "prod_name", nullable = false, length = 50)
    private String productName;
    @Column(name = "prod_description", length = 50)
    private String prodDescription;
    @Column(nullable = false, columnDefinition = "FLOAT CHECK (price > 0)")
    private float price;
    @ManyToOne
    @JoinColumn(name = "supplierId")
    private Supplier supplier;
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    public Product() {
    }

    public Product(String productName, float price, Supplier supplier, Category category) {
        this.productName = productName;
        this.price = price;
        this.supplier = supplier;
        this.category = category;
    }

    public Product(String productName, String prodDescription, float price, Supplier supplier) {
        this.productName = productName;
        this.prodDescription = prodDescription;
        this.price = price;
        this.supplier = supplier;
    }

    public Long getId() {
        return id;
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

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productName.equals(product.productName);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", prodDescription='" + prodDescription + '\'' +
                ", price=" + price +
                ", supplier=" + supplier +
                ", category=" + category +
                '}';
    }

    public ProductDto toProductDto() {
        ProductDto productDto = new ProductDto();
        productDto.setProductId(this.id);
        productDto.setCategoryDto(this.category.toCategoryDto());
        productDto.setSupplierDto(this.supplier.toSupplierDto());
        productDto.setPrice(this.price);
        productDto.setProductName(this.productName);
        productDto.setProdDescription(this.prodDescription);
        return productDto;
    }
}
