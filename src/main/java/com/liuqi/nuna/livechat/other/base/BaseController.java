package com.liuqi.nuna.livechat.other.base;

import java.util.HashMap;
import java.util.Map;

public class BaseController {

    /**
     * @author liu.qi
     * @email liuqi_0725@aliyun.com
     * @date 2017/12/29 下午2:56
     * @param status, data, msg
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String, Object> renderJSON(boolean status, Object data, String msg){

        Map<String, Object> ret = new HashMap<String,Object>();
        ret.put("success", status);
        ret.put("data", data);
        ret.put("msg", msg);
        return ret;
    }
}
