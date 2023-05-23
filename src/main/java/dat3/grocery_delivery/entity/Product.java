package dat3.grocery_delivery.entity;

import dat3.grocery_delivery.dto.ProductRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int id;

  @Column(length = 30, unique = true)
  String name;


  double price;
  int weight;

  @OneToMany(mappedBy = "product")
  List<ProductOrder> productOrders = new ArrayList<>();

  public Product(ProductRequest p) {
    this.name = p.getName();
    this.price = p.getPrice();
    this.weight = p.getWeight();
  }

  public void addDProductOrder(ProductOrder po) {
    productOrders.add(po);
    po.setProduct(this);
  }
}
