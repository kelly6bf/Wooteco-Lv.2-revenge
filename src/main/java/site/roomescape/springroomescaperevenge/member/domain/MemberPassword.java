package site.roomescape.springroomescaperevenge.member.domain;

import site.roomescape.springroomescaperevenge.member.domain.exception.InvalidMemberPasswordLengthException;

public record MemberPassword(String value) {

    private static final int MINIMUM_ENABLE_PASSWORD_LENGTH = 8;
    private static final int MAXIMUM_ENABLE_PASSWORD_LENGTH = 13;

    public MemberPassword {
        validateNullOrEmpty(value);
        validatePasswordLength(value);
    }

    private void validateNullOrEmpty(final String password) {
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("회원 비밀번호 값은 NULL 혹은 공백일 수 없습니다.");
        }
    }

    private void validatePasswordLength(final String password) {
        final int passwordLength = password.length();
        if (passwordLength < MINIMUM_ENABLE_PASSWORD_LENGTH
                || passwordLength > MAXIMUM_ENABLE_PASSWORD_LENGTH) {
            throw new InvalidMemberPasswordLengthException("유효하지 않은 회원 비밀번호 길이입니다. - " + password);
        }
    }
}
