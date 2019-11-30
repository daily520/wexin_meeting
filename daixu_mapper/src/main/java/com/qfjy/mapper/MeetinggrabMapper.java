package com.qfjy.mapper;

import com.qfjy.po.Meetinggrab;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetinggrabMapper {
    int deleteByPrimaryKey(String id);

    int insert(Meetinggrab record);

    int insertSelective(Meetinggrab record);

    Meetinggrab selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Meetinggrab record);

    int updateByPrimaryKey(Meetinggrab record);
}