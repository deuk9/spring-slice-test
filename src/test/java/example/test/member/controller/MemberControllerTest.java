package example.test.member.controller;


import example.test.config.AbstractTestController;
import example.test.member.service.MemberService;
import example.test.member.service.input.MemberRegisterRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MemberControllerTest extends AbstractTestController {

    @Mock
    MemberService memberService;
    MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = mockMvc(new MemberController(memberService));
    }

    @Nested
    @DisplayName("맴버 등록")
    class Register {
        @Test
        @DisplayName("맴버 등록 성공")
        void success_register_member() throws Exception {
            MemberRegisterRequest memberRegisterRequest = MemberRegisterRequest.builder()
                    .email("name@emali.com")
                    .name("name")
                    .build();
            String body = objectMapper.writeValueAsString(memberRegisterRequest);

            mockMvc.perform(post("/members")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(body))
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful());
        }


        @Test
        @DisplayName("맴버 등록 실패")
        void fail_register_member() throws Exception {
            MemberRegisterRequest memberRegisterRequest = MemberRegisterRequest.builder()
                    .email("name@emali.com")
                    .name("name")
                    .build();

            String body = objectMapper.writeValueAsString(memberRegisterRequest);
            doThrow(new RuntimeException())
                    .when(memberService).register(any(MemberRegisterRequest.class));

            mockMvc.perform(post("/members")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(body))
                    .andDo(print())
                    .andExpect(status().isBadRequest());
        }
    }


}
