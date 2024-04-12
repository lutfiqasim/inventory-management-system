package bzu.api.inventoryManagmentSystem.service;

import bzu.api.inventoryManagmentSystem.dto.ProductDto;
import bzu.api.inventoryManagmentSystem.dto.ProductPartialDto;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto dto);

    ProductDto getProductById(Long id);

    List<ProductDto> getAllProducts();

    void updateProduct(Long id, ProductDto dto);

    void updateProductPartially(Long id, ProductPartialDto dto);

    void deleteProduct(Long id);

}
