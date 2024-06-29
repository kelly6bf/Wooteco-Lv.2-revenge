package site.roomescape.springroomescaperevenge.member.service.port;

import site.roomescape.springroomescaperevenge.member.domain.Member;

import java.util.List;

public interface MemberRepository {

    List<Member> getAll();

    Member getById(Long memberId);

    Member save(Member member);
}
