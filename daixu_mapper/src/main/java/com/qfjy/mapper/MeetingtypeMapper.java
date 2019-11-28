package com.qfjy.mapper;

import com.qfjy.po.Meetingtype;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MeetingtypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Meetingtype record);

    int insertSelective(Meetingtype record);

    Meetingtype selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Meetingtype record);

    int updateByPrimaryKey(Meetingtype record);

    //查取所有的会议种类
    @Select("select * from meetingtype where status=1 order by sortnum")
    List<Meetingtype> selectAllMeetingType();
}