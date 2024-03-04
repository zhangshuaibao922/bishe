package com.guet.bishe.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.bishe.entity.Authority;

import com.guet.bishe.entity.College;
import com.guet.bishe.mapper.AuthorityMapper;
import com.guet.bishe.mapper.CollegeMapper;
import com.guet.bishe.service.AuthorityService;
import com.guet.bishe.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.util.List;

/**
 * 权限;(authority)表服务实现类
 *
 * @author : cardo
 * @date : 2024-2-28
 */
@Service
public class AuthorityServiceImpl extends ServiceImpl<AuthorityMapper, Authority> implements AuthorityService {
    @Autowired
    private AuthorityMapper authorityMapper;

    /**
     *
     * @return 权限列表
     */
    public List<Authority> queryAll() {
        LambdaQueryWrapper<Authority> authorityLambdaQueryWrapper = new LambdaQueryWrapper<>();
        return authorityMapper.selectList(authorityLambdaQueryWrapper);
    }


    /**
     * 新增数据
     *
     * @param authority 实例对象
     * @return 实例对象
     */
    public boolean insert(Authority authority) {
        int insert = authorityMapper.insert(authority);
        return insert > 0;
    }

    /**
     * 更新数据
     *
     * @param authority 实例对象
     * @return 实例对象
     */
    public boolean update(Authority authority) {
        //1. 根据条件动态更新
        LambdaQueryWrapper<Authority> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Authority::getAuthorityId, authority.getAuthorityId());
        //2. 设置主键，并更新
        int update = authorityMapper.update(authority, lambdaQueryWrapper);
        return update > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param authorityId 主键
     * @return 是否成功
     */
    public boolean deleteByAuthorityId(String authorityId) {
        LambdaQueryWrapper<Authority> authorityLambdaQueryWrapper = new LambdaQueryWrapper<>();
        int total = authorityMapper.delete(authorityLambdaQueryWrapper.eq(Authority::getAuthorityId, authorityId));
        return total > 0;
    }

}