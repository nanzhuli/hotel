package com.hotel.repository;

import com.hotel.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface MemberRepository extends JpaRepository<Member,Integer>
{
}
