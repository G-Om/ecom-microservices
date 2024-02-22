package ecom.services.usermanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> addNewUser(User user) {

        return Optional.of(userRepository.save(user));
    }

    public Optional<User> deleteUserByObj(User user) {
        userRepository.delete(user);
        if (userRepository.findById(user.getId()).isEmpty())
            return Optional.of(user);
        else
            return null;
    }

    public User updateUserByObj(User user) {

        return userRepository.save(user);
        
    }

    public Optional<User> getUserById(long userId) {
        return userRepository.findById(userId);
    }
}
