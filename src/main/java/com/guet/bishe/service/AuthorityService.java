package com.guet.bishe.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guet.bishe.entity.Authority;

/**
 * 权限;(authority)表服务接口
 * @author : cardo
 * @date : 2024-2-28
 */
public interface AuthorityService{
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param authorityId 主键
     * @return 实例对象
     */
    Authority queryByAuthorityId(String authorityId);
    
    /**
     * 分页查询
     *
     * @param authority 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    Page<Authority> paginQuery(Authority authority, long current, long size);
    /** 
     * 新增数据
     *
     * @param authority 实例对象
     * @return 实例对象
     */
    Authority insert(Authority authority);
    /** 
     * 更新数据
     *
     * @param authority 实例对象
     * @return 实例对象
     */
    Authority update(Authority authority);
    /** 
     * 通过主键删除数据
     *
     * @param authorityId 主键
     * @return 是否成功
     */
    boolean deleteByAuthorityId(String authorityId);
}