package bzu.api.inventoryManagmentSystem.repository;

import bzu.api.inventoryManagmentSystem.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
