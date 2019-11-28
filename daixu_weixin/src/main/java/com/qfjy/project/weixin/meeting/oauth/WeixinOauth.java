package com.qfjy.project.weixin.meeting.oauth;

import com.qfjy.mapper.MeetingtypeMapper;
import com.qfjy.po.Meetingtype;
import com.qfjy.po.User;
import com.qfjy.po.Weiuser;
import com.qfjy.project.weixin.main.MenuManager;
import com.qfjy.project.weixin.util.OauthUtil;
import com.qfjy.project.weixin.util.WeixinUtil;
import com.qfjy.service.MeetingTypeService;
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
@Controller
public class WeixinOauth {
    @Autowired
    private WeiuserService weiuserService;
    @Autowired
    private UserService userService;
    @Autowired
    private MeetingTypeService meetingTypeService;
    @RequestMapping("weixin/user")
    public void weixinOauth(HttpServletResponse response) throws IOException {
        OauthUtil.oauthUrl(response,"oauth/weixin/user/invoke");
    }
    @RequestMapping("weixin/user/invoke")
    public String invoke(HttpServletRequest request){

        String openId=OauthUtil.getOpenid(request);

        Weiuser weiuser= weiuserService.selectByOpenid(openId);
        if (weiuser==null){
            return "";
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


    }
    @RequestMapping("weixin/meetingPub")
    public void meetingPubOauth(HttpServletResponse response) throws IOException {
        OauthUtil.oauthUrl(response,"oauth/weixin/meetingPub/invoke");

    }

    @RequestMapping("weixin/meetingPub/invoke")
    public String meetingPubInvoke(HttpServletRequest request){
        String openId=OauthUtil.getOpenid(request);
        Weiuser weiuser=weiuserService.selectByOpenid(openId);
        if(weiuser==null){
            return "";
        }else {
            User user=userService.getUserByWid(weiuser.getId());
            if(user==null){
                request.setAttribute("wid",weiuser.getId());
                return "weixin/login";
            }else {
                int rid=user.getRid();
                if (1==rid){
                    request.setAttribute("uid",user.getId());
                    return "weixin/meeting/meetingPub";
                }else if(2==rid){
                    return "weixin/unauth";
                }else {
                    return "";
                }
            }
        }
    }
}
