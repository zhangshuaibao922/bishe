package com.guet.bishe.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

 /**
 * 模版链接;
 * @author : cardo
 * @date : 2024-4-24
 */
@ApiModel(value = "模版链接",description = "")
@TableName("modelurl")
@Data
public class Modelurl implements Serializable{
    /** 主键 */
    @ApiModelProperty(name = "主键",notes = "")
    @TableId(value = "id",type= IdType.AUTO)
    public String id ;
    /** 答题卡id */
    @ApiModelProperty(name = "答题卡id",notes = "")
    public String paperClassId ;
    /** 链接 */
    @ApiModelProperty(name = "链接",notes = "")
    public String url ;

}