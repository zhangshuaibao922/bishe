package com.guet.bishe.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.guet.bishe.entity.Authority;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

 /**
 * 权限;(authority)表数据库访问层
 * @author : cardo
 * @date : 2024-2-28
 */
@Mapper
public interface AuthorityMapper  extends BaseMapper<Authority>{
    /** 
     * 分页查询指定行数据
     *
     * @param page 分页参数
     * @param wrapper 动态查询条件
     * @return 分页对象列表
     */
    IPage<Authority> selectByPage(IPage<Authority> page , @Param(Constants.WRAPPER) Wrapper<Authority> wrapper);
}