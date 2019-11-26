package com.qfjy.mapper;

import com.qfjy.po.Weiuser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface WeiuserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Weiuser record);

    int insertSelective(Weiuser record);

    Weiuser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Weiuser record);

    int updateByPrimaryKey(Weiuser record);

    @Select("select * from weiuser where openid=#{openid}")
    Weiuser selectByOpenid(String openid);

    @Insert("insert into weiuser values(default,#{openid},#{nickname},#{sex},#{city},#{country},#{province},#{headimgurl},#{subscribe},#{language},#{remark})")
    int saveWeiuser(Weiuser weiuser);
}