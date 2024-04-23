package com.guet.bishe.entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author cardo
 * @Version 1.0
 * @Description TODO
 * @date 2024/4/21 15:38
 */
@ApiModel(value = "考试设置",description = "")
@Data
public class SettingDto {
    /** 主键 */
    @ApiModelProperty(name = "主键",notes = "")
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
    @ApiModelProperty(name = "总题数",notes = "")
    public Integer answerNumbers ;
    /** 得分 */
    @ApiModelProperty(name = "得分",notes = "")
    public Integer count ;
    @ApiModelProperty(name = "是否批改完成",notes = "1完成 2未完成")
    public String allScore ;
}
