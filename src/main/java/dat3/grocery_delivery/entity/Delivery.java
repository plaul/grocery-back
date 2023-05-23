package dat3.grocery_delivery.entity;

import dat3.grocery_delivery.dto.DeliveryRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Delivery {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int id;

  @ManyToOne
  Van van;

  @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL)
  List<ProductOrder> productOrders = new ArrayList<>();

  public void addProductOrder(ProductOrder po){
    productOrders.add(po);
    po.setDelivery(this);
  }
  public Delivery(DeliveryRequest request){
    this.deliveryDate = request.getDeliveryDate();
    this.destination = request.getDestination();
    this.fromWareHouse = request.getFromWareHouse();
  }

  LocalDate deliveryDate;

  @Column(length = 15)
  String fromWareHouse;

  @Column(length = 100)
  String destination;
}
