package site.roomescape.springroomescaperevenge.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.roomescape.springroomescaperevenge.common.service.port.PasswordEncoder;
import site.roomescape.springroomescaperevenge.member.domain.Member;
import site.roomescape.springroomescaperevenge.member.domain.MemberCreate;
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
}
