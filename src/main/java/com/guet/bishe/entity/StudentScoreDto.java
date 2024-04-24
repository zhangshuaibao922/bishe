package com.guet.bishe.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author cardo
 * @Version 1.0
 * @Description TODO
 * @date 2024/4/24 22:01
 */
@ApiModel(value = "学生成绩", description = "")
@Data
public class StudentScoreDto {
    @ApiModelProperty(name = "学号", notes = "")
    public String studentId;
    @ApiModelProperty(name = "姓名", notes = "")
    public String studentName;
    /** 考试ID */
    @ApiModelProperty(name = "考试ID",notes = "")
    public String examId ;
    @ApiModelProperty(name = "总分",notes = "")
    public Double totalScore ;
}
