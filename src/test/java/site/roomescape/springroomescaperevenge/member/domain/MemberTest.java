package site.roomescape.springroomescaperevenge.member.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import site.roomescape.springroomescaperevenge.common.service.port.PasswordEncoder;
import site.roomescape.springroomescaperevenge.member.domain.exception.MemberPasswordMissMatchException;
import site.roomescape.springroomescaperevenge.mock.TestPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayName("회원 도메인 테스트")
class MemberTest {

    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        this.passwordEncoder = new TestPasswordEncoder();
    }

    @DisplayName("유효한 회원 정보를 입력받으면 회원 객체를 생성한다.")
    @Test
    void createMember() {
        // Given
        final MemberEmail email = new MemberEmail("kelly@email.com");
        final MemberPassword password = new MemberPassword("userPw1234!");
        final MemberName name = new MemberName("kelly");
        final MemberRole role = MemberRole.USER;

        // When
        final Member member = Member.builder()
                .email(email)
                .password(password)
                .name(name)
                .role(role)
                .build();

        // Then
        assertThat(member).isNotNull();
        assertSoftly(softly -> {
            softly.assertThat(member.getId()).isNull();
            softly.assertThat(member.getEmail()).isEqualTo(email);
            softly.assertThat(member.getPassword()).isEqualTo(password);
            softly.assertThat(member.getName()).isEqualTo(name);
            softly.assertThat(member.getRole()).isEqualTo(MemberRole.USER);
        });
    }

    @DisplayName("회원 권한 값으로 NULL을 입력받으면 예외를 발생시킨다.")
    @Test
    void createMemberWithNullMemberRole() {
        // Given
        final MemberEmail email = new MemberEmail("kelly@email.com");
        final MemberPassword password = new MemberPassword("userPw1234!");
        final MemberName name = new MemberName("kelly");
        final MemberRole invalidRole = null;

        // When & Then
        assertThatThrownBy(() -> Member.builder()
                .email(email)
                .password(password)
                .name(name)
                .role(invalidRole)
                .build())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("회원 권한 값은 NULL이 입력될 수 없습니다.");
    }

    @DisplayName("유효한 이메일 변경 정보를 입력받으면 회원 이메일 정보를 수정한다.")
    @Test
    void updateEmailTest() {
        // Given
        final Member member = Member.builder()
                .email(new MemberEmail("kelly6bf@gmail.com"))
                .password(new MemberPassword("kellyPw1234!"))
                .name(new MemberName("kelly"))
                .role(MemberRole.USER)
                .build();
        final MemberEmail newEmail = new MemberEmail("elmo@email.com");

        // When
        final Member updatedMember = member.updateEmail(newEmail);

        // Then
        assertThat(updatedMember.getEmail()).isEqualTo(newEmail);
    }

    @DisplayName("유효한 이름 변경 정보를 입력받으면 회원 이름 정보를 수정한다.")
    @Test
    void updateNameTest() {
        // Given
        final Member member = Member.builder()
                .email(new MemberEmail("kelly6bf@gmail.com"))
                .password(new MemberPassword("kellyPw1234!"))
                .name(new MemberName("kelly"))
                .role(MemberRole.USER)
                .build();
        final MemberName newName = new MemberName("elmo");

        // When
        final Member updatedMember = member.updateName(newName);

        // Then
        assertThat(updatedMember.getName()).isEqualTo(newName);
    }

    @DisplayName("유효한 비밀번호 변경 정보를 입력받으면 회원 비밀번호 정보를 수정한다.")
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
        final MemberPassword newPassword = new MemberPassword("elmoPw1234!");

        // When
        final Member updatedMember = member.changePassword(oldPassword, newPassword, passwordEncoder);

        // Then
        assertThat(passwordEncoder.matches(newPassword.value(), updatedMember.getPassword().value())).isTrue();
    }

    @DisplayName("잘못된 기존 비밀번호와 함께 변경을 요청받으면 예외를 발생시킨다.")
    @Test
    void changePasswordWithInvalidOldPasswordTest() {
        // Given
        final MemberPassword oldPassword = new MemberPassword("kellyPw1234!");
        final Member member = Member.builder()
                .email(new MemberEmail("kelly6bf@gmail.com"))
                .password(oldPassword.encode(passwordEncoder))
                .name(new MemberName("kelly"))
                .role(MemberRole.USER)
                .build();
        final MemberPassword newPassword = new MemberPassword("elmoPw1234!");
        final MemberPassword invalidOldPassword = new MemberPassword("invalidPw1234!");

        // When & Then
        assertThatThrownBy(() -> member.changePassword(invalidOldPassword, newPassword, passwordEncoder))
                .isInstanceOf(MemberPasswordMissMatchException.class)
                .hasMessage("회원 비밀번호가 일치하지 않습니다.");
    }
}
