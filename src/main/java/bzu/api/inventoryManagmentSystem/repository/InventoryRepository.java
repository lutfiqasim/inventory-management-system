package bzu.api.inventoryManagmentSystem.repository;

import bzu.api.inventoryManagmentSystem.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
