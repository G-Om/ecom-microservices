package ecom.services.cartmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

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

    public Cart addToCart(Long cartId, List<Long> products) {
        Cart currentCart = cartRepository.findById(cartId).orElse(null);
        if (currentCart == null)
            return null;
        currentCart.setProductIdList(products);
        return cartRepository.save(currentCart);
    }

    public Cart addCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public void deleteCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }
}
