package com.guet.bishe.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guet.bishe.entity.Choose;
import org.apache.ibatis.annotations.Mapper;


 /**
 * 选课;(choose)表数据库访问层
 * @author : cardo
 * @date : 2024-4-10
 */
@Mapper
public interface ChooseMapper  extends BaseMapper<Choose>{
}