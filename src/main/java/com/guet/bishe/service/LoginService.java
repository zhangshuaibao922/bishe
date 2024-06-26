package com.guet.bishe.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.guet.bishe.entity.LoginDto;
import com.guet.bishe.entity.Response;
import com.guet.bishe.entity.User;

public interface LoginService extends IService<LoginDto> {
    Response<User> login(LoginDto loginDto);

    String updateByUser(User user);
}
