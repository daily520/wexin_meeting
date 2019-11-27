package com.qfjy.service;

import com.qfjy.po.Weiuser;

/**
 * @author daily
 * @version 1.0
 * @date 2019/11/26 17:42
 */
public interface WeiuserService {
    Weiuser selectByOpenid(String openid);
    int add(Weiuser record);

}
