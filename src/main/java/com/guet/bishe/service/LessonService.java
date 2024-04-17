package com.guet.bishe.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guet.bishe.entity.Lesson;
import com.guet.bishe.entity.LessonDto;
import com.guet.bishe.entity.Response;

import java.util.List;


/**
 * 课程;(lesson)表服务接口
 * @author : cardo
 * @date : 2024-4-8
 */
public interface LessonService extends IService<Lesson> {
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param LessonName 主键
     * @return 实例对象
     */
    List<Lesson> queryById(String LessonName);

    /** 
     * 新增数据
     *
     * @param lesson 实例对象
     * @return 实例对象
     */
    boolean insert(Lesson lesson);
    /** 
     * 更新数据
     *
     * @param lesson 实例对象
     * @return 实例对象
     */
    boolean update(Lesson lesson);
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    Response<List<LessonDto>> queryAll();

    Response<List<LessonDto>> queryAllByTeacherId(String teacherId);
}