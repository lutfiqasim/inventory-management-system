package bzu.api.inventoryManagmentSystem.controller;

import bzu.api.inventoryManagmentSystem.dto.SupplierDto;
import bzu.api.inventoryManagmentSystem.dto.SupplierPartialDto;
import bzu.api.inventoryManagmentSystem.exception.BadRequestException;
import bzu.api.inventoryManagmentSystem.exception.ResourceNotFoundException;
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

@RestController()
@RequestMapping("/suppliers")
public class SupplierController {
    private final SupplierServiceImp supplierService;

    @Autowired
    public SupplierController(SupplierServiceImp supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAllSuppliers() {
        try {
            List<SupplierDto> supplierDtos = supplierService.getAllSuppliers();
            return ResponseEntity.ok(supplierDtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while retrieving suppliers");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> addNewSupplier(@Valid @RequestBody SupplierDto supplierDto, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, Object> errorMap = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().
                    body(new BadRequestException("Validation failed", "Supplier", errorMap.toString()));
        }
        try {
            SupplierDto createdSupplier = supplierService.createSupplier(supplierDto);
            return ResponseEntity.status(HttpStatus.CREATED).
                    body(createdSupplier);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("An error occurred while creating supplier");
        }
    }

    @GetMapping("/{supplierId}")
    public ResponseEntity<?> getSupplierById(@PathVariable Long supplierId) {
        try {
            SupplierDto supplierDto = supplierService.getSupplierById(supplierId);
            return ResponseEntity.ok(supplierDto);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("An error occurred while retrieving supplier with supplierId: " + supplierId);
        }
    }

    @PutMapping("/{supplierId}")
    public ResponseEntity<?> updateExistingSupplier(@PathVariable Long supplierId, @Valid @RequestBody SupplierDto supplierDto, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, Object> errorMap = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().
                    body(new BadRequestException("Validation failed", "Supplier", errorMap.toString()).toString());
        }
        try {
            supplierService.updateSupplier(supplierId, supplierDto);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("An error occurred while updating supplier");
        }
        return ResponseEntity.ok("Supplier with id:" + supplierId + ", updated successfully");
    }

    @PatchMapping("/{supplierId}")
    public ResponseEntity<?> updateSupplierPartially(@PathVariable Long supplierId, @Valid @RequestBody SupplierPartialDto supplierPartialDto, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, Object> errorMap = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().
                    body(new BadRequestException("Validation failed", "Category", errorMap.toString()).toString());
        }
        try {
            supplierService.updateSupplierPartially(supplierId, supplierPartialDto);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("An error occurred while updating supplier");
        }
        return ResponseEntity.ok("Supplier with id:" + supplierId + ", partially updated successfully");

    }

    @DeleteMapping("/{supplierId}")
    public ResponseEntity<?> deleteSupplierById(@PathVariable Long supplierId) {
        try {
            supplierService.deleteSupplier(supplierId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).
                    body("Successfully deleted supplier with id: " + supplierId);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("An error occurred while deleting the supplier with id: " + supplierId);
        }
    }

}
