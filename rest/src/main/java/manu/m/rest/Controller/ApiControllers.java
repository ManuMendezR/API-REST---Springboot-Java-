package manu.m.rest.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import manu.m.rest.Model.User;
import manu.m.rest.Repo.UserRepo;
import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class ApiControllers {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/")
    public String getPage(){
        return "Welcome";
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return this.userRepo.findAll();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable int id){
        return this.userRepo.findById(id).get();
    }

    @PostMapping("/user/create")
    public String postUser(@RequestBody User user){
        this.userRepo.save(user);
        return "Usuario creado.";
    }

    @PutMapping("/user/update/{id}")
    public String putUser(@PathVariable int id, @RequestBody User user){
        User updatedUser = this.userRepo.findById(id).get();
        updatedUser.setName(user.getName());
        updatedUser.setPhoneNumber(user.getPhoneNumber());
        updatedUser.setEmail(user.getEmail());
        this.userRepo.save(updatedUser);
        return "Usuario con id " + id + " actualizado.";
    }

    @DeleteMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable int id){
        this.userRepo.deleteById(id);
        return "Usuario con id " + id + " eliminado";
    }
}
