package spring.postproject.Member.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spring.postproject.Member.Entity.Member;
import spring.postproject.Member.Entity.MemberRoll;
import spring.postproject.Member.Service.MemberService;
import spring.postproject.Member.Service.SessionLoginServiceImpl;
import spring.postproject.Member.dto.MemberCreateDto;
import spring.postproject.Member.dto.MemberLoginDto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final SessionLoginServiceImpl loginService;
    private final String login = "login";
    private final MemberService memberService;

    @GetMapping("/")
    public String loginForm(@SessionAttribute(name = login,required = false)Member member,
            Model model){
        log.info("loginForm");
        if(member != null){
            model.addAttribute("member",member);
            return "member/memberInfo";
        }
        model.addAttribute("memberLoginDto",new MemberLoginDto());
        return "member/loginForm";
    }

    @PostMapping("/")
    public String login(@Valid MemberLoginDto memberLoginDto, BindingResult result,
                        HttpServletRequest request, Model model){
        log.info("login : ");
        log.info(memberLoginDto.getUserId() + memberLoginDto.getPassword());
        if (result.hasErrors()) {
            return "member/loginForm";
        }
        Member loginMember = loginService.login(memberLoginDto.getUserId(), memberLoginDto.getPassword());

        if (loginMember == null) {
            result.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        //로그인 성공 처리
        //세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
        HttpSession session = request.getSession();
        //세션에 로그인 회원 정보 보관
        session.setAttribute(login, loginMember);
        log.info("session id : " + session.getId());

        model.addAttribute("member",loginMember);
        return "/member/memberInfo";
    }

    @GetMapping("/member/new")
    public String createMember(Model model){
        log.info("createMember");
        model.addAttribute("memberCreateDto",new MemberCreateDto());
        return "member/createMember";
    }

    @PostMapping("/member/new")
    public String create(@Valid MemberCreateDto memberCreateDto, BindingResult result){
        log.info(memberCreateDto.getNickname() + memberCreateDto.getUserId() + memberCreateDto.getPassword());

        if (result.hasErrors()) {
            return "member/createMember";
        }
        log.info("create");
        Member member = Member.builder()
                .userId(memberCreateDto.getUserId())
                .password(memberCreateDto.getPassword())
                .nickName(memberCreateDto.getNickname())
                .build();
        member.updateRole(MemberRoll.NORMAL);
        memberService.signUp(member);
        log.info("create ok : " + member.getNickname());

        return "redirect:/";
    }


    //@GetMapping("/member/memberInfo")
    public String memberInfo(@RequestParam("member")Member member,Model model){
        log.info("member info : " + member.getNickname());
        model.addAttribute("member",member);
        return "member/memberInfo";
    }

    @GetMapping("/member/logout")
    public String logout(HttpServletRequest request) {
        log.info("logout");
        // getSession(false) 를 사용해야 함 (세션이 없더라도 새로 생성하면 안되기 때문)
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        log.info("session delete success");
        return "redirect:/";
    }
}
