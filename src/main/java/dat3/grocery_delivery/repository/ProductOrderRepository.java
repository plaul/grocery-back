package dat3.grocery_delivery.repository;

import dat3.grocery_delivery.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOrderRepository extends JpaRepository<ProductOrder,Integer> {

}
