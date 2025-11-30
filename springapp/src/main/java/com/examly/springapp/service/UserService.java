package com.examly.springapp.service;
import java.util.List;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.examly.springapp.exception.EntityAlreadyExistsException;
import com.examly.springapp.model.LoginDTO;
import com.examly.springapp.model.LoginRequest;
import com.examly.springapp.model.User;

/**
 * Service interface for managing User entities.
 * 
 * This interface provides methods for user registration, login, and conversion
 * between
 * User and LoginDTO entities. It is intended to be implemented by a service
 * class that
 * performs the necessary operations.
 */
public interface UserService {

    /**
     * Registers a new user.
     * 
     * @param user The user to be registered.
     * @return A boolean indicating the success of the registration.
     * @throws ResourceAlreadyExistsException If the user already exists.
     */
    boolean register(User user) throws EntityAlreadyExistsException;

    /**
     * Logs in a user.
     * 
     * @param user The user to be logged in.
     * @return A LoginDTO containing login information for the user.
     * @throws UsernameNotFoundException If the user is not found.
     */
    LoginDTO login(LoginRequest user) throws UsernameNotFoundException;

    /**
     * Converts a User entity to a LoginDTO.
     * 
     * @param user The user to be converted.
     * @return A LoginDTO containing login information for the user.
     */

    LoginDTO convertToDTO(User user);

    /**
     * Converts a LoginDTO to a User entity.
     * 
     * @param dto The LoginDTO to be converted.
     * @return A User entity.
     * @throws UsernameNotFoundException If the user is not found.
     */
    User convertToEntity(LoginDTO dto) throws UsernameNotFoundException;

    /**
     * Retrieves all users.
     * 
     * @return A list of all users.
     */
    List<User> getAll();

    User getUserById(int userId);
}