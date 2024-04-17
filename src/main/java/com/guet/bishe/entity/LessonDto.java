package com.guet.bishe.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author cardo
 * @Version 1.0
 * @Description TODO
 * @date 2024/4/16 16:22
 */
@ApiModel(value = "课程",description = "")
@Data
public class LessonDto {
    /** 主键 */
    @ApiModelProperty(name = "主键",notes = "")
    public Integer id ;
    /** 课程ID */
    @ApiModelProperty(name = "课程ID",notes = "")
    public String lessonId ;
    /** 课程名 */
    @ApiModelProperty(name = "课程名",notes = "")
    public String lessonName ;
    /** 学时 */
    @ApiModelProperty(name = "学时",notes = "")
    public String hours ;
    /** 学分 */
    @ApiModelProperty(name = "学分",notes = "")
    public String score ;
    /** 教师ID */
    @ApiModelProperty(name = "教师ID",notes = "")
    public String teacherId ;
    /** 教师ID */
    @ApiModelProperty(name = "教师名称",notes = "")
    public String teacherName ;
}
