package com.guet.bishe.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guet.bishe.entity.College;


/**
 * 学院;(college)表服务接口
 * @author : http://www.chiner.pro
 * @date : 2024-2-27
 */
public interface CollegeService{
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param collegeId 主键
     * @return 实例对象
     */
    College queryByCollegeId(String collegeId);
    
    /**
     * 分页查询
     *
     * @param college 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    Page<College> paginQuery(College college, long current, long size);
    /** 
     * 新增数据
     *
     * @param college 实例对象
     * @return 实例对象
     */
    College insert(College college);
    /** 
     * 更新数据
     *
     * @param college 实例对象
     * @return 实例对象
     */
    College update(College college);
    /**
     * 通过主键删除数据
     *
     * @param collegeId 主键
     * @return 是否成功
     */
    boolean deleteByCollegeId(String collegeId);
}