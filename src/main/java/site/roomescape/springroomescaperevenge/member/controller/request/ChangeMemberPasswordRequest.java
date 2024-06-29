package site.roomescape.springroomescaperevenge.member.controller.request;

public record ChangeMemberPasswordRequest(
        String oldPassword,
        String newPassword
) {
}
