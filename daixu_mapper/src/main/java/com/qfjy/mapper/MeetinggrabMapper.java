package com.qfjy.mapper;

import com.qfjy.po.Meetinggrab;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetinggrabMapper {
    int deleteByPrimaryKey(String id);

    int insert(Meetinggrab record);

    int insertSelective(Meetinggrab record);

    Meetinggrab selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Meetinggrab record);

    int updateByPrimaryKey(Meetinggrab record);

    List<Meetinggrab> selectGrabList(String pid);

    @Update("update meetinggrab set grabStatus=2,grabDate=NOW() where pid=#{pid}")
    int updateGrabFail(String pid);
    @Update("update meetinggrab set grabStatus=1,grabDate=NOW() where pid=#{pid} and uid=#{uid}")
    int updateGrabSucc(String pid,String uid);
}