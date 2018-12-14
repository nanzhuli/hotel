package com.hotel.controller;

import com.hotel.model.Member;
import com.hotel.model.Result;
import com.hotel.service.MemberService;
import com.hotel.util.ResultReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class MemberController
{
	private final MemberService memberService;

	@Autowired
	public MemberController(MemberService memberService)
	{
		this.memberService=memberService;
	}

	/**
	 *
	 * @return 返回会员列表
	 */
	@RequestMapping("/member/getall")
	public Result<List<Member>> getAll()
	{
		return ResultReturn.success(memberService.findAll());
	}

	/**
	 *
	 * @param phone 手机号码
	 * @return 返回对应会员
	 */
	@RequestMapping("/member/getbyphone")
	public Result<Member> getByPhone(@RequestParam("phone") String phone)
	{
		return ResultReturn.success(memberService.findByPhone(phone));
	}

	/**
	 *
	 * @param id 身份证
	 * @return 返回对应会员
	 */
	@RequestMapping("/member/getbyid")
	public Result<Member> getByID(@RequestParam("id") String id)
	{
		return ResultReturn.success(memberService.findByID(id));
	}

	/**
	 *
	 * @param phone 电话号码
	 * @param name 姓名
	 * @param id 身份证
	 * @return 返回对应会员
	 */
	@RequestMapping("/member/insert")
	public Result<Member> insert(@RequestParam("phone") String phone,
								 @RequestParam("name") String name,
								 @RequestParam("id") String id)
	{
		Member member=new Member();

		member.setPhone(phone);
		member.setName(name);
		member.setId(id);
		member.setEntertime(new Timestamp(System.currentTimeMillis()));

		return ResultReturn.success(memberService.save(member));
	}

	/**
	 *
	 * @param phone 电话号码
	 * @return 返回成功
	 */
	@RequestMapping("/member/deletebyphone")
	public Result deleteByPhone(@RequestParam("phone") String phone)
	{
		memberService.delete(memberService.findByPhone(phone));
		return ResultReturn.success();
	}

	/**
	 *
	 * @param id 身份证
	 * @return 返回成功
	 */
	@RequestMapping("/member/deletebyid")
	public Result deleteByID(@RequestParam("id") String id)
	{
		memberService.delete(memberService.findByID(id));
		return ResultReturn.success();
	}
}
