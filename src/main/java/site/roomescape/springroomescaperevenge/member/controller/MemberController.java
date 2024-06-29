package site.roomescape.springroomescaperevenge.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.roomescape.springroomescaperevenge.member.controller.request.ChangeMemberPasswordRequest;
import site.roomescape.springroomescaperevenge.member.controller.request.CreateMemberRequest;
import site.roomescape.springroomescaperevenge.member.controller.request.UpdateMemberRequest;
import site.roomescape.springroomescaperevenge.member.controller.response.MemberResponse;
import site.roomescape.springroomescaperevenge.member.domain.Member;
import site.roomescape.springroomescaperevenge.member.domain.MemberPassword;
import site.roomescape.springroomescaperevenge.member.service.MemberService;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/members")
@RestController
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public List<MemberResponse> getMembers() {
        return memberService.getAll()
                .stream()
                .map(MemberResponse::new)
                .toList();
    }

    @PostMapping
    public ResponseEntity<MemberResponse> create(@RequestBody final CreateMemberRequest request) {
        final Member member = memberService.create(request.toMemberCreate());
        final MemberResponse response = new MemberResponse(member);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{memberId}")
    public ResponseEntity<MemberResponse> update(
            @PathVariable("memberId") final Long memberId,
            @RequestBody final UpdateMemberRequest request
    ) {
        final Member updatedMember = memberService.update(memberId, request.toMemberUpdate());
        final MemberResponse response = new MemberResponse(updatedMember);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{memberId}/password")
    public ResponseEntity<String> changePassword(
            @PathVariable("memberId") final Long memberId,
            @RequestBody final ChangeMemberPasswordRequest request
    ) {
        memberService.changePassword(
                memberId,
                new MemberPassword(request.oldPassword()),
                new MemberPassword(request.newPassword())
        );

        return ResponseEntity.ok("비밀번호 변경되었다.");
    }

    @DeleteMapping("/{memberId}")
    public void delete(@PathVariable("memberId") final Long memberId) {
        memberService.delete(memberId);
    }
}
