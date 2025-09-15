package manu.m.rest.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import manu.m.rest.Model.Product;
import manu.m.rest.Repo.ProductRepo;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductRepo productRepo;

    @GetMapping("")
    public List<Product> getProducts(){
        return this.productRepo.findAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id){
        return this.productRepo.findById(id).get();
    }

    @PostMapping("")
    public String postProduct(@RequestBody Product product){
        this.productRepo.save(product);
        return "Servicio creado";
    }

    @PutMapping("/{id}")
    public String putProduct(@PathVariable int id, @RequestBody Product product){
        Product updatedProduct = this.productRepo.findById(id).get();
        updatedProduct.setPrice(product.getPrice());
        updatedProduct.setProductName(product.getProductName());
        this.productRepo.save(updatedProduct);
        return "Servicio con id " + id + " actualizado";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id){
        this.productRepo.deleteById(id);
        return "Servicio con id " + id + " eliminado";
    }

}
