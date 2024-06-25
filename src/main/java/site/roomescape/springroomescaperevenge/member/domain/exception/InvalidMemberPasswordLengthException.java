package site.roomescape.springroomescaperevenge.member.domain.exception;

public class InvalidMemberPasswordLengthException extends RuntimeException {

    public InvalidMemberPasswordLengthException(final String message) {
        super(message);
    }
}
