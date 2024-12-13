package Hope.controller;

import Hope.entity.User;
import Hope.models.LoginCredentials;
import Hope.repository.UserRepository;
import Hope.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authManager;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(UserRepository userRepository, JWTUtil jwtUtil, AuthenticationManager authManager, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.authManager = authManager;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public Map<String, Object> registerHandler(@RequestBody User user){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user = userRepository.save(user);
        String token = jwtUtil.generateToken(user.getUsername(), "Enseignant");
        return Collections.singletonMap("jwt-token", token);
    }

    @PostMapping("/login")
    public Map<String, Object> loginHandler(@RequestBody LoginCredentials body){
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(body.getUsername(), body.getPassword());
            authManager.authenticate(token);
            String authToken = jwtUtil.generateToken(body.getUsername(), "Enseignant");
            return Collections.singletonMap("jwt-token", authToken);
        } catch(Exception e){
            throw new RuntimeException("Invalid Login Credentials");
        }
    }
}
