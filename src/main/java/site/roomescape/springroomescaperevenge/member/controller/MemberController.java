package site.roomescape.springroomescaperevenge.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import site.roomescape.springroomescaperevenge.member.controller.request.CreateMemberRequest;
import site.roomescape.springroomescaperevenge.member.controller.response.MemberResponse;
import site.roomescape.springroomescaperevenge.member.domain.Member;
import site.roomescape.springroomescaperevenge.member.service.MemberService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members")
    public List<MemberResponse> getMembers() {
        return memberService.getAll()
                .stream()
                .map(MemberResponse::new)
                .toList();
    }

    @PostMapping("/members")
    public ResponseEntity<MemberResponse> create(@RequestBody final CreateMemberRequest request) {
        final Member member = memberService.create(request.toMemberCreate());
        final MemberResponse response = new MemberResponse(member);

        return ResponseEntity.ok(response);
    }
}
