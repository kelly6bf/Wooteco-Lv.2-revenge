package site.roomescape.springroomescaperevenge.common.api;

import lombok.Getter;

import java.util.List;

@Getter
public class MultipleSuccessResultApiResponse<T> extends SuccessApiResponse{

    private final List<T> data;

    public MultipleSuccessResultApiResponse(String message, List<T> data) {
        super(message);
        this.data = data;
    }
}
