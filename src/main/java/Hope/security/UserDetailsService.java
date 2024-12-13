package Hope.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsService {

    // TODO: Exception à personnaliser !!
    UserDetails loadUserByUsername(String username) throws Exception;
}
