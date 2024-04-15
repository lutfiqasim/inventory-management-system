package bzu.api.inventoryManagmentSystem.service.ProductImp;

import bzu.api.inventoryManagmentSystem.dto.ProductDto;
import bzu.api.inventoryManagmentSystem.dto.ProductPartialDto;
import bzu.api.inventoryManagmentSystem.exception.ResourceNotFoundException;
import bzu.api.inventoryManagmentSystem.model.Category;
import bzu.api.inventoryManagmentSystem.model.Product;
import bzu.api.inventoryManagmentSystem.model.Supplier;
import bzu.api.inventoryManagmentSystem.repository.CategoryRepository;
import bzu.api.inventoryManagmentSystem.repository.ProductRepository;
import bzu.api.inventoryManagmentSystem.repository.SupplierRepository;
import bzu.api.inventoryManagmentSystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImp implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    private SupplierRepository supplierRepository;

    @Autowired
    public ProductServiceImp(ProductRepository productRepository, CategoryRepository categoryRepository, SupplierRepository supplierRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.supplierRepository = supplierRepository;
    }

    @Override
    public ProductDto createProduct(ProductDto dto) {
        Product newProduct = productRepository.save(mapToEntity(dto));
        return entityToMap(newProduct);
    }

    @Override
    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                getResourceNotFoundException(id, "Product"));
        return entityToMap(product);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::entityToMap).
                collect(Collectors.toList());
    }

    @Override
    public void updateProduct(Long id, ProductDto dto) {
        Product existingProduct = productRepository.findById(id).orElseThrow(() ->
                getResourceNotFoundException(id, "Product"));
        //update existing product with new data
        Category category = categoryRepository.findById(dto.getCategoryDto().getId()).orElseThrow(() ->
                getResourceNotFoundException(dto.getSupplierDto().getId(), "Category"));
        existingProduct.setCategory(category);
        Supplier supplier = supplierRepository.findById(dto.getSupplierDto().getId()).orElseThrow(() ->
                getResourceNotFoundException(dto.getSupplierDto().getId(), "Supplier"));
        existingProduct.setSupplier(supplier);
        existingProduct.setProductName(dto.getProductName());
        existingProduct.setPrice(dto.getPrice());
        existingProduct.setProdDescription(dto.getProdDescription());
        productRepository.save(existingProduct);
    }

    @Override
    public void updateProductPartially(Long id, ProductPartialDto dto) {
        Product existingProduct = productRepository.findById(id).orElseThrow(() ->
                getResourceNotFoundException(id, "Product"));
        if (dto.getCategory() != null)
            existingProduct.setCategory(dto.getCategory().toCategory());
        if (dto.getPrice() != 0.0)
            existingProduct.setPrice(dto.getPrice());
        if (dto.getProductName() != null)
            existingProduct.setProductName(dto.getProductName());
        if (dto.getSupplier() != null)
            existingProduct.setSupplier(dto.getSupplier().toSupplier());
        if (dto.getProdDescription() != null)
            existingProduct.setProdDescription(dto.getProdDescription());
        productRepository.save(existingProduct);

    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id))
            throw getResourceNotFoundException(id, "Product");
        productRepository.deleteById(id);
    }

    private static ResourceNotFoundException getResourceNotFoundException(Long id, String resource) {
        return new ResourceNotFoundException(resource, "id", String.valueOf(id));
    }

    private Product mapToEntity(ProductDto dto) {
        if (dto == null)
            return null;
        Product product = new Product();
        Supplier s = supplierRepository.findById(dto.getSupplierDto().getId()).orElseThrow(() ->
                getResourceNotFoundException(dto.getSupplierDto().getId(), "Supplier"));
        product.setSupplier(s);
        Category c = categoryRepository.findById(dto.getCategoryDto().getId()).orElseThrow(() ->
                getResourceNotFoundException(dto.getCategoryDto().getId(), "Category"));
        product.setCategory(c);
        product.setPrice(dto.getPrice());
        product.setProductName(dto.getProductName());
        product.setProdDescription(dto.getProdDescription());
        return product;
    }

    private ProductDto entityToMap(Product product) {
        if (product == null) {
            return null;
        }
        ProductDto dto = new ProductDto();
        dto.setProductId(product.getId());
        dto.setPrice(product.getPrice());
        dto.setProductName(product.getProductName());
        dto.setProdDescription(product.getProdDescription());
        dto.setSupplierDto(product.getSupplier().toSupplierDto());
        dto.setCategoryDto(product.getCategory().toCategoryDto());
        return dto;
    }

}
