package spring.postproject.Member.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring.postproject.Member.Service.MemberService;
import spring.postproject.Member.dto.MemberCreateDto;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class MemberRestApiController {

    private final MemberService memberService;

    @PostMapping("/userIdDuplicationCheck")
    public int userIdDuplicationCheck(@RequestBody HashMap<String,Object> memberCreateDto) {
        return memberService.userIdDuplicationCheck((String)memberCreateDto.get("userId")) == (Boolean)true ? 1:0;
    }

    @PostMapping("/nickNameDuplicationCheck")
    public int nickNameDuplicationCheck(@RequestBody HashMap<String,Object> memberCreateDto) {
        return memberService.nicknameDuplicationCheck((String)memberCreateDto.get("nickname")) == (Boolean)true ? 1:0;
    }



}
