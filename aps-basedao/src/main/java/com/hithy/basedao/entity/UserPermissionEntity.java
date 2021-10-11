package com.hithy.basedao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2021-09-26 10:38:15
 */
@Data
@TableName("USER_PERMISSION")
public class UserPermissionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增id
	 */
	@TableId(type= IdType.AUTO)
	private Integer id;
	/**
	 * 权限名称
	 */
	private String permissionName;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
