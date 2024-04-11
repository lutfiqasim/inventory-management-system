package bzu.api.inventoryManagmentSystem.controller;

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
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryServiceImp categoryService;
//    private final ProductServiceImp productServiceImp;

    @Autowired
    public CategoryController(CategoryServiceImp categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAllCategories(@RequestParam(name = "orderByName", required = false, defaultValue = "false") boolean sortByName) {
        try {
            List<CategoryDto> categoryDtos;
            if (sortByName) {
                System.out.println("HERE");
                categoryDtos = categoryService.getAllCategoriesSortByName();
            } else {
                categoryDtos = categoryService.getAllCategories();

            }
            System.out.println("-----------------------");
            
            for (CategoryDto c : categoryDtos) {
                System.out.println(c.toString());
            }
            System.out.println("-----------------------");
            return ResponseEntity.ok(categoryDtos);
        } catch (Exception e) {
            // Return an internal server error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while retrieving categories");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> addNewCategory(@Valid @RequestBody CategoryDto categoryDto, BindingResult result) {
        //checks for erros based on the dto validation
//        if (result.hasErrors()) {
//            Map<String, Object> errorResponse = new HashMap<>();
//            errorResponse.put("message", "validation failed");
//            errorResponse.put("errors", result.getAllErrors());
//            return ResponseEntity.badRequest().body(errorResponse);
//        }
        if (result.hasErrors()) {
            Map<String, Object> errorMap = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().
                    body(new BadRequestException("Validation failed", "Category", errorMap.toString()).toString());
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
            Map<String, Object> errorMap = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().
                    body(new BadRequestException("Validation failed", "Category", errorMap.toString()).toString());
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
            Map<String, Object> errorMap = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().
                    body(new BadRequestException("Validation failed", "Category", errorMap.toString()).toString());
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
