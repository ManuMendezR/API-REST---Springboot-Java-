package manu.m.rest.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import manu.m.rest.Model.Service;

public interface ServiceRepo extends JpaRepository<Service, Integer> {

}