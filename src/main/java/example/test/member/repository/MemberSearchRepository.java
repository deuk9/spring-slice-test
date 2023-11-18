package example.test.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import example.test.member.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static example.test.member.model.QMember.member;

@Repository
@RequiredArgsConstructor
public class MemberSearchRepository {

    private final JPAQueryFactory jpaQueryFactory;


    public Optional<Member> findByEmail(String email) {

        return Optional.ofNullable(
                jpaQueryFactory.selectFrom(member)
                        .where(member.email.eq(email))
                        .fetchOne());
    }
}
