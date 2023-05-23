package dat3.grocery_delivery.service;

import dat3.grocery_delivery.dto.ProductRequest;
import dat3.grocery_delivery.dto.ProductResponse;
import dat3.grocery_delivery.entity.Product;
import dat3.grocery_delivery.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductService {
  ProductRepository productRepository;


  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public ProductResponse addProduct(ProductRequest productRequest){
    if(productRepository.existsByName(productRequest.getName())){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Product with this name already exists");
    }
    Product product = new Product(productRequest);
    product =productRepository.save(product);
    ProductResponse res = new ProductResponse(product);
    return res;
  }

  //TODO --> Refactor to use pagination
  public List<ProductResponse> allProducts(){
    List<Product> allProducts = productRepository.findAll();
    return allProducts.stream().map((product -> new ProductResponse(product))).toList();
  }

  public ProductResponse findById(int id){
    Product p = productRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"No product with this Id found"));
    return new ProductResponse(p);
  }
  public ProductResponse findByName(String name){
    Product p = productRepository.findByName(name).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"No product with this Name found"));
    return new ProductResponse(p);
  }
}
