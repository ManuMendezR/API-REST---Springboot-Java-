package manu.m.rest.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import manu.m.rest.Model.Product;
import manu.m.rest.Model.User;
import manu.m.rest.Repo.ProductRepo;
import manu.m.rest.Repo.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ProductRepo productRepo;

    public List<User> getUsersService(){
        return this.userRepo.findAll();
    }
    
    public User getUserByIdService(int id){
        return this.userRepo.findById(id).get();
    }

    public String postUserService(User user){
        this.userRepo.save(user);
        return "Usuario creado.";
    }

    public String putUserService(int id, User user){
        User updatedUser = this.userRepo.findById(id).get();
        updatedUser.setName(user.getName());
        updatedUser.setPhoneNumber(user.getPhoneNumber());
        updatedUser.setEmail(user.getEmail());
        this.userRepo.save(updatedUser);
        return "Usuario con id " + id + " actualizado.";
    }

    public String deleteUserService(int id){
        this.userRepo.deleteById(id);
        return "Usuario con id " + id + " eliminado";
    }

    public List<Product> getUserProductsService(@PathVariable int id){
        User user = this.userRepo.findById(id).get();
        return user.getProducts();
    }

    public String addProductToUserService(int user_id, int product_id){
        User user = this.userRepo.findById(user_id).get();
        Product product = this.productRepo.findById(product_id).get();
        user.enrollProduct(product);
        this.userRepo.save(user);
        return "Se ha añadido el servicio con id " + product_id + " al usuario con id " + user_id;
    }

    public String removeProductFromUserService(int user_id, int product_id){
        User user = this.userRepo.findById(user_id).get();
        for(Product servicio : user.getProducts()){
            if(servicio.getId() == product_id){
                user.removeProduct(product_id);
                this.userRepo.save(user);
            }
        }
        return "Se ha eliminado el servicio con id " + product_id + " del usuario con id " + user_id;
    }
}
