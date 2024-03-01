package com.guet.bishe.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


 /**
 * 专业;
 * @author : cardo
 * @date : 2024-3-1
 */
@ApiModel(value = "专业",description = "")
@TableName("major")
@Data
public class Major implements Serializable{
    /** 主键 */
    @ApiModelProperty(name = "主键",notes = "")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @TableId(value = "id",type= IdType.AUTO)
    public Integer id ;
    /** 专业ID */
    @ApiModelProperty(name = "专业ID",notes = "")
    public String majorId ;
    /** 专业名称 */
    @ApiModelProperty(name = "专业名称",notes = "")
    public String majorName ;
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