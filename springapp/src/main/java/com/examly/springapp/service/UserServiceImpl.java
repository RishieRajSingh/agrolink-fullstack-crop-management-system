package com.examly.springapp.service;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.examly.springapp.config.JwtUtils;
import com.examly.springapp.exception.EntityAlreadyExistsException;
import com.examly.springapp.model.LoginDTO;
import com.examly.springapp.model.LoginRequest;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.UserRepo;

import jakarta.persistence.EntityNotFoundException;

/**
 * Service class for managing User entities.
 * 
 * This class provides methods for user registration, login, and conversion between 
 * User and LoginDTO entities. It uses the UserRepo, PasswordEncoder, AuthenticationManager, 
 * and JwtUtils to perform the necessary operations.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JwtUtils utils;

    public UserServiceImpl(UserRepo userRepo, PasswordEncoder encoder, AuthenticationManager authManager, JwtUtils utils){
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.authManager = authManager;
        this.utils = utils;
    }
    
    private static Logger myLogger = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * Registers a new user.
     * 
     * @param user The user to be registered.
     * @return A boolean indicating the success of the registration.
     * @throws ResourceAlreadyExistsException If the user already exists.
     */
    @Override
    public boolean register(User user) throws EntityAlreadyExistsException {
        User found = userRepo.getByEmail(user.getEmail());
        if (found != null) {
            throw new EntityAlreadyExistsException("User already exists");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);
        myLogger.info("New User created {}", user);
        return true;
    }
    
    /**
     * Logs in a user.
     * 
     * @param user The user to be logged in.
     * @return A LoginDTO containing login information for the user.
     * @throws UsernameNotFoundException If the user is not found or the credentials are incorrect.
     */
    @Override
    public LoginDTO login(LoginRequest user) throws UsernameNotFoundException {
        String username = user.getEmail();
        String password = user.getPassword();
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        myLogger.info("authenticated: {}", authentication.isAuthenticated());
        if (authentication.isAuthenticated()) {
            User foundUser = userRepo.getByEmail(username);
            LoginDTO dto = convertToDTO(foundUser);

            List<String> roleList = authentication.getAuthorities().stream().map(r -> r.getAuthority()).collect(Collectors.toList());
            String role = roleList.get(0);

            String token = utils.generateToken(username,role);
            dto.setToken(token);
            dto.setRole(role);
            return dto;
        }
        throw new UsernameNotFoundException("Invalid username or password");
    }

    /**
     * Converts a User entity to a LoginDTO.
     * 
     * @param user The user to be converted.
     * @return A LoginDTO containing login information for the user.
     */
    @Override

    public LoginDTO convertToDTO(User user) {
        LoginDTO dto = new LoginDTO();
        dto.setUserId(user.getUserId());
        dto.setRole(user.getUserRole());
        dto.setEmail(user.getUsername());
        dto.setUsername(user.getUsername());
        return dto;
    }

    /**
     * Converts a LoginDTO to a User entity.
     * 
     * @param dto The LoginDTO to be converted.
     * @return A User entity.
     * @throws UsernameNotFoundException If the user is not found.
     */
    @Override
    public User convertToEntity(LoginDTO dto) throws UsernameNotFoundException {
        User found = userRepo.getByEmail(dto.getEmail());
        if (found == null) {
            throw new UsernameNotFoundException("Username in DTO does not exist");
        }
        return found;
    }

    /**
     * Retrieves all users.
     * 
     * @return A list of all users.
     */
    @Override
    public List<User> getAll() {
        return userRepo.findAll();
    }

    @Override
    public User getUserById(int userId) throws EntityNotFoundException  {
        User fId = userRepo.findById(userId).orElse(null);
        if(fId == null){
            throw new EntityNotFoundException("User with Id"+userId+"not found");
        }
        return fId;
    }
}
