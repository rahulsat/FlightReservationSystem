package com.connect.service;

import com.connect.dto.requests.RequestMember;
import com.connect.dto.response.ResponseMember;
import com.connect.entity.Members;
import com.connect.entity.User;
import com.connect.repository.MemberRepository;
import com.connect.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final UserRepository userRepository;

    // ✅ ADD
    public ResponseMember addMember(RequestMember request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Members member = new Members();
        member.setName(request.getName());
        member.setAge(request.getAge());
        member.setPhoneNumber(request.getPhoneNumber());
        member.setUser(user);

        return mapToResponse(memberRepository.save(member));
    }

    // ✅ GET ALL
    public List<ResponseMember> getAllMembers() {
        return memberRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // ✅ PUT (FULL UPDATE)
    public ResponseMember updateMember(int id, RequestMember request) {
        // Use the 'id' passed from the Controller, NOT the one inside the request body
        Members member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + id));

        member.setName(request.getName());
        member.setAge(request.getAge());
        // ... update other fields

        memberRepository.save(member);
        return mapToResponse(member);
    }
    // ✅ PATCH (PARTIAL UPDATE)
    public ResponseMember patchMember(int id, RequestMember request) {
        // 1. Find the member (ensure 'id' isn't null if using Integer)
        Members member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + id));

        // 2. Partial updates for basic fields
        if (request.getName() != null && !request.getName().isBlank()) {
            member.setName(request.getName());
        }

        if (request.getPhoneNumber() != null && !request.getPhoneNumber().isBlank()) {
            member.setPhoneNumber(request.getPhoneNumber());
        }

        // Using > 0 is safer for IDs and Ages
        if (request.getAge() > 0) {
            member.setAge(request.getAge());
        }

        // 3. Handle the Relationship (The likely source of your error)
        // Check for both null (if Integer) and 0 (if int)
        Integer newUserId = request.getUserId();
        if (newUserId != null && newUserId > 0) {
            User user = userRepository.findById(newUserId)
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + newUserId));
            member.setUser(user);
        }

        // 4. Save and Map
        Members savedMember = memberRepository.save(member);
        return mapToResponse(savedMember);
    }

    // ✅ MAPPING
    private ResponseMember mapToResponse(Members member) {
        ResponseMember response = new ResponseMember();
        response.setName(member.getName());
        response.setAge(member.getAge());
        response.setPhoneNumber(member.getPhoneNumber());
        response.setUserId(member.getUser().getUser_id());
        response.setUserName(member.getUser().getName());
        return response;
    }

    public void deleteMember(int id) {
        // 1. Check if member exists
        if (!memberRepository.existsById(id)) {
            throw new RuntimeException("Cannot delete: Member not found with id: " + id);
        }

        // 2. Perform the deletion
        memberRepository.deleteById(id);
    }
}
