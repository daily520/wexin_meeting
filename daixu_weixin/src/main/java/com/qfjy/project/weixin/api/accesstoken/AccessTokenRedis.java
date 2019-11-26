package com.qfjy.project.weixin.api.accesstoken;

import com.qfjy.project.weixin.main.MenuManager;
import com.qfjy.project.weixin.util.WeixinUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author daily
 * @version 1.0
 * @date 2019/11/22 17:43
 */
@Service
public class AccessTokenRedis {
    @Resource
    private RedisTemplate<String,Object> redisTemplate;
    @Resource(name = "redisTemplate")
    private ValueOperations<String,String> stringTemplate;
    private static final String ACCESS_TOKEN_KEY="access_token";
    private static  final String AccessTokenUrl="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    /**
     * 获取access_token时,判断是否存在
     * 存在获得后返回
     * 不存在,先请求后存到redis并设置过期时间
     */
    public String getRedisAccessToken(){
        if (redisTemplate.hasKey(ACCESS_TOKEN_KEY)){
            String token=stringTemplate.get(ACCESS_TOKEN_KEY);
            System.out.println("redis"+token);
            return token;
        }else {
            String token=getAccessToken();
            System.out.println("wexin"+token);
            stringTemplate.set(ACCESS_TOKEN_KEY,token,110, TimeUnit.MINUTES);
            return token;
        }
    }

    private String getAccessToken(){
        String url=AccessTokenUrl.replace("APPID", MenuManager.appId).replace("APPSECRET",MenuManager.appSecret);

        JSONObject jsonObject= WeixinUtil.httpRequest(url,"GET",null);

        String token= (String) jsonObject.get("access_token");
        return token;
    }
}
