package ua.goit.servlets.model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "manufacturers")
public class Manufacturer {

    @Id
    @Column(name = "MANUFACTURER_ID", nullable = false, updatable = false, unique = true)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID manufacturerId;

    @Column(name = "MANUFACTURER_NAME", unique = true)
    private String manufacturerName;

    @OneToMany(mappedBy = "manufacturer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

    public Manufacturer() {
    }

    public Manufacturer(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public Manufacturer(String manufacturerName, List<Product> products) {
        this.manufacturerName = manufacturerName;
        this.products = products;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Manufacturer{");
        sb.append("manufacturerId=").append(manufacturerId);
        sb.append(", manufacturerName='").append(manufacturerName).append('\'');
        sb.append(", products=").append(products);
        sb.append('}');
        return sb.toString();
    }

    public UUID getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(UUID manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
