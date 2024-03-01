package com.guet.bishe.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guet.bishe.entity.Authority;
import org.apache.ibatis.annotations.Mapper;


/**
 * 权限;(authority)表数据库访问层
 *
 * @author : cardo
 * @date : 2024-2-28
 */
@Mapper
public interface AuthorityMapper extends BaseMapper<Authority> {
}