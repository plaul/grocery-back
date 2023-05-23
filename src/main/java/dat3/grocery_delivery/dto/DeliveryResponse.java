package dat3.grocery_delivery.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.grocery_delivery.entity.Delivery;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeliveryResponse {
  int deliveryId;
  LocalDate deliveryDate;
  String fromWareHouse;
  String destination;
  Double totalWeight;
  Double totalPrice;
  List<ProductResponse> products;

  public DeliveryResponse(Delivery delivery, boolean includeProductOrders) {
    this.deliveryId = delivery.getId();
    this.fromWareHouse = delivery.getFromWareHouse();
    this.destination = delivery.getDestination();
    this.deliveryDate = delivery.getDeliveryDate();
    if(includeProductOrders){
      products = delivery.getProductOrders().stream().map((p) -> new ProductResponse(p.getProduct(),p.getQuantity(),p.getId())).toList();
    }
  }
  public DeliveryResponse(Delivery delivery, boolean includeProductOrders,boolean includeTotals) {
    this(delivery,includeProductOrders);
    this.totalPrice = delivery.getProductOrders().stream().map(p->p.getQuantity()*p.getProduct().getPrice()).mapToDouble(n->n.doubleValue()).sum();
    this.totalWeight = (delivery.getProductOrders().stream().map(p->p.getQuantity()*p.getProduct().getWeight()).mapToDouble(n->n.doubleValue()).sum())/1000;
  }
}
