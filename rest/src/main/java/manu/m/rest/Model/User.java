package manu.m.rest.Model;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private String phoneNumber;

    @Column
    private String email;

    @ManyToMany
    @JoinTable(name = "userHasProduct", 
      joinColumns = @JoinColumn(name = "userId", referencedColumnName = "id"), 
      inverseJoinColumns = @JoinColumn(name = "productId", referencedColumnName = "id"))
    private List<Product> products;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Product> getProducts(){
        return products;
    }

    public void enrollProduct(Product product){
        this.products.add(product);
    }

    public void removeProduct(int id){
        this.products.removeIf(servicio -> servicio.getId() == id);
    }
    
}
