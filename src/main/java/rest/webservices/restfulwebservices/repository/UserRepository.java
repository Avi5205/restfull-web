package rest.webservices.restfulwebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rest.webservices.restfulwebservices.user.User;

public interface UserRepository extends JpaRepository<User,Integer> {
}
