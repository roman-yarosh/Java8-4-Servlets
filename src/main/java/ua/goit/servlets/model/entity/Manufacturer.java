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
    @Column(name = "MANUFACTURER_ID", nullable = false, updatable = false, unique = true, columnDefinition = "BINARY(16)")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID Id;

    @Column(name = "MANUFACTURER_NAME", unique = true)
    private String Name;

    @OneToMany(mappedBy = "manufacturer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

    public Manufacturer() {
    }

    public Manufacturer(UUID Id, String Name) {
        this.Id = Id;
        this.Name = Name;
    }

    public Manufacturer(String Name) {
        this.Name = Name;
    }

    public Manufacturer(String Name, List<Product> products) {
        this.Name = Name;
        this.products = products;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Manufacturer{");
        sb.append("Id=").append(Id);
        sb.append(", Name='").append(Name).append('\'');
        sb.append(", products=").append(products);
        sb.append('}');
        return sb.toString();
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID manufacturerId) {
        this.Id = manufacturerId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String manufacturerName) {
        this.Name = manufacturerName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
