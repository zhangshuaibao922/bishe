package com.guet.bishe.service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guet.bishe.entity.Grade;


/**
 * 年级;(grade)表服务接口
 * @author : cardo
 * @date : 2024-3-4
 */
public interface GradeService extends IService<Grade> {
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param gradeId 主键
     * @return 实例对象
     */
    Grade queryByGradeId(String gradeId);
    
    /**
     * 分页查询
     *
     * @param gradeYear 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    Page<Grade> paginQuery(String gradeYear, long current, long size);
    /** 
     * 新增数据
     *
     * @param grade 实例对象
     * @return 实例对象
     */
    boolean insert(Grade grade);
    /** 
     * 更新数据
     *
     * @param grade 实例对象
     * @return 实例对象
     */
    boolean update(Grade grade);
    /** 
     * 通过主键删除数据
     *
     * @param gradeId 主键
     * @return 是否成功
     */
    boolean deleteByGradeId(String gradeId);
}