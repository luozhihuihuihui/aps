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
 * @date 2021-09-26 17:10:32
 */
@Data
@TableName("USER_ROLE_BIND")
public class UserRoleBindEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type= IdType.AUTO)
	private Integer id;
	/**
	 * 
	 */
	private Long userId;
	/**
	 * 
	 */
	private Integer roleId;
	/**
	 * 
	 */
	private Date createTime;

}
