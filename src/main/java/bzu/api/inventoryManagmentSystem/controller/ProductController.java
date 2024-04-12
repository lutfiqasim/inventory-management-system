package bzu.api.inventoryManagmentSystem.controller;

import bzu.api.inventoryManagmentSystem.controller.handler.ValidationExceptionHandler;
import bzu.api.inventoryManagmentSystem.dto.ProductDto;
import bzu.api.inventoryManagmentSystem.dto.ProductPartialDto;
import bzu.api.inventoryManagmentSystem.exception.BadRequestException;
import bzu.api.inventoryManagmentSystem.exception.ResourceNotFoundException;
import bzu.api.inventoryManagmentSystem.service.CategoryImp.CategoryServiceImp;
import bzu.api.inventoryManagmentSystem.service.ProductImp.ProductServiceImp;
import bzu.api.inventoryManagmentSystem.service.SupplierImp.SupplierServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductServiceImp productService;


    @Autowired
    public ProductController(ProductServiceImp productService, SupplierServiceImp supplierService, CategoryServiceImp categoryService) {
        this.productService = productService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAllProducts() {
        try {
            List<ProductDto> productDtos = productService.getAllProducts();
            return ResponseEntity.ok(productDtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while retrieving products");
        }
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable Long productId) {
        try {
            ProductDto productDto = productService.getProductById(productId);
            return ResponseEntity.ok(productDto);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("An error occurred while retrieving product");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDto productDto, BindingResult result) {
        if (result.hasErrors()) {
            return ValidationExceptionHandler.validate(result, "Product");
        }
        try {
            ProductDto dto = getProductDto(productDto);
            ProductDto createdProductDto = productService.createProduct(dto);

            return ResponseEntity.status(HttpStatus.CREATED).body(createdProductDto);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while creating the product: " + e.getMessage());
        }
    }

    @PutMapping("/{productId}")
    public ResponseEntity<?> updateExistingProduct(@PathVariable Long productId, @Valid @RequestBody ProductDto productDto, BindingResult result) {
        if (result.hasErrors()) {
            return ValidationExceptionHandler.validate(result, "Product");
        }
        try {
            productService.updateProduct(productId, productDto);

        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while Updating the product: " + e.getMessage());
        }

        return ResponseEntity.ok("Product with id: " + productId + ", updated successfully");

    }

    @PatchMapping("/{productId}")
    public ResponseEntity<?> updateCategoryPratially(@PathVariable Long productId, @Valid @RequestBody ProductPartialDto productPartialDto, BindingResult result) {
        if (result.hasErrors()) {
            return ValidationExceptionHandler.validate(result, "Product");
        }
        try {
            productService.updateProductPartially(productId, productPartialDto);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("An error occurred while updating category");
        }

        return ResponseEntity.ok("Product with id:" + productId + ", partially updated successfully");

    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProductById(@PathVariable Long productId) {
        try {
            productService.deleteProduct(productId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).
                    body("Successfully deleted product with id: " + productId);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("An error occurred while deleting product");
        }

    }

    private ProductDto getProductDto(ProductDto productDto) throws ResourceNotFoundException {

        ProductDto product = new ProductDto();
        product.setProductName(productDto.getProductName());
        product.setProdDescription(productDto.getProdDescription());
        product.setPrice(productDto.getPrice());
        product.setSupplierDto(productDto.getSupplierDto());
        product.setCategoryDto(productDto.getCategoryDto());
        return product;
    }
}
