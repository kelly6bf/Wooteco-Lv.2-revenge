package site.roomescape.springroomescaperevenge.member.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import site.roomescape.springroomescaperevenge.member.domain.Member;
import site.roomescape.springroomescaperevenge.member.service.port.MemberRepository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberJpaRepository memberJpaRepository;

    @Override
    public List<Member> getAll() {
        return memberJpaRepository.findAll()
                .stream()
                .map(MemberEntity::toModel)
                .toList();
    }

    @Override
    public Member save(final Member member) {
        final MemberEntity memberEntity = new MemberEntity(member);
        return memberJpaRepository.save(memberEntity).toModel();
    }
}
