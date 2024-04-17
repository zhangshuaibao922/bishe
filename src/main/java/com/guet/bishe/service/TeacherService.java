package com.guet.bishe.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guet.bishe.entity.Response;
import com.guet.bishe.entity.Teacher;

import java.util.List;


/**
 * 教师;(teacher)表服务接口
 * @author : cardo
 * @date : 2024-2-28
 */
public interface TeacherService extends IService<Teacher> {

    /**
     * 通过ID查询单条数据
     *
     * @param teacherId 主键
     * @return 实例对象
     */
    List<Teacher> queryByTeacherId(String teacherId);

    /**
     * 分页查询
     *
     * @param collegeId 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    Page<Teacher> paginQuery(String collegeId, long current, long size);
    /**
     * 新增数据
     *
     * @param teacher 实例对象
     * @return 实例对象
     */
    boolean insert(Teacher teacher);
    /**
     * 更新数据
     *
     * @param teacher 实例对象
     * @return 实例对象
     */
    boolean update(Teacher teacher);
    /**
     * 通过主键删除数据
     *
     * @param teacherId 主键
     * @return 是否成功
     */
    boolean deleteByTeacherId(String teacherId);

    Response<List<Teacher>> queryAll();
}