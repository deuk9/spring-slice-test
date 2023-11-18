package example.test.member.service.mapper;


import example.test.member.model.Member;
import example.test.member.service.input.MemberRegisterRequest;
import example.test.member.service.output.MemberDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MemberMapper {


    Member toEntity(MemberRegisterRequest memberRegisterRequest);
    MemberDetail toDetail(Member member);
}
