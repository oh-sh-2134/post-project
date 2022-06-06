package spring.postproject.Member.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.postproject.Member.Entity.Member;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member,Long> {

    Optional<Member> findByNickname(String nickName);
    Optional<Member> findByUserIdAndPassword(String userId, String Password);
}
