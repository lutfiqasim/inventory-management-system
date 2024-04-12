package bzu.api.inventoryManagmentSystem.model;

import bzu.api.inventoryManagmentSystem.dto.CategoryDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "category_title", nullable = false)
    private String name;

    @Column(name = "category_description")
    private String description;

    // No-argument constructor for JPA
    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return name.equals(category.name);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public CategoryDto toCategoryDto() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(this.name);
        categoryDto.setDescription(this.description);
        categoryDto.setId(this.id);
        return categoryDto;
    }

}
