package cn.edu.ecnu.controller;

import cn.edu.ecnu.convertor.UserConvertor;
import cn.edu.ecnu.model.entity.User;
import cn.edu.ecnu.model.request.UserLoginRequest;
import cn.edu.ecnu.model.request.UserRegisterRequest;
import cn.edu.ecnu.service.UserService;
import cn.edu.ecnu.util.Result;
import cn.edu.ecnu.util.ResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("用户信息 Controller")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /*@ApiOperation("用户登录")
    @PostMapping("/login")
    public Result login(@RequestBody UserLoginRequest userLoginRequest) {

        return ResultGenerator.genSuccessResult();
    }*/

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public Result register(@RequestBody UserRegisterRequest userRegisterRequest) {
        User user = UserConvertor.convertRequestToEntity(userRegisterRequest);
        userService.register(user);
        return ResultGenerator.genSuccessResult();
    }
}
