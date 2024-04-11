package bzu.api.inventoryManagmentSystem.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SupplierPartialDto {
    @Size(min = 3, max = 50)

    private String name;
    @Size(min = 10, max = 50)
    private String email;
    @Size(min = 10, max = 15)
    private String phoneNo;

    public SupplierPartialDto() {
    }

    public SupplierPartialDto(String name, String email, String phoneNo) {
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
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
        return "SupplierPartialDto{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                '}';
    }
}
