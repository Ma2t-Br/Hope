package Hope.controller.login;

import Hope.model.User;
import Hope.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoginService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
       this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public boolean login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            System.out.println("Invalid input provided.");
            return false;
        }

       Optional<User> users = userRepository.findUserByUsername(username);
        if (users.isEmpty()){
            return false;
        }
        User user = users.get();
        return passwordEncoder.matches(password, user.getPassword());
    }
}
