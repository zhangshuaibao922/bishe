package com.guet.bishe.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.guet.bishe.entity.College;
import org.apache.ibatis.annotations.Mapper;


/**
* 学院;(college)表数据库访问层
* @author : cardo
* @date : 2024-2-27
*/
@Mapper
public interface CollegeMapper extends BaseMapper<College>{
}