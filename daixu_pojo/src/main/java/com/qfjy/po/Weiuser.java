package com.qfjy.po;

import lombok.Data;

import java.io.Serializable;
@Data
public class Weiuser implements Serializable {
    private Integer id;

    private String openid;

    private String nickname;

    private Integer sex;

    private String city;

    private String country;

    private String province;

    private String headimgurl;

    private Short subscribe;

    private String language;

    private String remark;

    private long subscribe_time;
    private String unionid;
    private int groupid;
    private String[] tagid_list;
    private String subscribe_scene;
    private String qr_scene_str;
    private String qr_scene;



}