package manu.m.rest.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import manu.m.rest.Model.Product;
import manu.m.rest.Repo.ProductRepo;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;

    public List<Product> getProductsService(){
        return this.productRepo.findAll();
    }

    public Product getProductByIdService(int id){
        return this.productRepo.findById(id).get();
    }

    public String postProductService(Product product){
        this.productRepo.save(product);
        return "Servicio creado";
    }

    public String putProductService(int id, Product product){
        Product updatedProduct = this.productRepo.findById(id).get();
        updatedProduct.setPrice(product.getPrice());
        updatedProduct.setProductName(product.getProductName());
        this.productRepo.save(updatedProduct);
        return "Servicio con id " + id + " actualizado";
    }

    public String deleteProductService(int id){
        this.productRepo.deleteById(id);
        return "Servicio con id " + id + " eliminado";
    }
}
