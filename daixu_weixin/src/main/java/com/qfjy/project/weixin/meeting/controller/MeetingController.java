package com.qfjy.project.weixin.meeting.controller;

import com.qfjy.mapper.MeetingtypeMapper;
import com.qfjy.po.Meetingpub;
import com.qfjy.po.Meetingtype;
import com.qfjy.service.MeetingTypeService;
import com.qfjy.service.MeetinggrabService;
import com.qfjy.service.MeetingpubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author daily
 * @version 1.0
 * @date 2019/11/28 11:48
 */
@Controller
@RequestMapping("meeting")
public class MeetingController {


    @Autowired
    private MeetingTypeService meetingTypeService;
    @Autowired
    private MeetingpubService meetingpubService;
    @Autowired
    private MeetinggrabService meetinggrabService;
    @RequestMapping("typeList")
    @ResponseBody
    public List<Meetingtype> getList(){
        return meetingTypeService.getMeetingType();
    }

    @RequestMapping("addPub")
    @ResponseBody
    public String addPubInfo(Meetingpub meetingpub){
        int num=meetingpubService.addMeetingpub(meetingpub);
        return num+"";
    }
    @RequestMapping("mypub")
    @ResponseBody
    public List<Meetingpub> getMeetingPubList(String uid){
        return meetingpubService.getMyMeetingpub(uid);
    }

    @RequestMapping("grab")
    public String toGrab(@RequestParam("uid") Integer uid, HttpServletRequest request){
        request.setAttribute("uid",uid);
        return "weixin/meeting/meetingGrab";
    }

    @RequestMapping("grabList")
    @ResponseBody
    public List<Meetingpub> grabLit(@RequestParam("uid") Integer uid,@RequestParam("tname") String tname){
        return meetingpubService.getUsefulPub(uid,tname);
    }

    @RequestMapping("myGrab")
    @ResponseBody
    public List<Meetingpub> myGrab(@RequestParam("uid") String uid){
        return meetingpubService.getMygrab(uid);
    }
}
