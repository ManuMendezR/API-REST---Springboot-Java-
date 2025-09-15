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
import manu.m.rest.Model.Service;
import manu.m.rest.Repo.ServiceRepo;

@RestController
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    ServiceRepo serviceRepo;

    @GetMapping("")
    public List<Service> getServices(){
        return this.serviceRepo.findAll();
    }

    @GetMapping("/{id}")
    public Service getServiceById(@PathVariable int id){
        return this.serviceRepo.findById(id).get();
    }

    @PostMapping("")
    public String postService(@RequestBody Service service){
        this.serviceRepo.save(service);
        return "Servicio creado";
    }

    @PutMapping("/{id}")
    public String putService(@PathVariable int id, @RequestBody Service service){
        Service updatedService = this.serviceRepo.findById(id).get();
        updatedService.setPrice(service.getPrice());
        updatedService.setServiceName(service.getServiceName());
        this.serviceRepo.save(updatedService);
        return "Servicio con id " + id + " actualizado";
    }

    @DeleteMapping("/{id}")
    public String deleteService(@PathVariable int id){
        this.serviceRepo.deleteById(id);
        return "Servicio con id " + id + " eliminado";
    }

}
