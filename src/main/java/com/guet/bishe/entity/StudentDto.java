package com.guet.bishe.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author cardo
 * @Version 1.0
 * @Description TODO
 * @date 2024/4/19 17:20
 */
@ApiModel(value = "学生答题卡dto", description = "")
@Data
public class StudentDto  implements Serializable {

    @ApiModelProperty(name = "学号", notes = "")
    public String studentId;
    @ApiModelProperty(name = "姓名", notes = "")
    public String studentName;
    @ApiModelProperty(name = "答题卡链接", notes = "")
    public String paperUrl;
}
