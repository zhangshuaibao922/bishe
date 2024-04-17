package com.guet.bishe.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

 /**
 * 得分;
 * @author : cardo
 * @date : 2024-4-17
 */
@ApiModel(value = "得分",description = "")
@TableName("score")
@Data
public class Score implements Serializable{
    /** 主键 */
    @ApiModelProperty(name = "主键",notes = "")
    @TableId(value = "id",type= IdType.AUTO)
    public Integer id ;
    /** 答题卡ID */
    @ApiModelProperty(name = "答题卡ID",notes = "")
    public String paperId ;
    /** 解答ID */
    @ApiModelProperty(name = "解答ID",notes = "")
    public String answerId ;
    /** 批改次数 */
    @ApiModelProperty(name = "批改次数",notes = "")
    public Integer num ;
    /** 得分 */
    @ApiModelProperty(name = "得分",notes = "")
    public Integer answerScore ;
    /** 批改教师 */
    @ApiModelProperty(name = "批改教师",notes = "")
    public String teacherId ;
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