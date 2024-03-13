package com.guet.bishe.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guet.bishe.entity.LoginDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper extends BaseMapper<LoginDto> {
}
