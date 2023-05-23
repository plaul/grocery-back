package dat3.grocery_delivery.entity;

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
public class Van {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int id;

  @OneToMany(mappedBy = "van")
  List<Delivery> deliveries = new ArrayList<>();

  public void addDelivery(Delivery delivery){
    deliveries.add(delivery);
    delivery.setVan(this);
  }

  @Column(length = 50)
  String brand;

  @Column(length = 50)
  String model;

  int capacity;
}
