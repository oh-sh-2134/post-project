package spring.postproject.Member.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spring.postproject.Member.Entity.Member;
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
public class LoginController {

    private final SessionLoginServiceImpl loginService;

    @GetMapping("/")
    public String loginForm(Model model){
        model.addAttribute("memberLoginDto",new MemberLoginDto());
        return "member/loginForm";
    }

    @GetMapping("/member/new")
    public String createMember(Model model){
        model.addAttribute("memberCreateDto",new MemberCreateDto());
        return "member/createMember";
    }

    @PostMapping("/")
    public String login(@Valid MemberLoginDto memberLoginDto, BindingResult result,
                        HttpServletRequest request){
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
        session.setAttribute(SessionConst.LOGIN_MEMBER.toString(), loginMember);

        return "/member/memberInfo";
    }

    @PostMapping("/member/new")
    public String create(@Valid MemberCreateDto memberCreateDto, BindingResult result){
        return "member/createMember";
    }


    @GetMapping("/member/memberInfo")
    public String memberInfo(){

        return "member/memberInfo";
    }


    @PostMapping("/login")
    public String login2(@Valid @ModelAttribute MemberLoginDto form, BindingResult bindingResult,
                          @RequestParam(defaultValue = "/") String redirectURL,
                          HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        Member loginMember = loginService.login(form.getUserId(), form.getPassword());

        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        //로그인 성공 처리
        //세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
        HttpSession session = request.getSession();
        //세션에 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER.toString(), loginMember);

        return "redirect:" + redirectURL;

    }

    @PostMapping("/logout")
    public String logoutV3(HttpServletRequest request) {
        // getSession(false) 를 사용해야 함 (세션이 없더라도 새로 생성하면 안되기 때문)
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    private void expireCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
