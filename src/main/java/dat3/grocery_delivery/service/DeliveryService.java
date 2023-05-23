package dat3.grocery_delivery.service;

import dat3.grocery_delivery.dto.DeliveryRequest;
import dat3.grocery_delivery.dto.DeliveryResponse;
import dat3.grocery_delivery.dto.ProductOrderRequest;
import dat3.grocery_delivery.dto.ProductResponse;
import dat3.grocery_delivery.entity.Delivery;
import dat3.grocery_delivery.entity.Product;
import dat3.grocery_delivery.entity.ProductOrder;
import dat3.grocery_delivery.repository.DeliveryRepository;
import dat3.grocery_delivery.repository.ProductOrderRepository;
import dat3.grocery_delivery.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DeliveryService {
  DeliveryRepository deliveryRepository;
  ProductRepository productRepository;
  ProductOrderRepository productOrderRepository;

  public DeliveryService(DeliveryRepository deliveryRepository, ProductRepository productRepository, ProductOrderRepository productOrderRepository) {
    this.deliveryRepository = deliveryRepository;
    this.productRepository = productRepository;
    this.productOrderRepository = productOrderRepository;
  }

  public DeliveryResponse getDelivery(int id){
    Delivery delivery = deliveryRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Delivery not found"));
    return new DeliveryResponse(delivery,true);
  }

  public DeliveryResponse createDelivery(DeliveryRequest body){
    Delivery delivery = new Delivery(body);
    delivery = deliveryRepository.save(delivery);
    return new DeliveryResponse(deliveryRepository.save(delivery),false);
  }

  public DeliveryResponse addProductOrder(ProductOrderRequest prodOrderRequest){
    Delivery delivery = deliveryRepository.findById(prodOrderRequest.getDeliveryId())
            .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"No Delivery with this id found"));
    Product product = productRepository.findById(prodOrderRequest.getProductId())
            .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "No order with this id found"));
    ProductOrder po = new ProductOrder(delivery,product,prodOrderRequest.getQuantity());
    delivery.addProductOrder(po);
    delivery = deliveryRepository.save(delivery);
    return new DeliveryResponse(delivery,true);
  }

  public List<ProductResponse> deleteProductOrderLine(int id){
    ProductOrder prodOrder = productOrderRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Not found"));
    productOrderRepository.deleteById(id);
    List<ProductOrder> productOrders = prodOrder.getDelivery().getProductOrders();
    List<ProductResponse> orderLines = productOrders.stream().map((p) -> new ProductResponse(p.getProduct(),p.getQuantity(),p.getId())).toList();
    return orderLines;
  }

  public List<DeliveryResponse> getDeliveries() {
    List<Delivery> deliveries = deliveryRepository.findAll();
    return deliveries.stream().map(delivery -> new DeliveryResponse(delivery,false,true)).toList();
  }
}
