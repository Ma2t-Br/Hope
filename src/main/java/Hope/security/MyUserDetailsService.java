package Hope.security;

import Hope.entity.User;
import Hope.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws Exception {
        Optional<User> userRes = userRepository.findByUsername(username);
        if(userRes.isEmpty())
            throw new Exception("Could not find user with username: " + username);
        User user = userRes.get();
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("Enseignant")) // TODO: trouver comment vérifier le role
        );
    }
}
