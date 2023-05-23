package dat3.grocery_delivery.api;

import dat3.grocery_delivery.dto.ProductRequest;
import dat3.grocery_delivery.dto.ProductResponse;
import dat3.grocery_delivery.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
@CrossOrigin
public class ProductController {

  ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  public List<ProductResponse> getAll(){
    return productService.allProducts();
  }

  @GetMapping("/id/{id}")
  public ProductResponse findById(@PathVariable int id){
    return productService.findById(id);
  }
  @GetMapping("/name/{name}")
  public ProductResponse findById(@PathVariable String name){
    return productService.findByName(name);
  }

  @PostMapping
  public ProductResponse addProduct(@RequestBody ProductRequest body){
    ProductResponse response = productService.addProduct(body);
    return response;

  }

}
