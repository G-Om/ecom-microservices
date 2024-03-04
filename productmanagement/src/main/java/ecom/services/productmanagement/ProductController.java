package ecom.services.productmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/services/api/pm/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public List<Product> getProductList(){
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public Optional<Product> getProduct(@RequestParam long userId){

        return productService.getProductById(userId);
    }

    @PostMapping
    public Optional<Product> addNewProduct(@RequestBody Product product) {
        return productService.addNewProduct(product);
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        return productService.updateProductByObj(product);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProduct(@RequestBody Product product) {
        productService.deleteProductByObj(product);
        return new ResponseEntity<>("Deleted product successfully!", HttpStatus.OK);

    }
    @GetMapping("/quantity")
    public Integer getQuantity(@RequestParam Long productId) {

       Optional<Product> p= productService.getProductById(productId);
       if(p.isPresent())
           return p.get().getQuantity();
       return 0;
    }
    @GetMapping("/quantity/{productId}")
    public void updateProductQuantity(@PathVariable Long productId, @RequestParam Integer quantity) {
        Product oldProduct = productService.getProductById(productId).get();
        oldProduct.setQuantity(oldProduct.getQuantity() - quantity);
        productService.updateProductByObj(oldProduct);

    }


}















