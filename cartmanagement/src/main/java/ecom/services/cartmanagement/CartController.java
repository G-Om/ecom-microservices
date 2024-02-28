package ecom.services.cartmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.desktop.UserSessionEvent;
import java.util.List;

@RestController
@RequestMapping("/services/api/cm/")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/cart/all")
    public List<Cart> getAllCarts() {

        return cartService.getAllCarts();
    }
    @PostMapping("/cart")
    // TODO: Add new cart when a new user is created
    public Cart addNewCart(@RequestBody Cart cart) {
        return cartService.addCart(cart);
    }

    @GetMapping("/cart")
    public Cart getCartForUser(@RequestParam Long userId) {
        return cartService.getCartByUserId(userId);
    }
    @PutMapping("/cart/{cartId}")
    public Cart addProductsToCart(@PathVariable Long cartId, @RequestBody Cart cart) {
        return cartService.addToCart(cartId, cart.getProductIdList());
    }

    @DeleteMapping("/cart/{cartId}")
    public ResponseEntity<String> deleteCart(@PathVariable Long cartId) {
        cartService.deleteCart(cartId);
        if (cartService.getCartByCartId(cartId).isEmpty()) {
            return new ResponseEntity<>("Deleted cart successfully!", HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }




}
