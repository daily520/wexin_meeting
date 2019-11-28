package com.qfjy.mapper;

import com.qfjy.po.Meetingpub;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MeetingpubMapper {
    int deleteByPrimaryKey(String id);

    int insert(Meetingpub record);

    int insertSelective(Meetingpub record);

    Meetingpub selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Meetingpub record);

    int updateByPrimaryKey(Meetingpub record);

    @Select("select max(pcode) from meetingpub where left(pcode,8)=#{ptime}")
    String maxPcodeByTime(String ptime);

    @Select("select * from meetingpub where uid=#{uid} order by pcode desc")
    List<Meetingpub> getMyMeetingpub(String uid);
}