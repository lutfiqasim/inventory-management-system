package bzu.api.inventoryManagmentSystem.service;

import bzu.api.inventoryManagmentSystem.dto.CategoryDto;
import bzu.api.inventoryManagmentSystem.dto.CategoryPartialDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto getCategoryById(Long id);

    List<CategoryDto> getAllCategories();

    void updateCategory(CategoryDto category, long id);

    void updateCategoryPartially(CategoryPartialDto dto, Long id);

    void deleteCategory(long id);


    List<CategoryDto> getAllCategoriesSortByName();
}
