package com.hotel.handle;

public enum  handle
{
	DONE(1,"成功"),
	ORDER_HISTORY_FINDING_ERROR(500,"订单历史搜索失败"),
	;

	private int code;
	private String msg;

	handle(int code,String msg)
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
