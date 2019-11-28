package com.qfjy.project.weixin.meeting.oauth;

import com.qfjy.po.User;
import com.qfjy.po.Weiuser;
import com.qfjy.project.weixin.main.MenuManager;
import com.qfjy.project.weixin.util.WeixinUtil;
import com.qfjy.service.UserService;
import com.qfjy.service.WeiuserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author daily
 * @version 1.0
 * @date 2019/11/27 16:31
 */
@RequestMapping("oauth")
@Controller("weixinOauth1")
public class WeixinOauth {
    @Autowired
    private WeiuserService weiuserService;
    @Autowired
    private UserService userService;
    @RequestMapping("weixin/user")
    public void weixinOauth(HttpServletResponse response) throws IOException {
        String oauth_url= MenuManager.REAL_URL+"oauth/weixin/user/invoke";
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
    @RequestMapping("weixin/user/invoke")
    public String invoke(HttpServletRequest request){
        String code=request.getParameter("code");
        String url="https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid="+MenuManager.appId +
                "&secret="+MenuManager.appSecret +
                "&code="+code +
                "&grant_type=authorization_code";
        JSONObject jsonObject= WeixinUtil.httpRequest(url,"GET",null);
        System.out.println(jsonObject.toString());

        String openId= (String) jsonObject.get("openid");

        Weiuser weiuser= weiuserService.selectByOpenid(openId);
        if (weiuser==null){

        }else {
            User user=userService.getUserByWid(weiuser.getId());
            if (user==null){
                request.setAttribute("wid",weiuser.getId());
                return "weixin/login";
            }else {
                request.setAttribute("user",user);
                return "weixin/user/userInfo";
            }
        }

        return "oauth";
    }
}
