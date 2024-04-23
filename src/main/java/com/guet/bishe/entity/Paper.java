package com.guet.bishe.entity;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


 /**
 * 答题卡;
 * @author : cardo
 * @date : 2024-4-17
 */
@ApiModel(value = "答题卡",description = "")
@TableName("paper")
@Data
public class Paper implements Serializable{
    /** 主键 */
    @ApiModelProperty(name = "主键",notes = "")
    @TableId(value = "id",type= IdType.AUTO)
    public Integer id ;
    /** 考试ID */
    @ApiModelProperty(name = "考试ID",notes = "")
    public String examId ;
    /** 学生ID */
    @ApiModelProperty(name = "学生ID",notes = "")
    public String studentId ;
    /** 答题卡ID */
    @ApiModelProperty(name = "答题卡ID",notes = "")
    public String paperId ;
    /** 答题卡链接 */
    @ApiModelProperty(name = "答题卡链接",notes = "")
    public String paperUrl ;
    /** 总分 */
    @ApiModelProperty(name = "总分",notes = "")
    public Integer totalScore ;
     /** 切割 */
     @ApiModelProperty(name = "切割",notes = "0没切割 1切割")
     public String cut ;
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

     /** 顺序 */
     @ApiModelProperty(name = "顺序",notes = "")
     public String sequence ;
}