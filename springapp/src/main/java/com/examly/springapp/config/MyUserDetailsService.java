package com.examly.springapp.config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.UserRepo;
 
/**
 * Custom implementation of UserDetailsService for Spring Security.
 */
@Component
public class MyUserDetailsService implements UserDetailsService {
 
    @Autowired // NOSONAR
    private UserRepo repo;
 
    private static Logger myLogger = LoggerFactory.getLogger(MyUserDetailsService.class);
 
    /**
     * Loads the user by username (email) and returns UserDetails.
     *
     * @param email The email of the user to load.
     * @return UserDetails object containing user information.
     * @throws UsernameNotFoundException if the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        myLogger.info("INSIDE loadUserByUsername() method in MyUserDetailsService");
        User user = this.repo.getByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid Username");
        }
        return new MyUser(user);
    }
}