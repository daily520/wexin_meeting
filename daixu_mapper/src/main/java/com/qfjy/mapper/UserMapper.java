package com.qfjy.mapper;

import com.qfjy.po.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    @Select("select * from user where wid=#{wid}")
    User selectUserWid(Integer wid);

    @Select("select * from user where email=#{email}")
    User selectByEmail(String email);

    @Update("update user set wid=#{wid} where email=#{email}")
    int updateByEmail(Integer wid,String email);
}