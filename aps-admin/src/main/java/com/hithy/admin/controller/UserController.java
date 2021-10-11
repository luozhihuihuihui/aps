package com.hithy.admin.controller;

import com.hithy.admin.bo.RegisterBo;
import com.hithy.admin.service.UserService;
import com.hithy.basedao.entity.UserInfoEntity;
import com.hithy.common.ApsResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "用户管理")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "注册", notes = "邮箱、手机注册", httpMethod = "PUT")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "邮箱"),
            @ApiImplicitParam(name = "phone", value = "电话"),
            @ApiImplicitParam(name = "password", value = "密码"),
            @ApiImplicitParam(name = "code", value = "验证码")
    })
    @PutMapping("/register")
    public ApsResponse register(@RequestBody @Valid RegisterBo registerBo) {
        return userService.register(registerBo);
    }

    @ApiOperation(value = "获取验证码", notes = "通过邮箱获取验证码", httpMethod = "GET")
    @ApiImplicitParam(name = "email", value = "邮箱")
    @GetMapping("/code")
    public ApsResponse code(@RequestParam String email) {
        return userService.sendCode(email);
    }

    @ApiOperation(value = "获取用户列表", notes = "用户列表", httpMethod = "GET")
    @GetMapping("/users")
    public ApsResponse userList() {
        return ApsResponse.ok("list");
    }

    @ApiOperation(value = "更新信息", notes = "更新信息", httpMethod = "PUT")
    @PutMapping("/users/{id}")
    public ApsResponse updateUser(@PathVariable("id") Long id, @RequestBody UserInfoEntity userInfoEntity) {
        return ApsResponse.ok("add");
    }

    @ApiOperation(value = "查看用户信息", notes = "查看用户信息", httpMethod = "GET")
    @GetMapping("/users/{id}")
    public ApsResponse getUser(@PathVariable("id") Long id) {
        return ApsResponse.ok("get");
    }
}
