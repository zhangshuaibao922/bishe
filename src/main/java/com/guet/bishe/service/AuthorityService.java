package com.guet.bishe.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guet.bishe.entity.Authority;

import java.util.List;

/**
 * 权限;(authority)表服务接口
 *
 * @author : cardo
 * @date : 2024-2-28
 */
public interface AuthorityService extends IService<Authority> {
    /**
     *
     * @return 权限列表
     */
    List<Authority> queryAll();

    /**
     * 新增数据
     *
     * @param authority 实例对象
     * @return 实例对象
     */
    boolean insert(Authority authority);

    /**
     * 更新数据
     *
     * @param authority 实例对象
     * @return 实例对象
     */
    boolean update(Authority authority);

    /**
     * 通过主键删除数据
     *
     * @param authorityId 主键
     * @return 是否成功
     */
    boolean deleteByAuthorityId(String authorityId);
}