package com.qfjy.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class Meetinggrab implements Serializable {
    private String id;

    private String pid;

    private String remark;

    private String uid;

    private Date createdate;

    private Integer grabstatus;

    private Date grabdate;

    private Short status;


}