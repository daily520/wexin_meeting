package com.qfjy.project.weixin.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qfjy.po.Weiuser;
import com.qfjy.project.weixin.api.accesstoken.AccessTokenRedis;
import com.qfjy.service.WeiuserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import com.qfjy.project.weixin.util.WeixinUtil;
import org.springframework.stereotype.Service;

/**
 * @author daily
 * @version 1.0
 * @date 2019/11/26 17:50
 */
@Service
public class UserInfoService {
    private static final String GetUserInfo_URL="https://api.weixin.qq.com/cgi-bin/user/info" +
            "?access_token=ACCESS_TOKEN" +
            "&openid=OPENID&lang=zh_CN";
    @Autowired
    private AccessTokenRedis accessTokenRedis;
    @Autowired
    private WeiuserService weiuserService;
    public JSONObject getUserInfo(String openid){
        String url=GetUserInfo_URL.replace("OPENID",openid)
                .replace("ACCESS_TOKEN",accessTokenRedis.getRedisAccessToken());
        JSONObject jsonObject=WeixinUtil.httpRequest(url,"GET",null);
        return jsonObject;
    }
    public Weiuser transferWeiuser(JSONObject jsonObject){
        ObjectMapper objectMapper=new ObjectMapper();
        Weiuser weiuser=null;
        try {
            weiuser=objectMapper.readValue(jsonObject.toString(),Weiuser.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return weiuser;
    }
    public void userInfo(String openid){
        Weiuser weiuserObject=weiuserService.selectByOpenid(openid);
        if(weiuserObject==null){
            JSONObject jsonObject=getUserInfo(openid);
            Weiuser weiuser=transferWeiuser(jsonObject);
            weiuserService.add(weiuser);
        }

    }

}
