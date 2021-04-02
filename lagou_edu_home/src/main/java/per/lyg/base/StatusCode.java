package per.lyg.base;

import com.alibaba.fastjson.JSONObject;

/**
 * @author 李沅罡
 */

public enum StatusCode {

    SUCCESS(0,"success"),FAIL(0,"fail");

    //定义属性
    private int code;
    private String msg;

    StatusCode() {
    }

    StatusCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        JSONObject object = new JSONObject();
        object.put("status",code);
        object.put("msg",msg);
        return object.toString();
    }
}
