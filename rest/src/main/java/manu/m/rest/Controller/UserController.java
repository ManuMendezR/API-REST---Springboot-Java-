package manu.m.rest.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import manu.m.rest.Model.Product;
import manu.m.rest.Repo.ProductRepo;
import manu.m.rest.Model.User;
import manu.m.rest.Repo.UserRepo;
import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ProductRepo productRepo;

    @GetMapping("")
    public List<User> getUsers(){
        return this.userRepo.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id){
        return this.userRepo.findById(id).get();
    }

    @PostMapping("")
    public String postUser(@RequestBody User user){
        this.userRepo.save(user);
        return "Usuario creado.";
    }

    @PutMapping("/{id}")
    public String putUser(@PathVariable int id, @RequestBody User user){
        User updatedUser = this.userRepo.findById(id).get();
        updatedUser.setName(user.getName());
        updatedUser.setPhoneNumber(user.getPhoneNumber());
        updatedUser.setEmail(user.getEmail());
        this.userRepo.save(updatedUser);
        return "Usuario con id " + id + " actualizado.";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id){
        this.userRepo.deleteById(id);
        return "Usuario con id " + id + " eliminado";
    }

    @GetMapping("/get_product/{id}")
    public List<Product> getUserProducts(@PathVariable int id){
        User user = this.userRepo.findById(id).get();
        return user.getProducts();
    }

    @PutMapping("/add_product/{user_id}/{product_id}")
    public String addProductToUser(@PathVariable int user_id, @PathVariable int product_id){
        User user = this.userRepo.findById(user_id).get();
        Product product = this.productRepo.findById(product_id).get();
        user.enrollProduct(product);
        this.userRepo.save(user);
        return "Se ha añadido el servicio con id " + product_id + " al usuario con id " + user_id;
    }

    @PutMapping("/remove_product/{user_id}/{product_id}")
    public String removeProductFromUser(@PathVariable int user_id, @PathVariable int product_id){
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
