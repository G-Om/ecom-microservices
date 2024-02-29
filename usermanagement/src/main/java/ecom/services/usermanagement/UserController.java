package ecom.services.usermanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/services/api/um/users")
public class UserController {

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
    public Optional<User> addNewUser(@RequestBody User user) {
        return userService.addNewUser(user);
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
