package com.qfjy.service.impl;

import com.qfjy.mapper.UserMapper;
import com.qfjy.po.User;
import com.qfjy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author daily
 * @version 1.0
 * @date 2019/11/27 16:28
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByWid(Integer wid) {
        return userMapper.selectUserWid(wid);
    }

    @Override
    public User getUserByEmail(String email) {
        return userMapper.selectByEmail(email);
    }

    @Override
    public int updateByEmail(Integer wid, String email) {
        return userMapper.updateByEmail(wid,email);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }
}
