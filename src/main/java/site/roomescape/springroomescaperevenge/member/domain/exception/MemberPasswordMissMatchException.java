package site.roomescape.springroomescaperevenge.member.domain.exception;

public class MemberPasswordMissMatchException extends RuntimeException {

    public MemberPasswordMissMatchException(final String message) {
        super(message);
    }
}
