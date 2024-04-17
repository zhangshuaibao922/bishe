package com.guet.bishe.service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guet.bishe.entity.Exam;


 /**
 * 考试;(exam)表服务接口
 * @author : cardo
 * @date : 2024-4-17
 */
public interface ExamService extends IService<Exam> {
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    Exam queryById(Integer id);
    /**
     * 新增数据
     *
     * @param exam 实例对象
     * @return 实例对象
     */
    boolean insert(Exam exam);
    /** 
     * 更新数据
     *
     * @param exam 实例对象
     * @return 实例对象
     */
    boolean update(Exam exam);
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);
}