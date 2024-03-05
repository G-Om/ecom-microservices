package ecom.services.cartmanagement;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CartService {

    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private CartRepository cartRepository;

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    public Optional<Cart> getCartByCartId(Long cartId) {
        return cartRepository.findById(cartId);
    }

    public Cart addToCart(Long userId, List<Long> products) {
        Cart currentCart = cartRepository.findByUserId(userId);
        if (currentCart == null)
            return null;

        // Microservices Call
        //TODO: check availability of the products specified in the products list
        List<Integer> quantities =
        products.stream().map(
                (productId)-> {
                    // Make a call to productmanagement for each product and check its availability
                    // Do not add if unavailable
                    return restTemplate.getForObject("http://localhost:8082/services/api/pm/products/quantity?productId="+productId, Integer.class);
                }
        ).toList();

        for (int i =0; i< products.size();i++) {
            if (quantities.get(i) == 0 ) {
                Long remove = products.remove(i);
                System.out.println("Element has zero quantity : "+ remove);
            }
        }
        currentCart.setProductIdList(products);

        //TODO: Update the Quantity of each product in product management (later do optimize)
        for (Long productId : products) {
                // TODO: make a call to update quantity of each product
                restTemplate.getForObject("http://localhost:8082/services/api/pm/products/quantity/"+ productId+"?quantity=1", Void.class);
                }
        return cartRepository.save(currentCart);
    }

    public Cart addCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public void deleteCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }

    public void clearCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);
        cart.productIdList = null;
        cart.setTotal(0);
        cartRepository.save(cart);
    }
}
