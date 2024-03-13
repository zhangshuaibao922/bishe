package com.guet.bishe.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.guet.bishe.entity.LoginDto;

public interface LoginService extends IService<LoginDto> {
    String login(LoginDto loginDto);
}
