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


    /***
     *微信Oauth2.0工具
     * 1.用户同意授权，获取code
     * 2.用户同意授权，页面将跳转至 redirect_uri/?code=CODE&state=STATE
     * 3.通过code换取网页授权access_token
     * 4.拉取用户信息(需scope为 snsapi_userinfo)
     */
    public static void oauthUrl(HttpServletResponse response,String s) throws IOException {
        //拼接redirect_uri
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
        //重定向获取code
        response.sendRedirect(url);
    }
    public static String getOpenid(HttpServletRequest request){
        //取得code值
        String code=request.getParameter("code");
        //通过code换取网页授权的access_token
        String url="https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid="+MenuManager.appId +
                "&secret="+MenuManager.appSecret +
                "&code="+code +
                "&grant_type=authorization_code";
        //获取到整个对象
        JSONObject jsonObject= WeixinUtil.httpRequest(url,"GET",null);
        System.out.println(jsonObject.toString());
        //获取得到用户openid并返回
        String openId= (String) jsonObject.get("openid");
        return openId;
    }
}
