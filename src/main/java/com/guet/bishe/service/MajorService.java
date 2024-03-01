package com.guet.bishe.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guet.bishe.entity.Major;


/**
 * 专业;(major)表服务接口
 * @author : cardo
 * @date : 2024-3-1
 */
public interface MajorService{
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    Major queryById(Integer id);
    
    /**
     * 分页查询
     *
     * @param major 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    Page<Major> paginQuery(Major major, long current, long size);
    /** 
     * 新增数据
     *
     * @param major 实例对象
     * @return 实例对象
     */
    boolean insert(Major major);
    /** 
     * 更新数据
     *
     * @param major 实例对象
     * @return 实例对象
     */
    boolean update(Major major);
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);
}