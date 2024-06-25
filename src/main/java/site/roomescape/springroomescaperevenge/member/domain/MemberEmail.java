package site.roomescape.springroomescaperevenge.member.domain;

import site.roomescape.springroomescaperevenge.member.domain.exception.InvalidMemberEmailRegexException;

import java.util.regex.Pattern;

public record MemberEmail(String value) {

    private static final Pattern REGEX_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9_+&*-]+(?:\\." +
                    "[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                    "A-Z]{2,7}$");

    public MemberEmail {
        validateNullOrEmpty(value);
        validateEmailRegex(value);
    }

    private void validateNullOrEmpty(final String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("회원 이메일 값은 NULL 혹은 공백일 수 없습니다.");
        }
    }

    private void validateEmailRegex(final String email) {
        if (!REGEX_PATTERN.matcher(email).matches()) {
            throw new InvalidMemberEmailRegexException("회원 이메일의 형식이 유효하지 않습니다. - " + email);
        }
    }
}
