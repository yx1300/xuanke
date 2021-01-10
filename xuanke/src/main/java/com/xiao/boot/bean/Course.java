package com.xiao.boot.bean;

import lombok.Data;
import sun.dc.pr.PRError;

@Data
public class Course {
    private String id;
    private String name;
    private Integer total;
    private Integer num;
    private String teacher;
}
