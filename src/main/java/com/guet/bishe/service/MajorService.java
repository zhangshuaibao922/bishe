package com.guet.bishe.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guet.bishe.entity.Major;
import com.guet.bishe.entity.Teacher;


/**
 * 专业;(major)表服务接口
 * @author : cardo
 * @date : 2024-3-1
 */
public interface MajorService extends IService<Major> {
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param majorId 主键
     * @return 实例对象
     */
    Major queryByMajorId(String majorId);
    
    /**
     * 分页查询
     *
     * @param majorName 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    Page<Major> paginQuery(String majorName, long current, long size);
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
     * @param majorId 主键
     * @return 是否成功
     */
    boolean deleteByMajorId(String majorId);
}