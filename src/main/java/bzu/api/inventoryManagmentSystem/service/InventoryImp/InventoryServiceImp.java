package bzu.api.inventoryManagmentSystem.service.InventoryImp;

import bzu.api.inventoryManagmentSystem.dto.InventoryDto;
import bzu.api.inventoryManagmentSystem.dto.InventoryPartialDto;
import bzu.api.inventoryManagmentSystem.exception.ResourceNotFoundException;
import bzu.api.inventoryManagmentSystem.model.Inventory;
import bzu.api.inventoryManagmentSystem.model.Product;
import bzu.api.inventoryManagmentSystem.repository.InventoryRepository;
import bzu.api.inventoryManagmentSystem.repository.ProductRepository;
import bzu.api.inventoryManagmentSystem.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImp implements InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public InventoryDto createInventory(InventoryDto dto) {
        dto.setLastUpdated(Timestamp.valueOf(LocalDateTime.now()));
        Inventory newInventory = inventoryRepository.save(mapToEntity(dto));
        return entityToMap(newInventory);
    }

    @Override
    public InventoryDto getInventoryById(Long id) {
        Inventory inventory = inventoryRepository.findById(id).orElseThrow(() ->
                getResourceNotFoundException(id, "Inventory"));
        return entityToMap(inventory);
    }

    @Override
    public List<InventoryDto> getAllInventories() {
        List<Inventory> inventoryList = inventoryRepository.findAll();
        return inventoryList.stream().map(this::entityToMap).
                collect(Collectors.toList());
    }

    @Override
    public void updateInventory(Long id, InventoryDto dto) {
        Inventory existingInventory = inventoryRepository.findById(id).orElseThrow(() ->
                getResourceNotFoundException(id, "Inventory"));
        Product product = productRepository.findById(dto.getProductDto().getProductId()).
                orElseThrow(() -> getResourceNotFoundException(id, "Product"));
        existingInventory.setProduct(product);
        existingInventory.setQuantity(dto.getQuantity());
        dto.setLastUpdated(Timestamp.valueOf(LocalDateTime.now()));
        existingInventory.setLastUpdated(Timestamp.valueOf(LocalDateTime.now()));
        inventoryRepository.save(existingInventory);
    }

    @Override
    public void updateInventoryPartially(Long id, InventoryPartialDto dto) {
        Inventory existingInventory = inventoryRepository.findById(id).orElseThrow(() ->
                getResourceNotFoundException(id, "Inventory"));

        inventoryRepository.save(existingInventory);
        if (dto.getProductDto() != null) {
            Product product = productRepository.findById(dto.getProductDto().getProductId()).
                    orElseThrow(() -> getResourceNotFoundException(id, "Product"));
            existingInventory.setProduct(product);
        }
        if (dto.getQuantity() != null) {
            existingInventory.setQuantity(dto.getQuantity());
        }
        dto.setLastUpdated(Timestamp.valueOf(LocalDateTime.now()));
        existingInventory.setLastUpdated(Timestamp.valueOf(LocalDateTime.now()));
        inventoryRepository.save(existingInventory);

    }

    @Override
    public void deleteInventory(Long id) {
        if (!inventoryRepository.existsById(id))
            throw getResourceNotFoundException(id, "Inventory");

        inventoryRepository.deleteById(id);

    }

    private Inventory mapToEntity(InventoryDto dto) {
        if (dto == null)
            return null;
        Product product = productRepository.findById(dto.getProductDto().getProductId()).orElseThrow(() ->
                getResourceNotFoundException(dto.getProductDto().getProductId(), "Product"));
        Inventory inventory = new Inventory();
        inventory.setProduct(product);
        inventory.setQuantity(dto.getQuantity());
        inventory.setLastUpdated(Timestamp.valueOf(LocalDateTime.now()));
        return inventory;
    }

    private InventoryDto entityToMap(Inventory inventory) {
        if (inventory == null)
            return null;
        InventoryDto dto = new InventoryDto();
        dto.setId(inventory.getId());
        dto.setQuantity(inventory.getQuantity());
        dto.setProductDto(inventory.getProduct().toProductDto());
        dto.setLastUpdated(inventory.getLastUpdated());
        return dto;
    }

    private static ResourceNotFoundException getResourceNotFoundException(Long id, String resource) {
        return new ResourceNotFoundException(resource, "id", String.valueOf(id));
    }
}
