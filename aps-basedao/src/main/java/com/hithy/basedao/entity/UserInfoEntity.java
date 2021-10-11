package com.hithy.basedao.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2021-09-26 10:38:15
 */
@Data
@TableName("USER_INFO")
public class UserInfoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 唯一ID
     */
    @TableId
    private long id;
    /**
     * 密码
     */
    @JsonIgnore
    private String password;
    /**
     * 用户名称
     */
    private String name;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 电话
     */
    private String phone;
    /**
     * 出生日期
     */
    private Date born;
    /**
     * 头像
     */
    private String pic;
    /**
     * 简介
     */
    private String breif;
    /**
     * 创建时间
     */
    private Date createTime;

}
