package dat3.grocery_delivery.api;

import dat3.grocery_delivery.dto.DeliveryRequest;
import dat3.grocery_delivery.dto.DeliveryResponse;
import dat3.grocery_delivery.dto.ProductOrderRequest;
import dat3.grocery_delivery.dto.ProductResponse;
import dat3.grocery_delivery.service.DeliveryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/deliveries")
@CrossOrigin
public class DeliveryController {

  DeliveryService deliveryService;

  public DeliveryController(DeliveryService deliveryService) {
    this.deliveryService = deliveryService;
  }

  @GetMapping("/{id}")
  public DeliveryResponse getDelivery(@PathVariable int id){
    return deliveryService.getDelivery(id);
  }

  @GetMapping
  public List<DeliveryResponse> getDeliveries(){
    return deliveryService.getDeliveries();
  }

  @PostMapping
  public DeliveryResponse createDelivery(@RequestBody DeliveryRequest body){
    return deliveryService.createDelivery(body);
  }
  @PostMapping("/product")
  public DeliveryResponse addProductToDelivery(@RequestBody  ProductOrderRequest body){
    return deliveryService.addProductOrder(body);
  }

  @DeleteMapping("/product-order/{id}")
  public List<ProductResponse> deleteProductOrderLine(@PathVariable int id){
    return deliveryService.deleteProductOrderLine(id);
  }



}
