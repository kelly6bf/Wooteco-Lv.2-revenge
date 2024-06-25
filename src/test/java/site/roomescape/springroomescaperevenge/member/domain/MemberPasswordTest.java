package site.roomescape.springroomescaperevenge.member.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import site.roomescape.springroomescaperevenge.member.domain.exception.InvalidMemberPasswordLengthException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("회원 비밀번호 도메인 테스트")
class MemberPasswordTest {

    @DisplayName("유효한 회원 비밀번호 값이 입력되면 MemberPassword 객체를 생성한다.")
    @ValueSource(strings = {"userPw1234!", "aaaaaaaa", "aaaaaaaaaaaaa"})
    @ParameterizedTest
    void createMemberPassword(final String password) {
        // When
        final MemberPassword memberPassword = new MemberPassword(password);

        // Then
        assertThat(memberPassword).isNotNull();
        assertThat(memberPassword.value()).isEqualTo(password);
    }

    @DisplayName("회원 비밀번호 값으로 NULL 혹은 빈 값이 입력되면 예외를 발생시킨다.")
    @NullAndEmptySource
    @ParameterizedTest
    void createMemberPasswordWithNullOrEmptyValue(final String invalidInput) {
        // When & Then
        assertThatThrownBy(() -> new MemberPassword(invalidInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("회원 비밀번호 값은 NULL 혹은 공백일 수 없습니다.");
    }

    @DisplayName("유효하지 않은 길이의 회원 비밀번호 값이 입력되면 예외를 발생시킨다.")
    @ValueSource(strings = {"aaaaaaa", "aaaaaaaaaaaaaa"})
    @ParameterizedTest
    void createMemberPasswordWithInvalidLengthPasswordValue(final String invalidInput) {
        // When & Then
        assertThatThrownBy(() -> new MemberPassword(invalidInput))
                .isInstanceOf(InvalidMemberPasswordLengthException.class)
                .hasMessage("유효하지 않은 회원 비밀번호 길이입니다. - " + invalidInput);
    }
}
