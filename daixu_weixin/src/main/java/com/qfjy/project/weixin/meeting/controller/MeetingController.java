package com.qfjy.project.weixin.meeting.controller;

import com.qfjy.mapper.MeetingtypeMapper;
import com.qfjy.po.Meetinggrab;
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
import java.util.Map;

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
    @RequestMapping("pub")
    public String toPub(@RequestParam("pid") String pid, @RequestParam("uid")String uid, Map<String,Object> map){
        map.put("pid",pid);
        map.put("uid",uid);
        return "weixin/meeting/choose";
    }
    @RequestMapping("chooseList")
    @ResponseBody
    public List<Meetinggrab> list(@RequestParam("pid")String pid){
        System.out.println(pid);
        System.out.println(meetinggrabService.selectGrabListByPid(pid));
        return meetinggrabService.selectGrabListByPid(pid);
    }
    @RequestMapping("choose")
    @ResponseBody
    public int choose(@RequestParam("pid") String pid, @RequestParam("uid")String uid){
        int num=0;
        try{
            num=meetinggrabService.chooseGrab(pid,uid);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
            e.getMessage();
        }
        return num;
    }
}
