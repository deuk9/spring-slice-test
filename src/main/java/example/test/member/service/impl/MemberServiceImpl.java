package example.test.member.service.impl;

import example.test.member.repository.MemberRepository;
import example.test.member.repository.MemberSearchRepository;
import example.test.member.service.MemberService;
import example.test.member.service.input.MemberRegisterRequest;
import example.test.member.service.mapper.MemberMapper;
import example.test.member.service.output.MemberDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberSearchRepository memberSearchRepository;
    private final MemberMapper memberMapper;


    @Override
    @Transactional
    public void register(MemberRegisterRequest memberRegisterRequest) {

        if(memberRepository.existsByEmail(memberRegisterRequest.email()))
            throw new RuntimeException("already registered member");

        memberRepository.save(memberMapper.toEntity(memberRegisterRequest));
    }

    @Override
    public MemberDetail getByEmail(String email) {
        return memberSearchRepository.findByEmail(email)
                .map(memberMapper::toDetail)
                .orElseThrow(RuntimeException::new);
    }
}