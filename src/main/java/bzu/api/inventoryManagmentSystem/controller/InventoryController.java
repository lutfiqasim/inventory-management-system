package bzu.api.inventoryManagmentSystem.controller;

import bzu.api.inventoryManagmentSystem.controller.handler.ValidationExceptionHandler;
import bzu.api.inventoryManagmentSystem.dto.InventoryDto;
import bzu.api.inventoryManagmentSystem.dto.InventoryPartialDto;
import bzu.api.inventoryManagmentSystem.exception.BadRequestException;
import bzu.api.inventoryManagmentSystem.exception.ResourceNotFoundException;
import bzu.api.inventoryManagmentSystem.service.InventoryImp.InventoryServiceImp;
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
@RequestMapping("/inventories")
public class InventoryController {
    private final InventoryServiceImp inventoryService;

    @Autowired
    public InventoryController(InventoryServiceImp inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAllInventories() {
        try {
            List<InventoryDto> inventoryDtoList = inventoryService.getAllInventories();
            return ResponseEntity.ok().body(inventoryDtoList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while retrieving suppliers");
        }
    }

    @GetMapping("{inventoryId}")
    public ResponseEntity<?> getInventoryById(@PathVariable Long inventoryId) {
        try {
            InventoryDto inventoryDto = inventoryService.getInventoryById(inventoryId);
            return ResponseEntity.ok(inventoryDto);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while retrieving inventory with id: " + inventoryId);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> addNewInventory(@Valid @RequestBody InventoryDto inventoryDto, BindingResult result) {
        if (result.hasErrors()) {
            return ValidationExceptionHandler.validate(result, "Inventory");
        }
        try {
            InventoryDto createdInventory = inventoryService.createInventory(inventoryDto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(createdInventory);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body(e.getMessage());
        }
    }

    @PutMapping("/{inventoryId}")
    public ResponseEntity<?> updateExistingInventory(@PathVariable Long inventoryId, @Valid @RequestBody InventoryDto inventoryDto, BindingResult result) {
        if (result.hasErrors()) {
            return ValidationExceptionHandler.validate(result, "Inventory");
        }
        try {
            inventoryService.updateInventory(inventoryId, inventoryDto);
            return ResponseEntity.ok("Inventory with id: " + inventoryId + ", updated successfully");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while updating inventory with id: " + inventoryId);
        }
    }

    @PatchMapping("/{inventoryId}")
    public ResponseEntity<?> updateInventoryPartially(@PathVariable Long inventoryId, @Valid @RequestBody InventoryPartialDto inventoryDto, BindingResult result) {
        if (result.hasErrors()) {
            return ValidationExceptionHandler.validate(result, "Inventory");
        }
        try {
            inventoryService.updateInventoryPartially(inventoryId, inventoryDto);
            return ResponseEntity.ok("Inventory with id: " + inventoryId + ", partially updated successfully");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while updating inventory with id: " + inventoryId);
        }
    }

    @DeleteMapping("/{inventoryId}")
    public ResponseEntity<?> deleteProductById(@PathVariable Long inventoryId) {
        try {
            inventoryService.deleteInventory(inventoryId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).
                    body("Successfully deleted inventory with id: " + inventoryId);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("An error occurred while deleting inventory");
        }

    }

}
