package site.roomescape.springroomescaperevenge.member.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("회원 비밀번호 도메인 테스트")
class MemberPasswordTest {

    @DisplayName("회원 비밀번호 값으로 NULL 혹은 빈 값이 입력되면 예외를 발생시킨다.")
    @NullAndEmptySource
    @ParameterizedTest
    void createMemberPasswordWithNullOrEmptyValue(final String invalidInput) {
        // When & Then
        assertThatThrownBy(() -> new MemberPassword(invalidInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("회원 비밀번호 값은 NULL 혹은 공백일 수 없습니다.");
    }
}
