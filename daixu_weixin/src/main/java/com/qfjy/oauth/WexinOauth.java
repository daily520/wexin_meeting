package com.qfjy.oauth;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qfjy.project.weixin.main.MenuManager;
import com.qfjy.project.weixin.util.WeixinUtil;

import javax.servlet.http.HttpServletMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author daily
 * @version 1.0
 * @date 2019/11/26 16:37
 */
@RequestMapping("oauth")
@Controller
public class WexinOauth {
    @RequestMapping("weixin")
    public void weixinOauth(HttpServletResponse response) throws IOException {
        String oauth_url=MenuManager.REAL_URL+"oauth/invoke";
        try {
            oauth_url= URLEncoder.encode(oauth_url,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url="https://open.weixin.qq.com/connect/oauth2/authorize?" +
                "appid="+MenuManager.appId +
                "&redirect_uri="+oauth_url +
                "&response_type=code" +
                "&scope=snsapi_userinfo" +
                "&state=java#wechat_redirect";
        response.sendRedirect(url);
    }
    @RequestMapping("invoke")
    public String invoke(HttpServletRequest request){
        String code=request.getParameter("code");
        System.out.println(code);
        String state=request.getParameter("state");
        String url="https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid="+MenuManager.appId +
                "&secret="+MenuManager.appSecret +
                "&code="+code +
                "&grant_type=authorization_code";
        JSONObject jsonObject=WeixinUtil.httpRequest(url,"GET",null);
        System.out.println(jsonObject.toString());
        String access_token= (String) jsonObject.get("access_token");
        String openId= (String) jsonObject.get("openid");
        String userInfoUrl="https://api.weixin.qq.com/sns/userinfo" +
                "?access_token="+access_token +
                "&openid="+openId +
                "&lang=zh_CN";
        JSONObject userInfoObject=WeixinUtil.httpRequest(userInfoUrl,"GET",null);
        request.setAttribute("userinfo",userInfoObject);
        return "oauth";
    }
}
