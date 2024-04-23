package com.guet.bishe.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author cardo
 * @Version 1.0
 * @Description TODO
 * @date 2024/4/22 16:19
 */
@ApiModel(value = "批阅设置",description = "")
@Data
public class ExamModelDto {
    public int id;
    public String teacherId;
    public String paperClassId;
    public String[][] data;
}
