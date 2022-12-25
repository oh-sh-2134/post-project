package spring.postproject.Member.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import spring.postproject.Member.Entity.Member;
import spring.postproject.Member.Entity.MemberRoll;
import spring.postproject.Member.Service.MemberService;
import spring.postproject.Member.dto.MemberCreateDto;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/new")
    public String createMember(Model model){
        log.info("createMember");
        model.addAttribute("memberCreateDto",new MemberCreateDto());
        return "member/memberCreate";
    }

    @PostMapping("/member/new")
    public String create(@Valid MemberCreateDto memberCreateDto, BindingResult result, Model model){
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

        member.updateRole(MemberRoll.ROLE_NORMAL);
        memberService.signUp(member);
        log.info("member id : " + member.getId());

        log.info("create ok : " + member.getNickname());

        return "/member/loginForm";
    }


    @GetMapping("/member/Info")
    public String memberInfo(Model model){
        //log.info("member info : " + member.getNickname());
        //model.addAttribute("member",member);
        return "login";
    }
}
