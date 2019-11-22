package com.qfjy.controller;

import com.qfjy.project.weixin.api.accesstoken.AccessTokenRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author daily
 * @version 1.0
 * @date 2019/11/22 17:56
 */
@RestController
public class TestController {
    @Autowired
    private AccessTokenRedis accessTokenRedis;

    @RequestMapping("token")
    public String test(){
        return accessTokenRedis.getRedisAccessToken();
    }
}
