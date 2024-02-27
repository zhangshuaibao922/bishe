package com.guet.bishe.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


 /**
 * 学院;
 * @author : cardo
 * @date : 2024-2-27
 */
@ApiModel(value = "学院",description = "")
@TableName("college")
@Data
public class College implements Serializable,Cloneable{
    /** 主键 */
    @ApiModelProperty(name = "主键",notes = "")
    @TableId(value = "id",type= IdType.AUTO)
    public Integer id ;
    /** 学院ID */
    @ApiModelProperty(name = "学院ID",notes = "")
    public String collegeId ;
    /** 学院名称 */
    @ApiModelProperty(name = "学院名称",notes = "")
    public String collegeName ;
    /** 创建时间 */
    @ApiModelProperty(name = "创建时间",notes = "")
    @TableField(fill = FieldFill.INSERT)
    public String createTime ;
    /** 更新时间 */
    @ApiModelProperty(name = "更新时间",notes = "")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    public String updateTime ;
}