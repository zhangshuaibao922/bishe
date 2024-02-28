package com.guet.bishe.service.impl;

import cn.hutool.core.util.StrUtil;
import com.guet.bishe.entity.Authority;
import com.guet.bishe.entity.College;
import com.guet.bishe.mapper.AuthorityMapper;
import com.guet.bishe.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;

 /**
 * 权限;(authority)表服务实现类
 * @author : cardo
 * @date : 2024-2-28
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    private AuthorityMapper authorityMapper;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param authorityId 主键
     * @return 实例对象
     */
    public Authority queryByAuthorityId(String authorityId){
        LambdaQueryWrapper<Authority> authorityLambdaQueryWrapper = new LambdaQueryWrapper<>();
        return authorityMapper.selectOne(authorityLambdaQueryWrapper.eq(Authority::getAuthorityId,authorityId));
    }
    
    /**
     * 分页查询
     *
     * @param authority 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    public Page<Authority> paginQuery(Authority authority, long current, long size){
        //1. 构建动态查询条件
        LambdaUpdateChainWrapper<Authority> queryWrapper = queryConditions(authority);
        //2. 执行分页查询
        Page<Authority> pagin = new Page<>(current , size , true);
        IPage<Authority> selectResult = authorityMapper.selectByPage(pagin , queryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return pagin;
    }
    
    /** 
     * 新增数据
     *
     * @param authority 实例对象
     * @return 实例对象
     */
    public Authority insert(Authority authority){
        authorityMapper.insert(authority);
        return authority;
    }
    
    /** 
     * 更新数据
     *
     * @param authority 实例对象
     * @return 实例对象
     */
    public Authority update(Authority authority){
        //1. 根据条件动态更新
        LambdaUpdateChainWrapper<Authority> chainWrapper = queryConditions(authority);
        //2. 设置主键，并更新
        chainWrapper.set(Authority::getId, authority.getId());
        boolean ret = chainWrapper.update();
        //3. 更新成功了，查询最最对象返回
        if(ret){
            return queryByAuthorityId(authority.getAuthorityId());
        }else{
            return authority;
        }
    }
    
    /** 
     * 通过主键删除数据
     *
     * @param authorityId 主键
     * @return 是否成功
     */
    public boolean deleteByAuthorityId(String authorityId){
        LambdaQueryWrapper<Authority> authorityLambdaQueryWrapper = new LambdaQueryWrapper<>();
        int total = authorityMapper.delete(authorityLambdaQueryWrapper.eq(Authority::getAuthorityId,authorityId));
        return total > 0;
    }

     public LambdaUpdateChainWrapper<Authority>  queryConditions(Authority authority){
         LambdaUpdateChainWrapper<Authority> chainWrapper = new LambdaUpdateChainWrapper<Authority>(authorityMapper);
         if(StrUtil.isNotBlank(authority.getAuthorityId())){
             chainWrapper.eq(Authority::getAuthorityId, authority.getAuthorityId());
         }
         if(StrUtil.isNotBlank(authority.getAuthorityRole())){
             chainWrapper.eq(Authority::getAuthorityRole, authority.getAuthorityRole());
         }
         if(StrUtil.isNotBlank(authority.getCreateTime())){
             chainWrapper.eq(Authority::getCreateTime, authority.getCreateTime());
         }
         if(StrUtil.isNotBlank(authority.getUpdateTime())){
             chainWrapper.eq(Authority::getUpdateTime, authority.getUpdateTime());
         }
         return chainWrapper;
     }
}