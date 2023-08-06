package rest.webservices.restfulwebservices.user;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor

@RestController
public class UserResource {

    @Autowired
    private UserDAOService userDAOService;

    @GetMapping("/users")
    public List<User> retriveAllUser() {
        return userDAOService.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable Integer id) {
        User user = userDAOService.findById(id);

        if (user == null){
            throw new UserNotFoundException("id " + id);
        }
        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<User>(userDAOService.saveUser(user), HttpStatus.CREATED);
    }
}

