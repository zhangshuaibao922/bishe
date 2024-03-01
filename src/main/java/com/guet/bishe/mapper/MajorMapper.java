package com.guet.bishe.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guet.bishe.entity.Major;
import org.apache.ibatis.annotations.Mapper;

 /**
 * 专业;(major)表数据库访问层
 * @author : cardo
 * @date : 2024-3-1
 */
@Mapper
public interface MajorMapper  extends BaseMapper<Major>{
}