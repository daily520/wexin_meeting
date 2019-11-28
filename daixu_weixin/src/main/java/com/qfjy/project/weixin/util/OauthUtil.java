package com.qfjy.project.weixin.util;

import com.qfjy.project.weixin.main.MenuManager;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author daily
 * @version 1.0
 * @date 2019/11/28 13:59
 */
public class OauthUtil {
    public static void oauthUrl(HttpServletResponse response,String s) throws IOException {
        String oauth_url= MenuManager.REAL_URL+s;
        try {
            oauth_url= URLEncoder.encode(oauth_url,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url="https://open.weixin.qq.com/connect/oauth2/authorize?" +
                "appid="+MenuManager.appId +
                "&redirect_uri="+oauth_url +
                "&response_type=code" +
                "&scope=snsapi_base" +
                "&state=java#wechat_redirect";
        response.sendRedirect(url);
    }
    public static String getOpenid(HttpServletRequest request){
        String code=request.getParameter("code");
        String url="https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid="+MenuManager.appId +
                "&secret="+MenuManager.appSecret +
                "&code="+code +
                "&grant_type=authorization_code";
        JSONObject jsonObject= WeixinUtil.httpRequest(url,"GET",null);
        System.out.println(jsonObject.toString());

        String openId= (String) jsonObject.get("openid");
        return openId;
    }
}
