package com.connect.repository;

import com.connect.entity.Members;   // ✅ MUST BE PRESENT

import com.connect.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Members, Integer> {

    List<Members> findByUser(User user);

}