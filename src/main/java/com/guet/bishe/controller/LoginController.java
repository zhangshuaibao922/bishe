package com.guet.bishe.controller;

import com.guet.bishe.Utils.EmailUtil;
import com.guet.bishe.entity.Authority;
import com.guet.bishe.entity.LoginDto;
import com.guet.bishe.entity.Response;
import com.guet.bishe.entity.User;
import com.guet.bishe.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 登录功能接口
 *
 * @author : cardo
 * @date : 2024-2-28
 */
@Api(tags = "登录功能接口")
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private EmailUtil emailUtil;

    /**
     * 登录
     *
     * @param loginDto 实例对象
     * @return 实例对象
     */
    @ApiOperation("登录")
    @PostMapping("/login")
    public Response<User> add(@RequestBody LoginDto loginDto) {
        return loginService.login(loginDto);
    }

    /**
     * 发送验证码
     *
     * @param
     * @return
     */
    @ApiOperation("验证码")
    @GetMapping("/verificationCode/{email}")
    public Response<Boolean> verificationCode(@PathVariable String email) {
        emailUtil.sendRegisterEmail(email);
        Response<Boolean> response = new Response<>();
        response.setData(true);
        return response;
    }

    /**
     * 修改密码
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @ApiOperation("修改密码")
    @PostMapping("/updatepassword")
    public ResponseEntity<String> add(@RequestBody User user) {
        return ResponseEntity.ok(loginService.updateByUser(user));
    }
}
