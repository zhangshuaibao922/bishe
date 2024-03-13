package com.guet.bishe.controller;

import com.guet.bishe.entity.Authority;
import com.guet.bishe.entity.LoginDto;
import com.guet.bishe.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * 登录
     *
     * @param loginDto 实例对象
     * @return 实例对象
     */
    @ApiOperation("登录")
    @PostMapping("/login")
    public ResponseEntity<String> add(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(loginService.login(loginDto));
    }
}
