package bzu.api.inventoryManagmentSystem.dto;

import bzu.api.inventoryManagmentSystem.model.Category;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CategoryDto {
    private Long id;
    @NotNull
    @Size(min = 3, max = 50)
    private String name;
    @Size(min = 0, max = 100)
    private String description;

    public CategoryDto() {
    }

    public CategoryDto(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category toCategory() {
        Category category = new Category();
        category.setId(this.getId());
        category.setName(this.getName());
        category.setDescription(this.getDescription());
        return category;
    }

    @Override
    public String toString() {
        return "CategoryDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
