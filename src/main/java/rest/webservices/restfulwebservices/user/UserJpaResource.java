package rest.webservices.restfulwebservices.user;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import rest.webservices.restfulwebservices.repository.UserRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@AllArgsConstructor

@RestController
public class UserJpaResource {

    @Autowired
    private UserRepository repository;

    @GetMapping("/jpa/users")
    public List<User> retriveAllUser() {
        return repository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable Integer id) {
        Optional<User> user = repository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("id " + id);
        }
        EntityModel<User> entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retriveAllUser());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = repository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable Integer id) {
        repository.deleteById(id);
    }


}

