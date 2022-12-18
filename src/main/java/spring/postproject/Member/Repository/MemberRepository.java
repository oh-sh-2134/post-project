package spring.postproject.Member.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.postproject.Member.Entity.Member;

import javax.swing.text.html.Option;
import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member,Long> {

    Optional<Member> findByNickname(String nickName);
    Optional<Member> findByUserIdAndPassword(String userId, String Password);
    Optional<Member> findByUserId(String userId);

    boolean existsByNickname(String Nickname);
    boolean existsByUserId(String userId);
}
