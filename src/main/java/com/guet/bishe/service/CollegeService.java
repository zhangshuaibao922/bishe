package com.guet.bishe.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guet.bishe.entity.College;
import com.guet.bishe.entity.Response;

import java.util.List;


/**
 * 学院;(college)表服务接口
 * @author : cardo
 * @date : 2024-2-27
 */
public interface CollegeService extends IService<College> {
    
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
     * @param collegeId 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    Page<College> paginQuery(String collegeId, long current, long size);
    /** 
     * 新增数据
     *
     * @param college 实例对象
     * @return 实例对象
     */
    boolean insert(College college);
    /** 
     * 更新数据
     *
     * @param college 实例对象
     * @return 实例对象
     */
    boolean update(College college);
    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteByCollegeId(Integer id);

    Response<List<College>> queryAll();
}