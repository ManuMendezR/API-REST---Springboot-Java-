package manu.m.rest.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import manu.m.rest.Model.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

}