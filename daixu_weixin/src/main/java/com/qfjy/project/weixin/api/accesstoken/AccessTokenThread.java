package com.qfjy.project.weixin.api.accesstoken;

import com.qfjy.project.weixin.main.MenuManager;
import com.qfjy.project.weixin.util.WeixinUtil;
import net.sf.json.JSONObject;

/**
 * @author daily
 * @version 1.0
 * @date 2019/11/22 16:45
 */
public class AccessTokenThread extends Thread {
    private static  final String AccessTokenUrl="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    public static String access_token;
    @Override
    public void run(){
        while (true){
            try {
                access_token=getAccessToken();
                System.out.println("token:"+access_token);
                Thread.sleep(6600000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private String getAccessToken(){
        String url=AccessTokenUrl.replace("APPID", MenuManager.appId).replace("APPSECRET",MenuManager.appSecret);
        JSONObject jsonObject= WeixinUtil.httpRequest(url,"GET",null);
        String token= (String) jsonObject.get("access_token");
        return token;
    }
}
