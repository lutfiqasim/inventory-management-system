package bzu.api.inventoryManagmentSystem.controller;

import bzu.api.inventoryManagmentSystem.controller.handler.ValidationExceptionHandler;
import bzu.api.inventoryManagmentSystem.dto.CategoryDto;
import bzu.api.inventoryManagmentSystem.dto.CategoryPartialDto;
import bzu.api.inventoryManagmentSystem.exception.BadRequestException;
import bzu.api.inventoryManagmentSystem.exception.ResourceNotFoundException;
import bzu.api.inventoryManagmentSystem.service.CategoryImp.CategoryServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryServiceImp categoryService;

    @Autowired
    public CategoryController(CategoryServiceImp categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAllCategories(@RequestParam(name = "orderByName", required = false, defaultValue = "false") boolean sortByName) {
        try {
            List<CategoryDto> categoryDtos;
            if (sortByName) {
                categoryDtos = categoryService.getAllCategoriesSortByName();
            } else {
                categoryDtos = categoryService.getAllCategories();

            }
            return ResponseEntity.ok(categoryDtos);
        } catch (Exception e) {
            // Return an internal server error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while retrieving categories");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> addNewCategory(@Valid @RequestBody CategoryDto categoryDto, BindingResult result) {
        if (result.hasErrors()) {
            return ValidationExceptionHandler.validate(result, "Product");
        }
        try {
            CategoryDto createdCategory = categoryService.createCategory(categoryDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("An error occurred while creating category");
        }
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long categoryId) {
        try {
            CategoryDto categoryDto = categoryService.getCategoryById(categoryId);
            return ResponseEntity.ok(categoryDto);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("An error occurred while retrieving categories");
        }
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<?> updateExistingCategory(@PathVariable Long categoryId, @Valid @RequestBody CategoryDto categoryDto, BindingResult result) {
        if (result.hasErrors()) {
            return ValidationExceptionHandler.validate(result, "Product");
        }
        try {
            categoryService.updateCategory(categoryDto, categoryId);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("An error occurred while updating category");
        }
        return ResponseEntity.ok("Category with id:" + categoryId + ", updated successfully");
    }

    @PatchMapping("/{categoryId}")
    public ResponseEntity<?> updateCategoryPartially(@PathVariable Long categoryId, @Valid @RequestBody CategoryPartialDto categoryDto, BindingResult result) {
        if (result.hasErrors()) {
            return ValidationExceptionHandler.validate(result, "Product");
        }
        try {
            categoryService.updateCategoryPartially(categoryDto, categoryId);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("An error occurred while updating category");
        }
        return ResponseEntity.ok("Category with id:" + categoryId + ", partially updated successfully");
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable Long categoryId) {
        try {
            categoryService.deleteCategory(categoryId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).
                    body("Successfully deleted category with id: " + categoryId);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("An error occurred while deleting the category with id: " + categoryId);
        }
    }
    //    TODO: get all products of this category

}
