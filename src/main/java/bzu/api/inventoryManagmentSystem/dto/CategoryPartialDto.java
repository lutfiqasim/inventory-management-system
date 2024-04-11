package bzu.api.inventoryManagmentSystem.dto;

import jakarta.validation.constraints.Size;

public class CategoryPartialDto {
    @Size(min = 3, max = 100)
    private String name;
    @Size(min = 0, max = 100)
    private String description;

    public CategoryPartialDto() {
    }

    public CategoryPartialDto(String name, String description) {
        this.name = name;
        this.description = description;
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
    public String toString() {
        return "CategoryPartialDto{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
