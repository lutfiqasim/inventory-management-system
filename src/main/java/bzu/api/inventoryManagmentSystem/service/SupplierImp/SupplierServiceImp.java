package bzu.api.inventoryManagmentSystem.service.SupplierImp;

import bzu.api.inventoryManagmentSystem.dto.CategoryDto;
import bzu.api.inventoryManagmentSystem.dto.SupplierDto;
import bzu.api.inventoryManagmentSystem.dto.SupplierPartialDto;
import bzu.api.inventoryManagmentSystem.exception.ResourceNotFoundException;
import bzu.api.inventoryManagmentSystem.model.Category;
import bzu.api.inventoryManagmentSystem.model.Supplier;
import bzu.api.inventoryManagmentSystem.repository.SupplierRepository;
import bzu.api.inventoryManagmentSystem.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImp implements SupplierService {
    private SupplierRepository supplierRepository;

    @Autowired
    public SupplierServiceImp(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public SupplierDto createSupplier(SupplierDto supplierDto) {
        Supplier newSupplier = supplierRepository.save(mapToEntity(supplierDto));
        return entityToMap(newSupplier);
    }

    @Override
    public SupplierDto getSupplierById(Long id) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() ->
                getResourceNotFoundException(id));
        return entityToMap(supplier);
    }

    @Override
    public List<SupplierDto> getAllSuppliers() {
        List<Supplier> supplierList = supplierRepository.findAll();
        return supplierList.stream().map(this::entityToMap).
                collect(Collectors.toList());

    }

    @Override
    public void updateSupplier(Long id, SupplierDto supplierDto) {
        Supplier existingSupplier = supplierRepository.findById(id).orElseThrow(() ->
                getResourceNotFoundException(id));
        existingSupplier.setPhoneNo(supplierDto.getPhoneNo());
        existingSupplier.setName(supplierDto.getName());
        existingSupplier.setEmail(supplierDto.getEmail());
        supplierRepository.save(existingSupplier);
    }


    @Override
    public void updateSupplierPartially(Long id, SupplierPartialDto dto) {
        Supplier existingSupplier = supplierRepository.findById(id).orElseThrow(() ->
                getResourceNotFoundException(id));
        if (dto.getEmail() != null) {
            existingSupplier.setEmail(dto.getEmail());
        }
        if (dto.getName() != null) {
            existingSupplier.setName(dto.getName());
        }
        if (dto.getPhoneNo() != null) {
            existingSupplier.setPhoneNo(dto.getPhoneNo());
        }
        supplierRepository.save(existingSupplier);
    }

    @Override
    public void deleteSupplier(Long id) {
        if (!supplierRepository.existsById(id)) {
            throw getResourceNotFoundException(id);
        }
        supplierRepository.deleteById(id);
    }

    private static ResourceNotFoundException getResourceNotFoundException(Long id) {
        return new ResourceNotFoundException("Supplier", "id", String.valueOf(id));
    }

    private SupplierDto entityToMap(Supplier supplier) {
        SupplierDto dto = new SupplierDto();
        dto.setId(supplier.getId());
        dto.setName(supplier.getName());
        dto.setEmail(supplier.getEmail());
        dto.setPhoneNo(supplier.getPhoneNo());
        return dto;
    }

    private Supplier mapToEntity(SupplierDto dto) {
        Supplier supplier = new Supplier();
        supplier.setName(dto.getName());
        supplier.setEmail(dto.getEmail());
        supplier.setPhoneNo(dto.getPhoneNo());
        return supplier;
    }
}
