package com.qfjy.service;

import com.qfjy.po.User;

/**
 * @author daily
 * @version 1.0
 * @date 2019/11/27 16:27
 */
public interface UserService {
    User getUserByWid(Integer wid);
    User getUserByEmail(String email);
    int updateByEmail(Integer wid,String email);
    int updateUser(User user);
}
