package com.golfclub.member;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public Member addMember(@RequestBody Member member) {
        return memberService.addMember(member);
    }

    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/search")
    public List<Member> searchMembers(@RequestParam(required = false) String name,
                                      @RequestParam(required = false) String phone,
                                      @RequestParam(required = false) Integer duration) {
        if (name != null) {
            return memberService.searchByName(name);
        } else if (phone != null) {
            return memberService.searchByPhone(phone);
        } else if (duration != null) {
            return memberService.searchByDuration(duration);
        } else {
            return memberService.getAllMembers();
        }
    }
}
