package com.hotel.util;

import com.hotel.exception.ExceptionType;
import com.hotel.model.Result;

public class ResultReturn
{

    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(ExceptionType.DONE.getCode());
        result.setMsg(ExceptionType.DONE.getMsg());
        result.setData(object);
        return result;
    }

    public static Result success() {
        return success(null);
    }

    public static Result error(Integer code,String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
