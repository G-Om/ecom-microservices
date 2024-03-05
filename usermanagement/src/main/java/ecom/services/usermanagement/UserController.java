package ecom.services.usermanagement;

import com.fasterxml.jackson.databind.util.JSONPObject;
import ecom.services.usermanagement.util.Cart;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/services/api/um/users")
public class UserController {

    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    UserService userService;

    @GetMapping
    public List<User> getUserList(){
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public Optional<User> getUser(@RequestParam long userId){
        return userService.getUserById(userId);
    }

    @PostMapping
    public User addNewUser(@RequestBody User user) throws JSONException {

        User newUser = userService.addNewUser(user).get();

        //TODO: whenever a new user is added create a cart for him
        // Making a post request
        String createCartUrl = "http://localhost:8081/services/api/cm/cart";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Creating a JSON object for cart
        JSONObject cartJson = new JSONObject();
        cartJson.put("userId", newUser.getId());
        cartJson.put("total", 0);
        cartJson.put( "status", "ACTIVE");
        cartJson.put("shippingAddress", "");

        // First, we’ll build the request object of type HttpEntity based on the personJsonObject and the headers containing the Content-Type

        HttpEntity<String> request =
                new HttpEntity<String>(cartJson.toString(), headers);

        // Finally making the post call
        restTemplate.postForObject(createCartUrl, request, Cart.class);
        return newUser;
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        return userService.updateUserByObj(user);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUser(@RequestBody User user) {
        userService.deleteUserByObj(user);
        return new ResponseEntity<>("Deleted user successfully!", HttpStatus.OK);

    }


}
