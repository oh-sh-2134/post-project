package spring.postproject.config.Security.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spring.postproject.Excetion.ExceptionBoard;
import spring.postproject.Member.Entity.Member;
import spring.postproject.Member.Repository.MemberRepository;
import spring.postproject.config.Security.model.SecurityUserDetails;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService{

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("UserDetails");
        log.info(username);
        Member member = memberRepository.findByUserId(username).orElseThrow(ExceptionBoard.NOT_FOUND_MEMBER::getException);
        log.info(member.getUserId());
        log.info(member.getPassword());
        log.info(member.getMemberRoll().toString());
        return new SecurityUserDetails(member);
//        return User.builder()
//                .username(member.getUserId())
//                .password(member.getPassword())
//                .roles(member.getMemberRoll().toString())
//                .build();
    }
}
