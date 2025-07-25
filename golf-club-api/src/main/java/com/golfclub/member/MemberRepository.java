package com.golfclub.member;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByNameContainingIgnoreCase(String name);
    List<Member> findByPhoneNumber(String phoneNumber);
    List<Member> findByMembershipDurationInMonths(int duration);
}
