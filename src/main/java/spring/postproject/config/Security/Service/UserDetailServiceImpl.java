package spring.postproject.config.Security.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spring.postproject.Excetion.BaseException.CustomException;
import spring.postproject.Excetion.ExceptionBoard;
import spring.postproject.Member.Entity.Member;
import spring.postproject.Member.Repository.MemberRepository;
import spring.postproject.config.Security.model.securityUserDetails;

import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class UserDetailServiceImpl implements UserDetailsService{

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUserId(username).orElseThrow(ExceptionBoard.NOT_FOUND_MEMBER::getException);
        return new securityUserDetails(member);
    }
}
