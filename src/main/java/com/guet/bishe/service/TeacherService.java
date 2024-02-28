package com.guet.bishe.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guet.bishe.entity.Teacher;


/**
 * 教师;(teacher)表服务接口
 * @author : cardo
 * @date : 2024-2-28
 */
public interface TeacherService extends IService<Teacher> {
    Teacher selectByTeacherId(String teacherId);
}