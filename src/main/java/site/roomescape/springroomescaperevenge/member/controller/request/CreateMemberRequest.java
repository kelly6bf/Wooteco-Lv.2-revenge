package site.roomescape.springroomescaperevenge.member.controller.request;

import site.roomescape.springroomescaperevenge.member.domain.MemberCreate;

public record CreateMemberRequest(
        String email,
        String password,
        String name
) {
    public MemberCreate toMemberCreate() {
        return new MemberCreate(email, password, name);
    }
}
