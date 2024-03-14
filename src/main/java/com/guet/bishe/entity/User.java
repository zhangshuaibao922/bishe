package com.guet.bishe.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "用户信息",description = "")
@Data
public class User {
    @ApiModelProperty(name = "名字",notes = "")
    public String name;
    @ApiModelProperty(name = "账号",notes = "")
    public String username;
    @ApiModelProperty(name = "旧密码",notes = "")
    public String oldPassword;
    @ApiModelProperty(name = "新密码",notes = "")
    public String newPassword;
    @ApiModelProperty(name = "个人简介",notes = "")
    public String description;
    @ApiModelProperty(name = "角色",notes = "")
    public String authorityRole ;
    @ApiModelProperty(name = "类别",notes = "")
    public String identity ;
}
