package bzu.api.inventoryManagmentSystem.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SupplierDto {
    private Long id;
    @NotNull
    @Size(min = 3, max = 50)

    private String name;
    @NotNull
    @Size(min = 10, max = 50)
    private String email;
    @NotNull
    @Size(min = 10, max = 15)
    private String phoneNo;

    public SupplierDto() {
    }

    public SupplierDto(Long id, String name, String email, String phoneNo) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Override
    public String toString() {
        return "SupplierDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                '}';
    }
}
