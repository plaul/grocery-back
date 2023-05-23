package dat3.grocery_delivery.repository;

import dat3.grocery_delivery.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Integer> {
  boolean existsByName(String name);
  Optional<Product> findByName(String name);
}
