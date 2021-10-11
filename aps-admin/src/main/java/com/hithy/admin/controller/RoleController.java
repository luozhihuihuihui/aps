package com.hithy.admin.controller;

import com.hithy.admin.bo.RoleBo;
import com.hithy.admin.service.RoleService;
import com.hithy.common.ApsResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "角色管理")
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @ApiOperation("新增角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleName", value = "角色名称"),
            @ApiImplicitParam(name = "permissionId", value = "权限列表")
    })
    @PutMapping("/add")
    @ResponseBody
    public ApsResponse add(@RequestBody @Valid RoleBo roleBo) {
        return roleService.add(roleBo);
    }



}
