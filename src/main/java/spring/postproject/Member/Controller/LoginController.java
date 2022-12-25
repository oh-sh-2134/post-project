package spring.postproject.Member.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import spring.postproject.Post.Entity.Post;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;


@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final SessionLoginServiceImpl loginService;
    private final String login = "login";
    private final MemberService memberService;

    @GetMapping("/login")
//    public String loginForm(@SessionAttribute(name = login,required = false)Member member, Model model){
    public String loginForm(@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : member") Member member, Model model){
        log.info("loginForm");
//        log.info("member id : " + memberId);
        if(member != null){
//            Member member = memberService.findOne(memberId);
            model.addAttribute("member",member);
            List<Post> posts = member.getPostList();
            model.addAttribute("post",posts);
            for (Post post : posts) {
                log.info("post : " + post);
                log.info("post id : " + post.getId() + " post title : " + post.getTitle() + " post content : " + post.getContent());
            }

            return "redirect:/";
        }
//        model.addAttribute("memberLoginDto",new MemberLoginDto());
        return "/member/loginForm";
    }

//    @PostMapping("/auth/login")
    public String login(MemberLoginDto memberLoginDto, BindingResult result,
                        HttpServletRequest request, Model model){
        log.info("login : ");
        log.info(memberLoginDto.getUserId() + memberLoginDto.getPassword());
        if (result.hasErrors()) {
            return "member/loginForm";
        }
        Member loginMember = loginService.login(memberLoginDto.getUserId(), memberLoginDto.getPassword());

        if (loginMember == null) {
            result.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "member/loginForm";
        }

        //로그인 성공 처리
        //세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
        HttpSession session = request.getSession();
        //세션에 로그인 회원 정보 보관
        session.setAttribute(login, loginMember.getId());
        log.info("session id : " + session.getId());

        model.addAttribute("member",loginMember);
        List<Post> posts = loginMember.getPostList();
        model.addAttribute("post",posts);
        return "redirect:/";
    }

    @GetMapping("/auth/loginFailure")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception,
                        Model model) {

        /* 에러와 예외를 모델에 담아 view resolve */
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "/member/loginForm";
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
