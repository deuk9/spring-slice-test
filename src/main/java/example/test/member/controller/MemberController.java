package example.test.member.controller;

import example.test.member.service.output.MemberDetail;
import example.test.member.service.MemberService;
import example.test.member.service.input.MemberRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public void register(@RequestBody MemberRegisterRequest request) {
        memberService.register(request);
    }

    @GetMapping("{email}")
    public ResponseEntity<MemberDetail> getByEmail(@PathVariable String email) {

        return ResponseEntity.ok(memberService.getByEmail(email));
    }

}
