package cn.edu.ecnu.controller;

import cn.edu.ecnu.convertor.UserConvertor;
import cn.edu.ecnu.exception.CustomizeException;
import cn.edu.ecnu.model.entity.User;
import cn.edu.ecnu.model.enums.ResultCodeEnum;
import cn.edu.ecnu.model.request.UserLoginRequest;
import cn.edu.ecnu.model.request.UserModifyRequest;
import cn.edu.ecnu.model.request.UserRegisterRequest;
import cn.edu.ecnu.model.response.UserLoginResponse;
import cn.edu.ecnu.model.response.UserQueryResponse;
import cn.edu.ecnu.service.UserService;
import cn.edu.ecnu.util.Result;
import cn.edu.ecnu.util.ResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Api("用户信息 Controller")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public Result<UserLoginResponse> login(@RequestBody UserLoginRequest userLoginRequest) {
        User user = userService.queryUserByUsername(userLoginRequest.getUsername());
        if (Objects.isNull(user)) {
            throw new CustomizeException(ResultCodeEnum.USERNAME_NOT_EXISTED);
        }
        boolean isMatched = bCryptPasswordEncoder
                .matches(userLoginRequest.getPassword(), user.getPassword());
        if (!isMatched) {
            throw new CustomizeException(ResultCodeEnum.PASSWORD_NOT_MATCHED);
        }
        UserLoginResponse userLoginResponse = UserConvertor.convertEntityToLoginResponse(user);
        return ResultGenerator.genSuccessResult(userLoginResponse);
    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public Result register(@RequestBody UserRegisterRequest userRegisterRequest) {
        User user = UserConvertor.convertRequestToEntity(userRegisterRequest);
        userService.register(user);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("修改用户信息")
    @PostMapping("/modify")
    public Result modifyUserInfo(@RequestBody UserModifyRequest userModifyRequest) {
        if (Objects.isNull(userModifyRequest) || Objects.isNull(userModifyRequest.getId())) {
            throw new CustomizeException(ResultCodeEnum.PARAM_ILLEGAL);
        }
        User user = UserConvertor.convertRequestToEntity(userModifyRequest);
        userService.modifyUserInfo(user);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("根据 ID 查询用户信息")
    @GetMapping("/{id}")
    public Result queryUserInfoById(@PathVariable Integer id) {
        User user = userService.queryUserById(id);
        UserQueryResponse userQueryResponse = UserConvertor.convertEntityToResponse(user);
        return ResultGenerator.genSuccessResult(userQueryResponse);
    }
}
