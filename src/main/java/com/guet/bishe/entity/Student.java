package com.guet.bishe.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

 /**
 * 学生;
 * @author : cardo
 * @date : 2024-3-5
 */
@ApiModel(value = "学生",description = "")
@TableName("student")
@Data
public class Student implements Serializable{
    /** 主键 */
    @ApiModelProperty(name = "主键",notes = "")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @TableId(value = "id",type= IdType.AUTO)
    public Integer id ;
    /** 所在班级ID */
    @ApiModelProperty(name = "所在班级ID",notes = "")
    public String classId ;
    /** 学生ID */
    @ApiModelProperty(name = "学生ID",notes = "")
    public String studentId ;
    /** 学生密码 */
    @ApiModelProperty(name = "学生密码",notes = "")
    public String studentPassword ;
    /** 学生姓名 */
    @ApiModelProperty(name = "学生姓名",notes = "")
    public String studentName ;
    /** 身份证号 */
    @ApiModelProperty(name = "身份证号",notes = "")
    public String idCardNo ;
    /** 手机号;11位手机号 */
    @ApiModelProperty(name = "手机号",notes = "11位手机号")
    public String mobilePhone ;
    /** 性别 */
    @ApiModelProperty(name = "性别",notes = "")
    public String gender ;
    /** 状态 */
    @ApiModelProperty(name = "状态",notes = "")
    public String status ;
    /** 权限id */
    @ApiModelProperty(name = "权限id",notes = "")
    public String authorityId ;
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

}