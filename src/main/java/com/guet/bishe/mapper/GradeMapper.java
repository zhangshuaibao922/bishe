package com.guet.bishe.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guet.bishe.entity.Grade;
import org.apache.ibatis.annotations.Mapper;

 /**
 * 年级;(grade)表数据库访问层
 * @author : cardo
 * @date : 2024-3-4
 */
@Mapper
public interface GradeMapper  extends BaseMapper<Grade>{
}