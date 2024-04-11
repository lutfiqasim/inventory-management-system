package bzu.api.inventoryManagmentSystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "supplier_name", nullable = false)
    private String name;
    @Column(name = "supplier_email", nullable = false)
    @Size(min = 10, max = 50)
    private String email;
    @Column(nullable = false)
    @Size(min = 10, max = 15)
    private String phoneNo;

    public Supplier() {
    }

    public Supplier(String name, String email, String phoneNo) {
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
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
        return "Supplier{" +
                "supplier_id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                '}';
    }
}
