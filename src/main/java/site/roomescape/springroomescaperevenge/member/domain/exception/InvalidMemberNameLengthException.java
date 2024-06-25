package site.roomescape.springroomescaperevenge.member.domain.exception;

public class InvalidMemberNameLengthException extends RuntimeException {

    public InvalidMemberNameLengthException(final String message) {
        super(message);
    }
}
