package site.roomescape.springroomescaperevenge.member.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import site.roomescape.springroomescaperevenge.member.domain.exception.InvalidMemberPasswordLengthException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayName("회원 생성 도메인 테스트")
class MemberCreateTest {

    @DisplayName("유효한 회원 생성 정보를 입력받으면 MemberCreate 객체를 생성한다.")
    @ValueSource(strings = {"userPw1234!", "aaaaaaaa", "aaaaaaaaaaaaa"})
    @ParameterizedTest
    void createMember(final String password) {
        // Given
        final String email = "kelly@email.com";
        final String name = "kelly";

        // When
        final MemberCreate memberCreate = MemberCreate.builder()
                .email(email)
                .password(password)
                .name(name)
                .build();

        // Then
        assertThat(memberCreate).isNotNull();
        assertSoftly(softly -> {
            softly.assertThat(memberCreate.getEmail().value()).isEqualTo(email);
            softly.assertThat(memberCreate.getPassword().value()).isEqualTo(password);
            softly.assertThat(memberCreate.getName().value()).isEqualTo(name);
        });
    }

    @DisplayName("유효하지 않은 길이의 회원 비밀번호 값이 입력되면 예외를 발생시킨다.")
    @ValueSource(strings = {"aaaaaaa", "aaaaaaaaaaaaaa"})
    @ParameterizedTest
    void createMemberPasswordWithInvalidLengthPasswordValue(final String invalidInput) {
        // Given
        final String email = "kelly@email.com";
        final String name = "kelly";

        // When & Then
        assertThatThrownBy(() -> MemberCreate.builder()
                    .email(email)
                    .password(invalidInput)
                    .name(name)
                    .build())
                .isInstanceOf(InvalidMemberPasswordLengthException.class)
                .hasMessage("유효하지 않은 회원 비밀번호 길이입니다. - " + invalidInput);
    }
}
