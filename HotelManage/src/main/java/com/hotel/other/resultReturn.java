package com.hotel.other;

import com.hotel.model.result;

public class resultReturn {

    public static result success(Object object) {
        result result = new result();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }

    public static result success() {
        return success(null);
    }

    public static result error(Integer code, String msg) {
        result result = new result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
