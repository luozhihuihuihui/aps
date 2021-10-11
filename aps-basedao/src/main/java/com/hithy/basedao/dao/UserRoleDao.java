package com.hithy.basedao.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hithy.basedao.entity.UserRoleEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2021-09-26 10:38:15
 */
@Mapper
public interface UserRoleDao extends BaseMapper<UserRoleEntity> {
	
}
