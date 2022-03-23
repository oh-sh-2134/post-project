package spring.postproject.Repository.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.postproject.Entity.Member.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {


    Optional<Member> findByNickName(String nickName);


}
