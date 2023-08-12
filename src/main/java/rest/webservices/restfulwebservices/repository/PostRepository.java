package rest.webservices.restfulwebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rest.webservices.restfulwebservices.posts.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
