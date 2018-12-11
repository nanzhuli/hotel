package com.hotel.exception;

public enum ExceptionType
{
	DONE(0,"成功"),
	GARAGE_FIND_BY_BRAND_ERROR(400,"未通过车牌找到目标车库"),
	ORDER_HISTORY_FINDING_ERROR(800,"订单历史搜索失败"),
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
