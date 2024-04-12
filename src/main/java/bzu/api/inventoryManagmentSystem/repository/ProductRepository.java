package bzu.api.inventoryManagmentSystem.repository;

import bzu.api.inventoryManagmentSystem.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
