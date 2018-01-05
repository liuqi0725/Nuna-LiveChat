package com.liuqi.nuna.livechat.other.tool;

import com.alibaba.fastjson.JSONObject;
import com.liuqi.nuna.livechat.other.sys.SystemParams;
import org.apache.commons.lang.StringUtils;

import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 通用工具类
 */
public class CommonTools {

    /**
     * 常用 user-Agent
     */
    private static final String[] general_userAgent = new String[]{
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/22.0.1207.1 Safari/537.1",
            "Mozilla/5.0 (X11; CrOS i686 2268.111.0) AppleWebKit/536.11 (KHTML, like Gecko) Chrome/20.0.1132.57 Safari/536.11",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.6 (KHTML, like Gecko) Chrome/20.0.1092.0 Safari/536.6",
            "Mozilla/5.0 (Windows NT 6.2) AppleWebKit/536.6 (KHTML, like Gecko) Chrome/20.0.1090.0 Safari/536.6",
            "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/19.77.34.5 Safari/537.1",
            "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/536.5 (KHTML, like Gecko) Chrome/19.0.1084.9 Safari/536.5",
            "Mozilla/5.0 (Windows NT 6.0) AppleWebKit/536.5 (KHTML, like Gecko) Chrome/19.0.1084.36 Safari/536.5",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1063.0 Safari/536.3",
            "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1063.0 Safari/536.3",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_0) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1063.0 Safari/536.3",
            "Mozilla/5.0 (Windows NT 6.2) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1062.0 Safari/536.3",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1062.0 Safari/536.3",
            "Mozilla/5.0 (Windows NT 6.2) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1061.1 Safari/536.3",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1061.1 Safari/536.3",
            "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1061.1 Safari/536.3",
            "Mozilla/5.0 (Windows NT 6.2) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1061.0 Safari/536.3",
            "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.24 (KHTML, like Gecko) Chrome/19.0.1055.1 Safari/535.24",
            "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/535.24 (KHTML, like Gecko) Chrome/19.0.1055.1 Safari/535.24",
            "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36"
    };

    /**
     * @author liu.qi
     * @email liuqi_0725@aliyun.com
     * @date 2017/12/29 下午5:39
     * @param userAgent
     * @return boolean
     */
    public static boolean validationUserAgent(String userAgent){
        for(String agent : general_userAgent){
            if(userAgent.startsWith(agent.substring(0,20))){
                return true;
            }
        }

        return false;
    }

    /**
     * 生成用户 token
     * @param expiration
     * @param key
     * @param key_val
     * @return
     */
    public static String generate_user_token(Integer expiration,String key, Object key_val){

        JSONObject data = new JSONObject();
        data.put("expiration",expiration);
        data.put("createtime",System.currentTimeMillis());
        data.put("key",key);
        data.put("val",key_val);

        String token_key = StringUtils.isEmpty(SystemParams.CHAT_USER_TOKEN_KEY) ? "73FFEBA38BA3EE44923CA05FD46DA516" : SystemParams.CHAT_USER_TOKEN_KEY;

        DESTools des = new DESTools(token_key);
        try {
            return des.encrypt(data.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析用户 token
     * @param token
     * @param key
     * @param key_val
     * @return
     */
    public static boolean check_user_token(String token,String key, String key_val){

        String token_key = StringUtils.isEmpty(SystemParams.CHAT_USER_TOKEN_KEY) ? "73FFEBA38BA3EE44923CA05FD46DA516" : SystemParams.CHAT_USER_TOKEN_KEY;

        DESTools des = new DESTools(token_key);
        try {
            JSONObject data = JSONObject.parseObject(des.decrypt(token));
            //判断时间
            int expiration = data.getInteger("expiration");
            long createtime = data.getLong("createtime");
            long currenttime = System.currentTimeMillis();

            long bet = (currenttime - createtime) / 1000;

            //判断时限
            if( bet > expiration){
                return false;
            }

            //判断 key
            if(!data.getString("key").equals(key))
                return false;

            //判断 value
            if(!data.getString("val").equals(key_val))
                return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * str 转 md5
     * @param str 需要转换的字符串
     * @param str2Upper 是否转大写
     * @return 转化后的 md5字符串
     */
    public static String Str2MD5(String str, boolean str2Upper) {

        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.update((str).getBytes("UTF-8"));
            byte b[] = md5.digest();

            int i;
            StringBuffer buf = new StringBuffer("");

            for(int offset=0; offset<b.length; offset++){
                i = b[offset];
                if(i<0){
                    i+=256;
                }
                if(i<16){
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }

            if(str2Upper){
                return buf.toString().toUpperCase();
            }else{
                return buf.toString();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }

    public static void main(String[] args) {

//        String s = "201801051612";
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");
//        Date start = null;
//        try {
//            start = sdf.parse(s);
//            System.out.println(start);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println((System.currentTimeMillis() - start.getTime()) / 1000 +" s");

        System.out.println(CommonTools.Str2MD5("I love nuna-livechat,alexliu",true));

    }

}
