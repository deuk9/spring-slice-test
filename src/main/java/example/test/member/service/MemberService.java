package example.test.member.service;

import example.test.member.service.input.MemberRegisterRequest;
import example.test.member.service.output.MemberDetail;

public interface MemberService {
    void register(MemberRegisterRequest memberRegisterRequest);
    MemberDetail getByEmail(String email);

}
