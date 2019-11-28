package com.qfjy.service.impl;

import com.qfjy.mapper.MeetingtypeMapper;
import com.qfjy.po.Meetingtype;
import com.qfjy.service.MeetingTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author daily
 * @version 1.0
 * @date 2019/11/28 11:40
 */
@Service
public class MeetingTypeServiceImpl implements MeetingTypeService {
    @Autowired
    private MeetingtypeMapper meetingtypeMapper;
    @Override
    public List<Meetingtype> getMeetingType() {
        return meetingtypeMapper.selectAllMeetingType();
    }
}
