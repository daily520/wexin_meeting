package com.qfjy.service.impl;

import com.qfjy.mapper.MeetingpubMapper;
import com.qfjy.po.Meetingpub;
import com.qfjy.service.MeetingpubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author daily
 * @version 1.0
 * @date 2019/11/28 17:00
 */
@Service
public class MeetingpubServiceImpl implements MeetingpubService {
    @Autowired
    private MeetingpubMapper meetingpubMapper;
    @Override
    public int addMeetingpub(Meetingpub meetingpub) {
        meetingpub.setId(UUID.randomUUID().toString());
        meetingpub.setCreatedate(new Date());
        meetingpub.setPcode(generateTme(meetingpub.getPtime()));
        meetingpub.setStatus((short)1);
        return meetingpubMapper.insertSelective(meetingpub);
    }

    @Override
    public String generateTme(String ptime) {
        String str=ptime.substring(0,10);
        str=str.replace("-","");
        String result=meetingpubMapper.maxPcodeByTime(str);
        if (StringUtils.isEmpty(result)){
            return str+"001";
        }else {
            Long l=Long.parseLong(result)+1;
            return l.toString();
        }
    }

    @Override
    public List<Meetingpub> getMyMeetingpub(String uid) {
        return meetingpubMapper.getMyMeetingpub(uid);
    }
}
