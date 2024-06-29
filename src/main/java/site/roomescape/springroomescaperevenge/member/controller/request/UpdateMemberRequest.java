package site.roomescape.springroomescaperevenge.member.controller.request;

import site.roomescape.springroomescaperevenge.member.domain.MemberUpdate;

public record UpdateMemberRequest(
        String email,
        String name
) {
    public MemberUpdate toMemberUpdate() {
        return new MemberUpdate(email, name);
    }
}
