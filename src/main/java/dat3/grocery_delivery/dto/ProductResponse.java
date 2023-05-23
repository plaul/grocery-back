package dat3.grocery_delivery.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.grocery_delivery.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse {
  int id;
  String name;
  Integer weight;
  Double price;
  Integer quantity;
  Integer productOrderId;

  public ProductResponse(Product product) {
    this.id = product.getId();
    this.name = product.getName();
    this.weight = product.getWeight();
    this.price = product.getPrice();
  }
  public ProductResponse(Product product, int quantity,int productOrderId) {
    this.id = product.getId();
    this.name = product.getName();
    this.weight = product.getWeight();
    this.price = product.getPrice();
    this.quantity = quantity;
    this.productOrderId = productOrderId;
  }
}
