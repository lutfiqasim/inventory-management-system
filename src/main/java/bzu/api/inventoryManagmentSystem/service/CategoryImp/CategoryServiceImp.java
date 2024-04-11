package bzu.api.inventoryManagmentSystem.service.CategoryImp;

import bzu.api.inventoryManagmentSystem.dto.CategoryDto;
import bzu.api.inventoryManagmentSystem.dto.CategoryPartialDto;
import bzu.api.inventoryManagmentSystem.exception.ResourceNotFoundException;
import bzu.api.inventoryManagmentSystem.model.Category;
import bzu.api.inventoryManagmentSystem.repository.CategoryRepository;
import bzu.api.inventoryManagmentSystem.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImp implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

        Category newCategory = categoryRepository.save(mapToEntity(categoryDto));
        //convert to DTO
        return entityToMap(newCategory);
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() ->
                getResourceNotFoundException(id));
        return entityToMap(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(this::entityToMap)
                .collect(Collectors.toList());
    }

    @Override
    public void updateCategory(CategoryDto categoryDto, long id) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> getResourceNotFoundException(id));

        // Update the existing category with new data
        existingCategory.setName(categoryDto.getName());
        existingCategory.setDescription(categoryDto.getDescription());

        categoryRepository.save(existingCategory);
    }

    @Override
    public void updateCategoryPartially(CategoryPartialDto dto, Long id) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> getResourceNotFoundException(id));

        //update existing category with provided data if exists
        if (dto.getName() != null) {
            existingCategory.setName(dto.getName());
        }
        if (dto.getDescription() != null) {
            existingCategory.setDescription(dto.getDescription());
        }
        categoryRepository.save(existingCategory);
    }


    @Override
    public void deleteCategory(long id) {
        if (!categoryRepository.existsById(id))
            throw getResourceNotFoundException(id);
        categoryRepository.deleteById(id);
    }

    @Override
    public List<CategoryDto> getAllCategoriesSortByName() {
        List<Category> categoryList = categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        return categoryList.stream().map(this::entityToMap).
                collect(Collectors.toList());
    }

    private static ResourceNotFoundException getResourceNotFoundException(Long id) {
        return new ResourceNotFoundException("Category", "id", String.valueOf(id));
    }

    private CategoryDto entityToMap(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        return dto;
    }

    private Category mapToEntity(CategoryDto dto) {
        Category category = new Category();
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        return category;
    }
}
