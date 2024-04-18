package com.guet.bishe.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author cardo
 * @Version 1.0
 * @Description TODO
 * @date 2024/4/18 16:58
 */
@ApiModel(value = "批阅设置",description = "")
@Data
public class TestModelDto {
    public Integer id ;
    public String teacherId;
    public String paperClassId;
}
