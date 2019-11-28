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

    //根据用户的openid查询微信会员
    @Select("select * from weiuser where openid=#{openid}")
    Weiuser selectByOpenid(String openid);

}