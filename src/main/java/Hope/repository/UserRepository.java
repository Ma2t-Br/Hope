package Hope.repository;

import Hope.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {

    private List<User> users = new ArrayList();

    public Optional<User> findByUsername(String username) {
        // This is a dummy implementation
        return null;
    }
}
