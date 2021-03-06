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
import spring.postproject.Post.Entity.Post;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final SessionLoginServiceImpl loginService;
    private final String login = "login";
    private final MemberService memberService;

    @GetMapping("/login")
    public String loginForm(@SessionAttribute(name = login,required = false)Member member, Model model){
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

//    @PostMapping("/login")
    public String login(MemberLoginDto memberLoginDto, BindingResult result,
                        HttpServletRequest request, Model model){
        log.info("login : ");
        log.info(memberLoginDto.getUserId() + memberLoginDto.getPassword());
        if (result.hasErrors()) {
            return "member/loginForm";
        }
        Member loginMember = loginService.login(memberLoginDto.getUserId(), memberLoginDto.getPassword());

        if (loginMember == null) {
            result.reject("loginFail", "????????? ?????? ??????????????? ?????? ????????????.");
            return "member/loginForm";
        }

        //????????? ?????? ??????
        //????????? ????????? ?????? ?????? ??????, ????????? ?????? ????????? ??????
        HttpSession session = request.getSession();
        //????????? ????????? ?????? ?????? ??????
        session.setAttribute(login, loginMember.getId());
        log.info("session id : " + session.getId());

        model.addAttribute("member",loginMember);
        List<Post> posts = loginMember.getPostList();
        model.addAttribute("post",posts);
        return "redirect:/";
    }

    @GetMapping("/member/new")
    public String createMember(Model model){
        log.info("createMember");
        model.addAttribute("memberCreateDto",new MemberCreateDto());
        return "member/memberCreate";
    }

    @PostMapping("/member/new")
    public String create(MemberCreateDto memberCreateDto, BindingResult result){
        log.info(memberCreateDto.getNickname() + memberCreateDto.getUserId() + memberCreateDto.getPassword());

        if (result.hasErrors()) {
            return "member/memberCreate";
        }
        log.info("create");
        Member member = Member.builder()
                .userId(memberCreateDto.getUserId())
                .password(memberCreateDto.getPassword())
                .nickName(memberCreateDto.getNickname())
                .build();
        log.info("member id : " + member.getId());

        member.updateRole(MemberRoll.NORMAL);
        memberService.signUp(member);
        log.info("member id : " + member.getId());

        log.info("create ok : " + member.getNickname());

        return "redirect:/";
    }


    @GetMapping("/member/Info")
    public String memberInfo(Model model){
        //log.info("member info : " + member.getNickname());
        //model.addAttribute("member",member);
        return "login";
    }

    @GetMapping("/member/logout")
    public String logout(HttpServletRequest request) {
        log.info("logout");
        // getSession(false) ??? ???????????? ??? (????????? ???????????? ?????? ???????????? ????????? ??????)
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        log.info("session delete success");
        return "redirect:/";
    }
}
