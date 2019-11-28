package com.qfjy.mapper;

import com.qfjy.po.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    //通过微信用户的id查询数据库中已绑定的用户
    @Select("select * from user where wid=#{wid}")
    User selectUserWid(Integer wid);

    //通过email查询用户,用于绑定
    @Select("select * from user where email=#{email}")
    User selectByEmail(String email);

    //更新用户详细信息
    @Update("update user set wid=#{wid} where email=#{email}")
    int updateByEmail(Integer wid,String email);
}