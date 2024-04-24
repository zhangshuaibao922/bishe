package com.guet.bishe.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 考试;
 *
 * @author : cardo
 * @date : 2024-4-17
 */
@ApiModel(value = "考试", description = "")
@Data
public class ExamDto implements Serializable {
    /**
     * 主键
     */
    @ApiModelProperty(name = "主键", notes = "")
    public Integer id;
    /**
     * 考试ID
     */
    @ApiModelProperty(name = "考试ID", notes = "")
    public String examId;
    /**
     * 课程ID
     */
    @ApiModelProperty(name = "课程ID", notes = "")
    public String lessonId;
    /**
     * 考试名
     */
    @ApiModelProperty(name = "考试名", notes = "")
    public String examName;
    /**
     * 考试类别
     */
    @ApiModelProperty(name = "考试类别", notes = "1是测试2是考试")
    public String examClass;
    /**
     * 考试设置
     */
    @ApiModelProperty(name = "考试设置", notes = "")
    public String examSet;
    /**
     * 答题卡模版id
     */
    @ApiModelProperty(name = "答题卡模版id", notes = "")
    public String paperClassId;
    /**
     * 答题卡模版id
     */
    @ApiModelProperty(name = "答题卡模版", notes = "")
    public String modelName;

    /**
     * 考试日期
     */
    @ApiModelProperty(name = "考试日期", notes = "")
    public String examData;
    /**
     * 创建时间
     */
    @ApiModelProperty(name = "创建时间", notes = "")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String createTime;
    /**
     * 更新时间
     */
    @ApiModelProperty(name = "更新时间", notes = "")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String updateTime;

    /** 是否完成 */
    @ApiModelProperty(name = "是否完成",notes = "")
    public Integer isDelete ;

    /** 是否完成 */
    @ApiModelProperty(name = "链接",notes = "")
    public List<Modelurl> models;

}