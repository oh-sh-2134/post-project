package spring.postproject.Post.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.postproject.Post.Entity.Post;

public interface PostRepository extends JpaRepository<Post,Long> {

}
