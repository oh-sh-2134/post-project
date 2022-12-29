package spring.postproject.Post.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import spring.postproject.Post.Entity.QPost;
import spring.postproject.Post.Entity.Post;

import static spring.postproject.Post.Entity.QPost.post;


@Repository
public class PostRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory factory;

    public PostRepositorySupport(JPAQueryFactory factory) {
        super(Post.class);
        this.factory = factory;
    }

    public void addViewCntFromRedis(Long postId, Long addCount) {
        factory
                .update(post)
                .set(post.count,addCount)
                .where(post.id.eq(postId))
                .execute();
    }
}

