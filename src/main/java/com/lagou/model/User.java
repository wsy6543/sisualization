package com.lagou.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data                // 提供set get方法和toString
@AllArgsConstructor  //添加全参构造方法
@NoArgsConstructor   //添加无参构造方法
public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String phone;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date  createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
