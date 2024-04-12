package bzu.api.inventoryManagmentSystem.service;

import bzu.api.inventoryManagmentSystem.dto.InventoryDto;
import bzu.api.inventoryManagmentSystem.dto.InventoryPartialDto;

import java.util.List;

public interface InventoryService {
    InventoryDto createInventory(InventoryDto dto);

    InventoryDto getInventoryById(Long id);

    List<InventoryDto> getAllInventories();

    void updateInventory(Long id, InventoryDto dto);

    void updateInventoryPartially(Long id, InventoryPartialDto dto);

    void deleteInventory(Long id);
}
