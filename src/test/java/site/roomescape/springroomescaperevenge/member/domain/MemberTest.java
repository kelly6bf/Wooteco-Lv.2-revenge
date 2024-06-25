package site.roomescape.springroomescaperevenge.member.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayName("회원 도메인 테스트")
class MemberTest {

    @DisplayName("유효한 회원 정보를 입력하면 회원 객체를 생성한다.")
    @Test
    void createMember() {
        // Given
        final String email = "kelly@email.com";
        final String password = "userPw1234!";
        final String name = "kelly";

        // When
        final Member member = Member.builder()
                .email(email)
                .password(password)
                .name(name)
                .build();

        // Then
        assertThat(member).isNotNull();
        assertSoftly(softly -> {
            softly.assertThat(member.getEmail().value()).isEqualTo(email);
            softly.assertThat(member.getPassword().value()).isEqualTo(password);
            softly.assertThat(member.getName().value()).isEqualTo(name);
        });
    }
}
