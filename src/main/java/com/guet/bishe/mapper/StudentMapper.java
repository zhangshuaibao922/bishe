package com.guet.bishe.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guet.bishe.entity.Student;
import org.apache.ibatis.annotations.Mapper;


 /**
 * 学生;(student)表数据库访问层
 * @author : cardo
 * @date : 2024-3-5
 */
@Mapper
public interface StudentMapper  extends BaseMapper<Student>{
}