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
import manu.m.rest.Service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("")
    public List<Product> getProductsController(){
        return this.productService.getProductsService();
    }

    @GetMapping("/{id}")
    public Product getProductByIdController(@PathVariable int id){
        return this.productService.getProductByIdService(id);
    }

    @PostMapping("")
    public String postProductController(@RequestBody Product product){
        return this.productService.postProductService(product);
    }

    @PutMapping("/{id}")
    public String putProductController(@PathVariable int id, @RequestBody Product product){
        return this.productService.putProductService(id, product);
    }

    @DeleteMapping("/{id}")
    public String deleteProductController(@PathVariable int id){
        return this.productService.deleteProductService(id);
    }

}
