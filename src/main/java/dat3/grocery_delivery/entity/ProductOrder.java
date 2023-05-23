package dat3.grocery_delivery.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrder {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int id;  //To avoid the "complexity" of a combined key

  @ManyToOne(cascade = CascadeType.MERGE)
  Product product;

  @ManyToOne(cascade = CascadeType.MERGE)
  Delivery delivery;

  int quantity;

  public ProductOrder(Delivery delivery, Product product,int quantity) {
    this.delivery = delivery;
    this.product = product;
    this.quantity = quantity;
  }
}
