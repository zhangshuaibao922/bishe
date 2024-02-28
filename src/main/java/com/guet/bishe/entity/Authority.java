package com.guet.bishe.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

 /**
 * 权限;
 * @author : cardo
 * @date : 2024-2-28
 */
@ApiModel(value = "权限",description = "")
@TableName("authority")
@Data
public class Authority implements Serializable{
    /** 主键id */
    @ApiModelProperty(name = "主键id",notes = "")
    @TableId(value = "id",type= IdType.AUTO)
    public Integer id ;
    /** 权限id */
    @ApiModelProperty(name = "权限id",notes = "")
    public String authorityId ;
    /** 角色 */
    @ApiModelProperty(name = "角色",notes = "")
    public String authorityRole ;
    /** 创建时间 */
    @ApiModelProperty(name = "创建时间",notes = "")
    @TableField(fill = FieldFill.INSERT)
    public String createTime ;
    /** 更新时间 */
    @ApiModelProperty(name = "更新时间",notes = "")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    public String updateTime ;

}