package site.roomescape.springroomescaperevenge.common.api;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SuccessApiResponse {

    private final String message;
    private final LocalDateTime timestamp = LocalDateTime.now();

    public SuccessApiResponse(String message) {
        this.message = message;
    }
}
