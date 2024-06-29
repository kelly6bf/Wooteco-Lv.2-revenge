package site.roomescape.springroomescaperevenge.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.roomescape.springroomescaperevenge.common.service.port.PasswordEncoder;
import site.roomescape.springroomescaperevenge.member.domain.Member;
import site.roomescape.springroomescaperevenge.member.domain.MemberCreate;
import site.roomescape.springroomescaperevenge.member.domain.MemberPassword;
import site.roomescape.springroomescaperevenge.member.domain.MemberUpdate;
import site.roomescape.springroomescaperevenge.member.service.port.MemberRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public List<Member> getAll() {
        return memberRepository.getAll();
    }

    public Member create(final MemberCreate memberCreate) {
        final Member member = memberCreate.create(passwordEncoder);
        return memberRepository.save(member);
    }

    public Member update(final Long memberId, final MemberUpdate memberUpdate) {
        final Member member = memberRepository.getById(memberId);
        final Member updatedMember = memberUpdate.update(member);

        return memberRepository.save(updatedMember);
    }

    public void changePassword(final Long memberId, final MemberPassword oldPassword, final MemberPassword newPassword) {
        final Member member = memberRepository.getById(memberId);
        final Member updatedMember = member.changePassword(oldPassword, newPassword, passwordEncoder);
        memberRepository.save(updatedMember);
    }

    public void delete(final Long memberId) {
        memberRepository.deleteById(memberId);
    }
}
