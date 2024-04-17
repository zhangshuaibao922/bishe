package com.guet.bishe.mapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.guet.bishe.entity.Seting;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


 /**
 * 考试设置;(seting)表数据库访问层
 * @author : cardo
 * @date : 2024-4-17
 */
@Mapper
public interface SetingMapper  extends BaseMapper<Seting>{
}