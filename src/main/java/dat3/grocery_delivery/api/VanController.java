package dat3.grocery_delivery.api;

import dat3.grocery_delivery.dto.VanResponse;
import dat3.grocery_delivery.service.VanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/vans")
@CrossOrigin
public class VanController {
  VanService vanService;

  public VanController(VanService vanService) {
    this.vanService = vanService;
  }

  @GetMapping
  List<VanResponse> getAllVans(@RequestParam(value= "addDeliveries", required = false,defaultValue = "false") boolean addDeliveries){
    return vanService.getAllVans(addDeliveries);
  }
  @GetMapping("/{id}")
  public VanResponse getVanById(@PathVariable int id){
    return vanService.getVanById(id);
  }

  @PostMapping("/{vanId}/{deliveryId}")
  ResponseEntity<String> addDelivery(@PathVariable int vanId, @PathVariable int deliveryId){
    String response = vanService.addDelivery(vanId,deliveryId);
    return new ResponseEntity<>(response,HttpStatus.OK);
  }
}
