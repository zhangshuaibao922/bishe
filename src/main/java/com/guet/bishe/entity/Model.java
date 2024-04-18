package com.guet.bishe.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

 /**
 * 模版;
 * @author : cardo
 * @date : 2024-4-18
 */
@ApiModel(value = "模版",description = "")
@TableName("model")
@Data
public class Model implements Serializable{
    /** 主键 */
    @ApiModelProperty(name = "主键",notes = "")
    @TableId(value = "id",type= IdType.AUTO)
    public Integer id ;
     /** 答题卡模版名称 */
     @ApiModelProperty(name = "答题卡模版名称",notes = "")
     public String modelName ;
     /** 模版类型1是test2是考试 */
     @ApiModelProperty(name = "模版类型1是test2是考试",notes = "")
     public String modelClass ;
     /** 答题卡id */
     @ApiModelProperty(name = "答题卡id",notes = "")
     public String paperClassId ;
     /** 数量 */
     @ApiModelProperty(name = "数量",notes = "")
     public String modelNumber ;

}