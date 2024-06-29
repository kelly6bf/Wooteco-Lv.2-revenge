package site.roomescape.springroomescaperevenge.member.domain;

import site.roomescape.springroomescaperevenge.member.domain.exception.InvalidMemberNameLengthException;

public record MemberName(String value) {

    private static final int MINIMUM_ENABLE_NAME_LENGTH = 2;
    private static final int MAXIMUM_ENABLE_NAME_LENGTH = 5;

    public MemberName {
        validateNullOrEmpty(value);
        validateNameLength(value);
    }

    private void validateNullOrEmpty(final String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("회원 이름 값은 NULL 혹은 공백일 수 없습니다.");
        }
    }

    private void validateNameLength(final String name) {
        final int nameLength = name.length();
        if (nameLength < MINIMUM_ENABLE_NAME_LENGTH
                || nameLength > MAXIMUM_ENABLE_NAME_LENGTH) {
            throw new InvalidMemberNameLengthException("유효하지 않은 회원 이름 길이입니다. - " + name);
        }
    }

    public boolean isEmpty() {
        return this.value == null || this.value.isBlank();
    }
}
