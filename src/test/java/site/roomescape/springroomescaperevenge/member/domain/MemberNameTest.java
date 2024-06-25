package site.roomescape.springroomescaperevenge.member.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import site.roomescape.springroomescaperevenge.member.domain.exception.InvalidMemberNameLengthException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("회원 이름 도메인 테스트")
class MemberNameTest {

    @DisplayName("유효한 회원 이름 값이 입력되면 MemberName 객체를 생성한다.")
    @ValueSource(strings = {"chu", "pi", "kelly"})
    @ParameterizedTest
    void createMemberName(final String name) {
        // When
        final MemberName memberName = new MemberName(name);

        // Then
        assertThat(memberName).isNotNull();
        assertThat(memberName.value()).isEqualTo(name);
    }

    @DisplayName("회원 이름 값으로 NULL 혹은 빈 값이 입력되면 예외를 발생시킨다.")
    @NullAndEmptySource
    @ParameterizedTest
    void createMemberNameWithNullOrEmptyValue(final String invalidInput) {
        // When & Then
        assertThatThrownBy(() -> new MemberName(invalidInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("회원 이름 값은 NULL 혹은 공백일 수 없습니다.");
    }

    @DisplayName("유효하지 않은 길이의 회원 이름 값이 입력되면 예외를 발생시킨다.")
    @ValueSource(strings = {"a", "aaaaaa"})
    @ParameterizedTest
    void createMemberNameWithInvalidLengthNameValue(final String invalidInput) {
        // When & Then
        assertThatThrownBy(() -> new MemberName(invalidInput))
                .isInstanceOf(InvalidMemberNameLengthException.class)
                .hasMessage("유효하지 않은 회원 이름 길이입니다. - " + invalidInput);
    }
}
