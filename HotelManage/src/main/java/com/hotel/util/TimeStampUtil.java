package com.hotel.util;

import java.sql.Timestamp;

public class TimeStampUtil
{
	/**
	 * Timestamp形如
	 * yyyy-mm-dd hh:mm:ss.fffffffff
	 *
	 * @param timeStamp 时间戳
	 * @return 返回时间戳的各个部分组成的字符串组
	 */
	public String[] getStringArray(Timestamp timeStamp)
	{
		return timeStamp.toString().split("[ \\-:.]");
	}

	/**
	 * 形如yyyymmddhhmmssfffffffff
	 *
	 * @param timestamp 时间戳
	 * @return 返回时间戳字符串(纯数字无连接符)
	 */
	public String getString(Timestamp timestamp)
	{
		StringBuilder str=new StringBuilder();
		for (String string :getStringArray(timestamp))
		{
			str.append(string);
		}
		return str.toString();
	}

	/**
	 *
	 * @param timeStamp1 时间戳1
	 * @param timeStamp2 时间戳2
	 * @return 返回二者之间的小时差
	 */
	public int getHoursFromTwoTimeStamp(Timestamp timeStamp1,Timestamp timeStamp2)
	{
		if(timeStamp1.before(timeStamp2))
		{
			return (int)(timeStamp2.getTime()-timeStamp1.getTime())/(1000*60*60);
		}
		else
		{
			return (int)(timeStamp1.getTime()-timeStamp2.getTime())/(1000*60*60);
		}
	}

	/**
	 *
	 * @param timeStamp1 时间戳1
	 * @param timeStamp2 时间戳2
	 * @return 返回二者之间的天数差
	 */
	public int getDaysFromTwoTimeStamp(Timestamp timeStamp1,Timestamp timeStamp2)
	{
		if(timeStamp1.before(timeStamp2))
		{
			return (int)(timeStamp2.getTime()-timeStamp1.getTime())/(1000*60*60*24);
		}
		else
		{
			return (int)(timeStamp1.getTime()-timeStamp2.getTime())/(1000*60*60*24);
		}
	}

}
