package bzu.api.inventoryManagmentSystem.service;

import bzu.api.inventoryManagmentSystem.dto.SupplierDto;
import bzu.api.inventoryManagmentSystem.dto.SupplierPartialDto;

import java.util.List;

public interface SupplierService {
    SupplierDto createSupplier(SupplierDto supplierDto);

    SupplierDto getSupplierById(Long id);

    List<SupplierDto> getAllSuppliers();

    void updateSupplier(Long id, SupplierDto supplierDto);

    void updateSupplierPartially(Long id, SupplierPartialDto dto);

    void deleteSupplier(Long id);
}
