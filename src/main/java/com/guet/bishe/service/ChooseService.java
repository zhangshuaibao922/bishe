package com.guet.bishe.service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guet.bishe.entity.Choose;
import com.guet.bishe.entity.Response;
import com.guet.bishe.entity.Student;

import java.util.List;

/**
 * 选课;(choose)表服务接口
 * @author : cardo
 * @date : 2024-4-10
 */
public interface ChooseService extends IService<Choose> {
    
    /** 
     * 通过studentName查询单条数据
     *
     * @param studentId 主键
     * @return 实例对象
     */
    List<Student> queryById(String studentId);

    /** 
     * 新增数据
     *
     * @param choose 实例对象
     * @return 实例对象
     */
    boolean insert(Choose choose);
    /** 
     * 更新数据
     *
     * @param choose 实例对象
     * @return 实例对象
     */
    boolean update(Choose choose);
    /** 
     * 通过主键删除数据
     *
     * @param studentId 主键
     * @return 是否成功
     */
    boolean deleteById(String studentId);

    Response<List<Student>> queryAll(String lessonId);
}