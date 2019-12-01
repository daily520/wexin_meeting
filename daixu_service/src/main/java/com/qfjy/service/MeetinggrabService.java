package com.qfjy.service;

import com.qfjy.po.Meetinggrab;

import java.util.List;

/**
 * @author daily
 * @version 1.0
 * @date 2019/11/30 15:03
 */
public interface MeetinggrabService {
    int insertSelective(Meetinggrab record);
    List<Meetinggrab> selectGrabListByPid(String pid);
    int chooseGrab(String pid,String uid);
}
