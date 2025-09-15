package manu.m.rest.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import manu.m.rest.Model.Product;
import manu.m.rest.Model.User;
import manu.m.rest.Service.UserService;
import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("")
    public List<User> getUsersController(){
        return this.userService.getUsersService();
    }

    @GetMapping("/{id}")
    public User getUserByIdController(@PathVariable int id){
        return this.userService.getUserByIdService(id);
    }

    @PostMapping("")
    public String postUserController(@RequestBody User user){
        return this.userService.postUserService(user);
    }

    @PutMapping("/{id}")
    public String putUserController(@PathVariable int id, @RequestBody User user){
        return this.userService.putUserService(id, user);
    }

    @DeleteMapping("/{id}")
    public String deleteUserController(@PathVariable int id){
        return this.userService.deleteUserService(id);
    }

    @GetMapping("/get_product/{id}")
    public List<Product> getUserProductsController(@PathVariable int id){
        return this.userService.getUserProductsService(id);
    }

    @PutMapping("/add_product/{user_id}/{product_id}")
    public String addProductToUserController(@PathVariable int user_id, @PathVariable int product_id){
        return this.userService.addProductToUserService(user_id, product_id);
    }

    @PutMapping("/remove_product/{user_id}/{product_id}")
    public String removeProductFromUserController(@PathVariable int user_id, @PathVariable int product_id){
        return this.userService.removeProductFromUserService(user_id, product_id);
    }
    
}
