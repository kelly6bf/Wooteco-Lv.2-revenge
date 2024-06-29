package site.roomescape.springroomescaperevenge.common.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(final String resource, final Long id) {
        super("해당 " + id + "의 " + resource + "정보가 존재하지 않습니다.");
    }
}
