package example.test.member.service.input;

import lombok.*;

@Builder
public record MemberRegisterRequest(String name, String email) {

}
