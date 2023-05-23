package dat3.grocery_delivery.service;

import dat3.grocery_delivery.dto.VanResponse;
import dat3.grocery_delivery.entity.Delivery;
import dat3.grocery_delivery.entity.Van;
import dat3.grocery_delivery.repository.DeliveryRepository;
import dat3.grocery_delivery.repository.VanRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class VanService {

  private VanRepository vanRepository;
  private DeliveryRepository deliveryRepository;

  public VanService(VanRepository vanRepository, DeliveryRepository deliveryRepository) {
    this.vanRepository = vanRepository;
    this.deliveryRepository = deliveryRepository;
  }

  public List<VanResponse> getAllVans(boolean includeDetails){
    List<Van> vans = vanRepository.findAll();
    return vans.stream().map(v-> new VanResponse(v,includeDetails)).toList();
  }


  public VanResponse getVanById(int id){
    Van van = vanRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"No Van with this Id found"));
    return new VanResponse(van,true);
  }

  public String addDelivery(int vanId, int deliveryId){
    Delivery delivery =   deliveryRepository.findById(deliveryId).
            orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Delivery with id '%s' not found",deliveryId)));
    if(delivery.getVan() !=null){
      String id = Integer.toString(delivery.getVan().getId());
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,String.format("This delivery is already assigned to van '%s'",id));
    }
    Van van = vanRepository.findById(vanId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Van with id '%s' not found",vanId)));
    if(van.getDeliveries().stream().filter(d->{return d.getDeliveryDate() == delivery.getDeliveryDate();}).toList().size()>0){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"This Van already have a delivery on this date");
    };
    double deliveryTotalWeight = delivery.getProductOrders().stream().map(po -> po.getQuantity()*po.getProduct().getWeight()).mapToDouble(x->x.doubleValue()).sum();
    int deliveryTotalWeightKilos = (int) (deliveryTotalWeight /1000);
    if(deliveryTotalWeightKilos > van.getCapacity()){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Total weight for delivery exceeds capacity of Van ");
    }
    van.addDelivery(delivery);
    vanRepository.save(van);
    return "{\"status\": \"OK\"}";
  }
}
