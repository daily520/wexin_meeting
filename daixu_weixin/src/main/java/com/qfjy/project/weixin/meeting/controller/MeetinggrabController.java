package com.qfjy.project.weixin.meeting.controller;

import com.qfjy.po.Meetinggrab;
import com.qfjy.service.MeetinggrabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author daily
 * @version 1.0
 * @date 2019/11/30 15:05
 */
//meetinggrab/tograb
@RequestMapping("meetinggrab")
@Controller
public class MeetinggrabController {
    @Autowired
    private MeetinggrabService meetinggrabService;
    @RequestMapping("tograb")
    public String toGrabPage(@RequestParam("uid")Integer uid,
                             @RequestParam("pid")String pid,
                             Map<String,Object> map){
        map.put("uid",uid);
        map.put("pid",pid);
        return "weixin/meeting/tograb";
    }
    @RequestMapping("add")
    public String addGrab(Meetinggrab meetinggrab,Map<String,Object> map){
        map.put("uid",meetinggrab.getUid());
        meetinggrabService.insertSelective(meetinggrab);
        return "weixin/meeting/meetingGrab";
    }
}
