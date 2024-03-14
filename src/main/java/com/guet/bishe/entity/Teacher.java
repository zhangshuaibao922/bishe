package com.guet.bishe.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

 /**
 * 教师;
 * @author : cardo
 * @date : 2024-2-28
 */
@ApiModel(value = "教师",description = "")
@TableName("teacher")
@Data
public class Teacher implements Serializable{
    /** 主键 */
    @ApiModelProperty(name = "主键",notes = "")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @TableId(value = "id",type= IdType.AUTO)
    public Integer id ;
    /** 所在学院ID */
    @ApiModelProperty(name = "所在学院ID",notes = "")
    public String collegeId ;
    /** 教师ID */
    @ApiModelProperty(name = "教师ID",notes = "")
    public String teacherId ;
    /** 姓名 */
    @ApiModelProperty(name = "姓名",notes = "")
    public String teacherName ;
    /** 密码 */
    @ApiModelProperty(name = "密码",notes = "")
    public String teacherPassword ;
    /** 身份证号 */
    @ApiModelProperty(name = "身份证号",notes = "")
    public String idCardNo ;
    /** 手机号 */
    @ApiModelProperty(name = "手机号",notes = "")
    public String mobilePhone ;
    /** 权限id */
    @ApiModelProperty(name = "权限id",notes = "")
    public String authorityId ;
    /** 状态 */
    @ApiModelProperty(name = "状态",notes = "")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String status ;
    /** 创建时间 */
    @ApiModelProperty(name = "创建时间",notes = "")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @TableField(fill = FieldFill.INSERT)
    public String createTime ;
    /** 更新时间 */
    @ApiModelProperty(name = "更新时间",notes = "")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @TableField(fill = FieldFill.INSERT_UPDATE)
    public String updateTime ;
     /** 个人简介 */
     @ApiModelProperty(name = "个人简介",notes = "")
     public String description ;
}