package com.qfjy.service.impl;

import com.qfjy.mapper.MeetinggrabMapper;
import com.qfjy.po.Meetinggrab;
import com.qfjy.service.MeetinggrabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author daily
 * @version 1.0
 * @date 2019/11/30 15:04
 */
@Service
public class MeetinggrabServiceImpl implements MeetinggrabService {
    @Autowired
    private MeetinggrabMapper meetinggrabMapper;
    @Override
    public int insertSelective(Meetinggrab record) {
        record.setId(UUID.randomUUID().toString());
        record.setCreatedate(new Date());
        record.setGrabstatus(0);
        record.setStatus((short) 1);
        return meetinggrabMapper.insertSelective(record);
    }

    @Override
    public List<Meetinggrab> selectGrabListByPid(String pid) {
        return meetinggrabMapper.selectGrabList(pid);
    }

    @Override
    @Transactional
    public int chooseGrab(String pid, String uid) {
        int num=meetinggrabMapper.updateGrabFail(pid);
        if (num<1){
            throw new RuntimeException("更新失败状态失败");
        }

        num=meetinggrabMapper.updateGrabSucc(pid,uid);
        if (num<1){
            throw new RuntimeException("更新成功状态失败");
        }
        return num;
    }
}
