package site.roomescape.springroomescaperevenge.member.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import site.roomescape.springroomescaperevenge.common.exception.ResourceNotFoundException;
import site.roomescape.springroomescaperevenge.member.domain.Member;
import site.roomescape.springroomescaperevenge.member.domain.MemberCreate;
import site.roomescape.springroomescaperevenge.member.domain.MemberEmail;
import site.roomescape.springroomescaperevenge.member.domain.MemberName;
import site.roomescape.springroomescaperevenge.member.domain.MemberPassword;
import site.roomescape.springroomescaperevenge.member.domain.MemberRole;
import site.roomescape.springroomescaperevenge.member.domain.MemberUpdate;
import site.roomescape.springroomescaperevenge.mock.FakeMemberRepository;
import site.roomescape.springroomescaperevenge.mock.TestPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayName("회원 서비스 테스트")
class MemberServiceTest {

    private TestPasswordEncoder passwordEncoder;
    private FakeMemberRepository memberRepository;
    private MemberService memberService;

    @BeforeEach
    void setUp() {
        final TestPasswordEncoder passwordEncoder = new TestPasswordEncoder();
        final FakeMemberRepository memberRepository = new FakeMemberRepository();
        this.passwordEncoder = passwordEncoder;
        this.memberRepository = memberRepository;
        this.memberService = new MemberService(memberRepository, passwordEncoder);
    }

    @DisplayName("MemberCreate 객체를 받으면 Member 객체를 생성하고 저장한다.")
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
            softly.assertThat(memberRepository.getById(1L)).isNotNull();
            softly.assertThat(member.getId()).isEqualTo(1L);
            softly.assertThat(member.getEmail().value()).isEqualTo(email);
            softly.assertThat(passwordEncoder.matches(password, member.getPassword().value())).isTrue();
            softly.assertThat(member.getName().value()).isEqualTo(name);
        });
    }

    @DisplayName("MemberUpdate 객체를 받으면 Member 객체 정보 수정을 요청하고 저장한다.")
    @Test
    void updateTest() {
        // Given
        final Member member = Member.builder()
                .email(new MemberEmail("kelly6bf@gmail.com"))
                .password(new MemberPassword("kellyPw1234!"))
                .name(new MemberName("kelly"))
                .role(MemberRole.USER)
                .build();
        memberRepository.save(member);

        final String newEmail = "elmo@email.com";
        final String newName = "elmo";
        final MemberUpdate memberUpdate = new MemberUpdate(newEmail, newName);

        // When
        final Member updatedMember = memberService.update(1L, memberUpdate);

        // Then
        final Member savedMember = memberRepository.getById(1L);
        assertSoftly(softly -> {
            softly.assertThat(updatedMember.getId()).isEqualTo(1L);
            softly.assertThat(updatedMember.getEmail().value()).isEqualTo(newEmail);
            softly.assertThat(updatedMember.getName().value()).isEqualTo(newName);
            softly.assertThat(savedMember.getId()).isEqualTo(1L);
            softly.assertThat(savedMember.getEmail().value()).isEqualTo(newEmail);
            softly.assertThat(savedMember.getName().value()).isEqualTo(newName);
        });
    }

    @DisplayName("기존 비밀번호, 새로운 비밀번호 객체를 받으면 Member 객체 정보 수정을 요청하고 저장한다.")
    @Test
    void changePasswordTest() {
        // Given
        final MemberPassword oldPassword = new MemberPassword("kellyPw1234!");
        final Member member = Member.builder()
                .email(new MemberEmail("kelly6bf@gmail.com"))
                .password(oldPassword.encode(passwordEncoder))
                .name(new MemberName("kelly"))
                .role(MemberRole.USER)
                .build();
        memberRepository.save(member);

        final Long memberId = 1L;
        final MemberPassword newPassword = new MemberPassword("elmoPw1234!");

        // When
        memberService.changePassword(
                memberId,
                oldPassword,
                newPassword
        );

        // Then
        final Member savedMember = memberRepository.getById(1L);
        assertThat(passwordEncoder.matches(newPassword.value(), savedMember.getPassword().value())).isTrue();

    }

    @DisplayName("회원을 삭제한다.")
    @Test
    void deleteTest() {
        // Given
        final Member member = Member.builder()
                .email(new MemberEmail("kelly6bf@gmail.com"))
                .password(new MemberPassword("kellyPw1234!"))
                .name(new MemberName("kelly"))
                .role(MemberRole.USER)
                .build();
        memberRepository.save(member);

        // When
        memberService.delete(1L);

        // Then
        assertThatThrownBy(() -> memberRepository.getById(1L))
                .isInstanceOf(ResourceNotFoundException.class);
    }
}
