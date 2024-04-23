package com.guet.bishe.entity;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

 /**
 * 试卷;
 * @author : cardo
 * @date : 2024-4-17
 */
@ApiModel(value = "试卷",description = "")
@TableName("answer")
@Data
public class Answer implements Serializable{
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
    /** 解答链接 */
    @ApiModelProperty(name = "解答链接",notes = "")
    public String answerUrl ;
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
     @ApiModelProperty(name = "是否批改",notes = "")
     public Integer isScore ;
 }