package com.examly.springapp.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.examly.springapp.exception.EntityAlreadyExistsException;
import com.examly.springapp.model.LoginDTO;
import com.examly.springapp.model.LoginRequest;
import com.examly.springapp.model.User;
import com.examly.springapp.service.UserServiceImpl;

/**
 * Controller class for managing user-related endpoints
 * Annotated with Rest Controller to indicate a Restful Controller
 */
@RestController
public class AuthController {

    /**
     * Constructor-based dependency injection for UserService.
     * Annotated with `@Autowired` to automatically wire the UserService bean.
     * 
     * @param userService the user Service.
     */

    private final UserServiceImpl userService;

    public AuthController(UserServiceImpl userService) {
        this.userService = userService;
    }
    
    /**
   * Registers a new user.
   *
   * @param user the user object containing the new user's details.
   * @return a `ResponseEntity` with the registered user's details and a status of `201 Created`.
  */

    @PostMapping("/api/register")
    public ResponseEntity<Boolean> registerUser(@RequestBody User user) throws EntityAlreadyExistsException{
        return ResponseEntity.status(201).body(userService.register(user));
    }
    /**
    * Logs in a user.
    *
    * @param user the user object containing the user's login credentials.
    * @return a `ResponseEntity` with the logged-in user's details and a status of `200 OK`.
    */
    @PostMapping("/api/login")
    public ResponseEntity<LoginDTO> loginUser(@RequestBody LoginRequest user){
        return ResponseEntity.status(200).body(userService.login(user));
    }
    @GetMapping("/api/users/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable int userId){
        return ResponseEntity.status(200).body(userService.getUserById(userId));
    }
}