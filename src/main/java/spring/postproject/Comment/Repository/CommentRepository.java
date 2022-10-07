package spring.postproject.Comment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.postproject.Comment.Entitiy.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

}
