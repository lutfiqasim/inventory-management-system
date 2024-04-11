package bzu.api.inventoryManagmentSystem.repository;

import bzu.api.inventoryManagmentSystem.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
