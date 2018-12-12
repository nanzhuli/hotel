package com.hotel.exception;

public enum ExceptionType
{
	DONE(0,"成功"),

	GARAGE_FIND_BY_ID_ERROR(400,"未通过车库编号找到对应车库"),
	GARAGE_FIND_BY_BRAND_ERROR(401,"未通过车牌找到对应车库"),

	GARAGE_HISTORY_FIND_BY_ID_ERROR(500,"未通过车库编号找到对应历史纪录"),
	GARAGE_HISTORY_FIND_BY_BRAND_ERROR(501,"未通过车牌找到对应历史纪录"),

	ORDER_HISTORY_FIND_BY_DAY_ERROR(800,"未找到该月份对应的订单历史"),
	ORDER_HISTORY_FIND_BY_MONTH_ERROR(801,"未找到该日对应的订单历史"),
	ORDER_HISTORY_FIND_BY_YEAR_ERROR(802,"未找到该年份对应的订单历史"),
	ORDER_HISTORY_FIND_BY_ID_ERROR(803,"未找到该身份证对应的订单历史"),

	FINANCE_FIND_BY_DAY_ERROR(900,"日财务报表获取失败"),
	FINANCE_FIND_BY_MONTH_ERROR(901,"月财务报表获取失败"),
	FINANCE_FIND_BY_YEAR_ERROR(902,"年财务报表获取失败"),

	UNKNOWN_ERROR(-1,"未知错误"),
	;

	private int code;
	private String msg;

	ExceptionType(int code,String msg)
	{
		this.code=code;
		this.msg=msg;
	}

	public int getCode()
	{
		return code;
	}

	public void setCode(int code)
	{
		this.code=code;
	}

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg=msg;
	}
}
