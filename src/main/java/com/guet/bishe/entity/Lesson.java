package com.guet.bishe.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

 /**
 * 课程;
 * @author : cardo
 * @date : 2024-4-8
 */
@ApiModel(value = "课程",description = "")
@TableName("lesson")
@Data
public class Lesson implements Serializable{
    /** 主键 */
    @ApiModelProperty(name = "主键",notes = "")
    @TableId(value = "id",type= IdType.AUTO)
    public Integer id ;
    /** 课程ID */
    @ApiModelProperty(name = "课程ID",notes = "")
    public String lessonId ;
    /** 课程名 */
    @ApiModelProperty(name = "课程名",notes = "")
    public String lessonName ;
    /** 学时 */
    @ApiModelProperty(name = "学时",notes = "")
    public String hours ;
    /** 学分 */
    @ApiModelProperty(name = "学分",notes = "")
    public String score ;
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

     /** 课程ID */
     @ApiModelProperty(name = "学院ID",notes = "")
     public String collegeId ;
 }