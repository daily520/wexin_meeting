package com.qfjy.mapper;

import com.qfjy.po.Meetingtype;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MeetingtypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Meetingtype record);

    int insertSelective(Meetingtype record);

    Meetingtype selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Meetingtype record);

    int updateByPrimaryKey(Meetingtype record);

    @Select("select * from meetingtype where status=1 order by sortnum")
    List<Meetingtype> selectAllMeetingType();
}