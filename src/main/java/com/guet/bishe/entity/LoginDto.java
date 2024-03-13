package com.guet.bishe.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 登录
 */
@ApiModel(value = "登录",description = "")
@Data
public class LoginDto {
    @ApiModelProperty(name = "账号类型",notes = "")
    public String identity;
    @ApiModelProperty(name = "账号",notes = "")
    public String username;
    @ApiModelProperty(name = "密码",notes = "")
    public String password;
}
