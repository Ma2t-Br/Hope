package Hope.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findUserByUsername(@Param("username") String username);

    @Modifying
    @Query(value = "INSERT INTO user (username, first_name, last_name, role, password) VALUES (:username, :firstName, :lastName, 'etudiant', :password)", nativeQuery = true)
    void insertUser(@Param("username") String username, @Param("password") String password, @Param("firstName") String firstName, @Param("lastName") String lastName);
}
