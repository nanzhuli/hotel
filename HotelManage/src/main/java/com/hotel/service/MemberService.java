package com.hotel.service;

import com.hotel.exception.ExceptionType;
import com.hotel.exception.HotelException;
import com.hotel.model.Member;
import com.hotel.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService
{
	@Autowired
	MemberRepository memberRepository;

	public List<Member> findAll()
	{
		return memberRepository.findAll();
	}

	public Member findByPhone(String phone) throws HotelException
	{
		for (Member member : memberRepository.findAll())
		{
			if(member.getPhone().equals(phone))
			{
				return member;
			}
		}

		throw new HotelException(ExceptionType.MEMBER_FIND_BY_PHONE_ERROR.getCode(),
				ExceptionType.MEMBER_FIND_BY_PHONE_ERROR.getMsg());
	}

	public Member findByID(String id) throws HotelException
	{
		for (Member member : memberRepository.findAll())
		{
			if(member.getId().equals(id))
			{
				return member;
			}
		}

		throw new HotelException(ExceptionType.MEMBER_FIND_BY_ID_ERROR.getCode(),
				ExceptionType.MEMBER_FIND_BY_ID_ERROR.getMsg());
	}

	public Member save(Member member)
	{
		return memberRepository.save(member);
	}

	public void delete(Member member)
	{
		memberRepository.delete(member);
	}
}
