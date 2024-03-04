package com.guet.bishe.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 班级;
 * @author : cardo
 * @date : 2024-3-4
 */
@ApiModel(value = "班级",description = "")
@TableName("clas")
@Data
public class Clas implements Serializable {
    /** 主键 */
    @ApiModelProperty(name = "主键",notes = "")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @TableId(value = "id",type= IdType.AUTO)
    public Integer id ;
    /** 班级ID */
    @ApiModelProperty(name = "班级ID",notes = "")
    public String classId ;
    /** 班级名称 */
    @ApiModelProperty(name = "班级名称",notes = "")
    public String className ;
    /** 所在学院ID */
    @ApiModelProperty(name = "所在学院ID",notes = "")
    public String collegeId ;
    /** 所属专业ID */
    @ApiModelProperty(name = "所属专业ID",notes = "")
    public String majorId ;
    /** 所在年级ID */
    @ApiModelProperty(name = "所在年级ID",notes = "")
    public String gradeId ;
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

}