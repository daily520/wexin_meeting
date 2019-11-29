package com.qfjy.project.weixin.meeting.controller;

import com.qfjy.po.User;
import com.qfjy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author daily
 * @version 1.0
 * @date 2019/11/27 16:31
 */
@RequestMapping("user")
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("login")
    @ResponseBody
    public String login(@RequestParam("email") String email,
                        @RequestParam("wid") Integer wid){
        User user=userService.getUserByEmail(email);
        if (user!=null){
            if (user.getWid()!=null){
                return "1";
            }else {
                userService.updateByEmail(wid,email);
                return "2";
            }
        }
        return "3";
    }

    @PostMapping("updateUser")
    @ResponseBody
    public String updateUserInfo(User user){
        int num=userService.updateUser(user);
        return num+"";
    }
    @RequestMapping("toLogin")
    public String toLogin(@RequestParam("wid") Integer wid, HttpServletRequest request){
        request.setAttribute("wid",wid);
        return "weixin/login";
    }
    @RequestMapping("toUnauth")
    public String toUnauth(){
        return "weixin/unauth";
    }
}
