package manu.m.rest.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import manu.m.rest.Model.Service;
import manu.m.rest.Repo.ServiceRepo;
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
    private ServiceRepo serviceRepo;

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

    @GetMapping("/get_service/{id}")
    public List<Service> getUserServices(@PathVariable int id){
        User user = this.userRepo.findById(id).get();
        return user.getServices();
    }

    @PutMapping("/add_service/{user_id}/{service_id}")
    public String addServiceToUser(@PathVariable int user_id, @PathVariable int service_id){
        User user = this.userRepo.findById(user_id).get();
        Service service = this.serviceRepo.findById(service_id).get();
        user.enrollService(service);
        this.userRepo.save(user);
        return "Se ha añadido el servicio con id " + service_id + " al usuario con id " + user_id;
    }

    @PutMapping("/remove_service/{user_id}/{service_id}")
    public String removeServiceFromUser(@PathVariable int user_id, @PathVariable int service_id){
        User user = this.userRepo.findById(user_id).get();
        for(Service servicio : user.getServices()){
            if(servicio.getId() == service_id){
                user.removeService(service_id);
                this.userRepo.save(user);
            }
        }
        return "Se ha eliminado el servicio con id " + service_id + " del usuario con id " + user_id;
    }
    
}
