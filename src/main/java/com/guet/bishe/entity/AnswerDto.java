package com.guet.bishe.entity;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
* 试卷;
* @author : cardo
* @date : 2024-4-17
*/
@ApiModel(value = "试卷",description = "")
@Data
public class AnswerDto implements Serializable{
   /** 答题卡ID */
   @ApiModelProperty(name = "答题卡ID",notes = "")
   public String paperId ;
   /** 解答ID */
   @ApiModelProperty(name = "解答ID",notes = "")
   public String answerId ;
   /** 解答链接 */
   @ApiModelProperty(name = "解答链接",notes = "")
   public String answerUrl ;
   @ApiModelProperty(name = "总分",notes = "")
   public Double avgScore;
}