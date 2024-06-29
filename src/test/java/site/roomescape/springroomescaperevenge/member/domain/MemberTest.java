package site.roomescape.springroomescaperevenge.member.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayName("회원 도메인 테스트")
class MemberTest {

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
}
