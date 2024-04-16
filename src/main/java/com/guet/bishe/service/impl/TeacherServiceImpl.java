package com.guet.bishe.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.bishe.entity.Lesson;
import com.guet.bishe.entity.Response;
import com.guet.bishe.entity.Teacher;
import com.guet.bishe.mapper.TeacherMapper;
import com.guet.bishe.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.util.List;

/**
 * 教师;(teacher)表服务实现类
 *
 * @author : cardo
 * @date : 2024-2-28
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param teacherId 主键
     * @return 实例对象
     */
    public Teacher queryByTeacherId(String teacherId) {
        LambdaQueryWrapper<Teacher> teacherLambdaQueryWrapper = new LambdaQueryWrapper<>();
        return teacherMapper.selectOne(teacherLambdaQueryWrapper.eq(Teacher::getTeacherId, teacherId));
    }

    /**
     * 分页查询
     *
     * @param collegeId 筛选条件
     * @param current   当前页码
     * @param size      每页大小
     * @return
     */
    public Page<Teacher> paginQuery(String collegeId, long current, long size) {
        //1. 构建动态查询条件
        LambdaQueryWrapper<Teacher> teacherLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(collegeId)) {
            teacherLambdaQueryWrapper.eq(Teacher::getCollegeId, collegeId);
        }
        //2. 执行分页查询
        Page<Teacher> pagin = new Page<>(current, size, true);
        IPage<Teacher> selectResult = teacherMapper.selectPage(pagin, teacherLambdaQueryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return pagin;
    }

    /**
     * 新增数据
     *
     * @param teacher 实例对象
     * @return 实例对象
     */
    public boolean insert(Teacher teacher) {
        int insert = teacherMapper.insert(teacher);
        return insert > 0;
    }

    /**
     * 更新数据
     *
     * @param teacher 实例对象
     * @return 实例对象
     */
    public boolean update(Teacher teacher) {
        //1. 根据条件动态更新
        LambdaQueryWrapper<Teacher> teacherLambdaQueryWrapper = new LambdaQueryWrapper<>();
        teacherLambdaQueryWrapper.eq(Teacher::getTeacherId, teacher.getTeacherId());
        //2. 设置主键，并更新
        int update = teacherMapper.update(teacher, teacherLambdaQueryWrapper);
        return update > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param teacherId 主键
     * @return 是否成功
     */
    public boolean deleteByTeacherId(String teacherId) {
        LambdaQueryWrapper<Teacher> teacherLambdaQueryWrapper = new LambdaQueryWrapper<>();
        teacherLambdaQueryWrapper.eq(Teacher::getTeacherId, teacherId);
        int total = teacherMapper.delete(teacherLambdaQueryWrapper);
        return total > 0;
    }

    @Override
    public Response<List<Teacher>> queryAll() {
        List<Teacher> teacherList = teacherMapper.selectList(null);
        Response<List<Teacher>> listResponse = new Response<>();
        listResponse.setMessage("查询成功");
        listResponse.setData(teacherList);
        return listResponse;
    }

}