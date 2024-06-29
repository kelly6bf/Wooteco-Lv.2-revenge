package site.roomescape.springroomescaperevenge.common.api;

import lombok.Getter;

@Getter
public class SingleSuccessResultApiResponse<T> extends SuccessApiResponse {

    private final T data;

    public SingleSuccessResultApiResponse(String message, T data) {
        super(message);
        this.data = data;
    }
}
