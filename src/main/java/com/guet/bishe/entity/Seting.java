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
 * 考试设置;
 * @author : cardo
 * @date : 2024-4-17
 */
@ApiModel(value = "考试设置",description = "")
@TableName("seting")
@Data
public class Seting implements Serializable{
    /** 主键 */
    @ApiModelProperty(name = "主键",notes = "")
    @TableId(value = "id",type= IdType.AUTO)
    public Integer id ;
    /** 教师数量 */
    @ApiModelProperty(name = "教师数量",notes = "")
    public Integer num ;
    /** 考试设置 */
    @ApiModelProperty(name = "考试设置",notes = "")
    public String examSet ;
    /** 题目id */
    @ApiModelProperty(name = "题目id",notes = "")
    public String answerId ;
    /** 老师id */
    @ApiModelProperty(name = "老师id",notes = "")
    public String teacherId ;

}