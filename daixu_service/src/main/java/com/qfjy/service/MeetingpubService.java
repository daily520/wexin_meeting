package com.qfjy.service;

import com.qfjy.po.Meetingpub;

import java.util.List;

/**
 * @author daily
 * @version 1.0
 * @date 2019/11/28 16:59
 */
public interface MeetingpubService {
    int addMeetingpub(Meetingpub meetingpub);
    String generateTme(String ptime);
    List<Meetingpub> getMyMeetingpub(String uid);
    List<Meetingpub> getUsefulPub(Integer uid,String tname);
    List<Meetingpub> getMygrab(String uid);
}
