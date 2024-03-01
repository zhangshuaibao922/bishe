package com.guet.bishe.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guet.bishe.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;


/**
 * 教师;(teacher)表数据库访问层
 *
 * @author : cardo
 * @date : 2024-2-28
 */

@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {
}