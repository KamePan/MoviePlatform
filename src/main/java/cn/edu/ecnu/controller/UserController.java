package cn.edu.ecnu.controller;

import cn.edu.ecnu.model.request.LoginRequest;
import cn.edu.ecnu.model.request.RegisterRequest;
import cn.edu.ecnu.util.Result;
import cn.edu.ecnu.util.ResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("用户信息 Controller")
@RestController
@RequestMapping("/user")
public class UserController {

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public Result<Void> login(LoginRequest loginRequest) {

        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public Result<Void> register(RegisterRequest registerRequest) {

        return ResultGenerator.genSuccessResult();
    }
}
