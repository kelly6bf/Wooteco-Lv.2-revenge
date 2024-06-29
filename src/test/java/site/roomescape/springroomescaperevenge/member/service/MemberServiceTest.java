package site.roomescape.springroomescaperevenge.member.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import site.roomescape.springroomescaperevenge.member.domain.Member;
import site.roomescape.springroomescaperevenge.member.domain.MemberCreate;
import site.roomescape.springroomescaperevenge.mock.FakeMemberRepository;
import site.roomescape.springroomescaperevenge.mock.TestPasswordEncoder;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayName("회원 서비스 테스트")
class MemberServiceTest {

    private MemberService memberService;

    @BeforeEach
    void setUp() {
        final FakeMemberRepository memberRepository = new FakeMemberRepository();
        final TestPasswordEncoder passwordEncoder = new TestPasswordEncoder();
        this.memberService = new MemberService(memberRepository, passwordEncoder);
    }

    @DisplayName("MemberCreate 객체를 받으면 Member 객체를 생성한다.")
    @Test
    void createTest() {
        // Given
        final String email = "kelly@email.com";
        final String password = "userPw1234!";
        final String name = "kelly";
        final MemberCreate memberCreate = new MemberCreate(email, password, name);

        // When
        final Member member = memberService.create(memberCreate);

        // Then
        assertSoftly(softly -> {
            softly.assertThat(member.getEmail().value()).isEqualTo(email);
            softly.assertThat(member.getPassword().value()).isEqualTo("encoded-header " + password + "encoded-footer");
            softly.assertThat(member.getName().value()).isEqualTo(name);
        });
    }
}
