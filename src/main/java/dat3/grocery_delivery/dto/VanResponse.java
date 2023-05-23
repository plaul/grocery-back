package dat3.grocery_delivery.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.grocery_delivery.entity.Van;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VanResponse {
  int vanId;
  String brand;
  String model;
  int capacity;
  List<DeliveryResponse> deliveryResponses;

  public VanResponse(Van van,boolean includeDeliveries) {
    this.vanId = van.getId();
    this.brand = van.getBrand();
    this.model = van.getModel();
    this.capacity = van.getCapacity();
    if(includeDeliveries){
      this.deliveryResponses = van.getDeliveries().stream().map(d->new DeliveryResponse(d,false,true)).toList();
    }
  }
}
