package com.guet.bishe.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author cardo
 * @Version 1.0
 * @Description TODO
 * @date 2024/4/24 15:39
 */
@ApiModel(value = "考试设置",description = "")
@Data
public class SettingDesDto {
    /** 考试设置 */
    @ApiModelProperty(name = "考试设置",notes = "")
    public String examSet ;
    /** 题目id */
    @ApiModelProperty(name = "题目id",notes = "")
    public String answerId ;
    /** 答案备注 */
    @ApiModelProperty(name = "答案备注",notes = "")
    public String description ;
}
