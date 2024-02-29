package ecom.services.productmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> addNewProduct(Product product) {

        return Optional.of(productRepository.save(product));
    }

    public Optional<Product> deleteProductByObj(Product product) {
        productRepository.delete(product);
        if (productRepository.findById(product.getId()).isEmpty())
            return Optional.of(product);
        else
            return null;
    }

    public Product updateProductByObj(Product product) {

        return productRepository.save(product);
        
    }

    public Optional<Product> getProductById(long userId) {
        return productRepository.findById(userId);
    }
}
