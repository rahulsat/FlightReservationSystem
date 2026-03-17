package com.connect.controller;

import com.connect.dto.requests.RequestMember;
import com.connect.dto.response.ResponseMember;
import com.connect.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // ✅ Add Member
    @PostMapping
    public ResponseMember addMember(@RequestBody RequestMember request) {
        return memberService.addMember(request);
    }

    // ✅ Get All Members
    @GetMapping
    public List<ResponseMember> getAllMembers() {
        return memberService.getAllMembers();
    }

    // ✅ Get Member By ID (IMPORTANT - add this)


    // ✅ Update Member (FULL UPDATE)
    @PutMapping("/{id}")
    public ResponseMember updateMember(
            @PathVariable int id,
            @RequestBody RequestMember request) {
        return memberService.updateMember(id, request);
    }

    // ✅ Patch Member (PARTIAL UPDATE)
    @PatchMapping("/{id}")
    public ResponseMember patchMember(
            @PathVariable int id,
            @RequestBody RequestMember request) {
        return memberService.patchMember(id, request);
    }

    // ✅ Delete Member (IMPORTANT)
    @DeleteMapping("/{id}")
    public String deleteMember(@PathVariable int id) {
        memberService.deleteMember(id);
        return "Member with ID " + id + " has been deleted successfully.";
    }

}