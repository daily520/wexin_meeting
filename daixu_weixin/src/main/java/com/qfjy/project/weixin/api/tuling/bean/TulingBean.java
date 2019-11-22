package com.qfjy.project.weixin.api.tuling.bean;

import lombok.Data;

/**
 * @author daily
 * @version 1.0
 * @date 2019/11/21 11:23
 */
@Data
public class TulingBean {
    private  int reqType=0;
    private Perception perception;
    private UserInfo userInfo;
}
