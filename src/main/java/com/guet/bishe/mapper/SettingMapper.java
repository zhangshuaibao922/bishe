package com.guet.bishe.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guet.bishe.entity.Setting;
import org.apache.ibatis.annotations.Mapper;


/**
 * 考试设置;(seting)表数据库访问层
 * @author : cardo
 * @date : 2024-4-17
 */
@Mapper
public interface SettingMapper extends BaseMapper<Setting>{
}