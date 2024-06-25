package site.roomescape.springroomescaperevenge.member.domain.exception;

public class InvalidMemberEmailRegexException extends RuntimeException {

    public InvalidMemberEmailRegexException(final String message) {
        super(message);
    }
}
