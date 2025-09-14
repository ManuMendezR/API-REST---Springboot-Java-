package manu.m.rest.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import manu.m.rest.Model.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
