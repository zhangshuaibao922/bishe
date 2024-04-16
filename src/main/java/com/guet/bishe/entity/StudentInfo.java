package com.guet.bishe.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author cardo
 * @Version 1.0
 * @Description TODO
 * @date 2024/4/15 16:44
 */

@Data
public class StudentInfo {
    /** 课程id */
    @ApiModelProperty(name = "课程id",notes = "")
    public String lessonId ;
    /** 学生ID */
    @ApiModelProperty(name = "学生姓名",notes = "")
    public String studentId;
}
