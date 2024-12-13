package Hope.repository;

import Hope.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.beans.BeanProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserRepository {

    private List<User> users = new ArrayList();

    public Optional<User> findByUsername(String username) {
        // TODO: Implement this method
        return null;
    }

    public User save(User user){
        // TODO: Implement this method
        return null;
    }
}
