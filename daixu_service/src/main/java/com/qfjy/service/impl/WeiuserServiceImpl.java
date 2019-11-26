package com.qfjy.service.impl;

import com.qfjy.mapper.WeiuserMapper;
import com.qfjy.po.Weiuser;
import com.qfjy.service.WeiuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author daily
 * @version 1.0
 * @date 2019/11/26 17:46
 */
@Service
public class WeiuserServiceImpl implements WeiuserService {
    @Resource
    private WeiuserMapper weiuserMapper;
    @Override
    public Weiuser selectByOpenid(String openid) {
        return weiuserMapper.selectByOpenid(openid);
    }

    @Override
    public int add(Weiuser record) {
        return weiuserMapper.insertSelective(record);
    }

}
