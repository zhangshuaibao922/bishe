package com.guet.bishe.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guet.bishe.entity.Model;
import org.apache.ibatis.annotations.Mapper;


/**
 * 模版;(model)表数据库访问层
 *
 * @author : cardo
 * @date : 2024-4-18
 */
@Mapper
public interface ModelMapper extends BaseMapper<Model> {
}