package site.roomescape.springroomescaperevenge.member.domain;

import site.roomescape.springroomescaperevenge.common.service.port.PasswordEncoder;

public record MemberPassword(String value) {

    public MemberPassword {
        validateNullOrEmpty(value);
    }

    private void validateNullOrEmpty(final String password) {
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("회원 비밀번호 값은 NULL 혹은 공백일 수 없습니다.");
        }
    }

    public MemberPassword encode(final PasswordEncoder passwordEncoder) {
        return new MemberPassword(passwordEncoder.encode(value));
    }
}
