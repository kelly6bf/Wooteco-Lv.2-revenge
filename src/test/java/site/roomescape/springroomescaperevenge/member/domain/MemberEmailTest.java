package site.roomescape.springroomescaperevenge.member.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import site.roomescape.springroomescaperevenge.member.domain.exception.InvalidMemberEmailRegexException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("회원 이메일 도메인 테스트")
class MemberEmailTest {

    @DisplayName("유효한 회원 이메일 값이 입력되면 MemberEmail 객체를 생성한다.")
    @Test
    void createMemberEmail() {
        // Given
        final String email = "tester@email.com";

        // When
        final MemberEmail memberEmail = new MemberEmail(email);

        // Then
        assertThat(memberEmail).isNotNull();
        assertThat(memberEmail.value()).isEqualTo(email);
    }

    @DisplayName("회원 이메일 값으로 NULL 혹은 빈 값이 입력되면 예외를 발생시킨다.")
    @NullAndEmptySource
    @ParameterizedTest
    void createMemberEmailWithNullOrEmptyValue(final String invalidInput) {
        // When & Then
        assertThatThrownBy(() -> new MemberEmail(invalidInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("회원 이메일 값은 NULL 혹은 공백일 수 없습니다.");
    }

    @DisplayName("유효하지 않은 이메일 형식의 값이 입력되면 예외를 발생시킨다.")
    @ValueSource(strings = {"tester", "tester!email.com", "tester@emailcom", "tester @ email . com"})
    @ParameterizedTest
    void createMemberEmailWithInvalidEmailRegexValue(final String invalidInput) {
        // When & Then
        assertThatThrownBy(() -> new MemberEmail(invalidInput))
                .isInstanceOf(InvalidMemberEmailRegexException.class)
                .hasMessage("회원 이메일의 형식이 유효하지 않습니다. - " + invalidInput);
    }
}
