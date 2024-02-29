package com.guet.bishe.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.bishe.entity.Teacher;
import com.guet.bishe.mapper.TeacherMapper;
import com.guet.bishe.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;

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
    public Teacher queryByTeacherId(String teacherId){
        LambdaQueryWrapper<Teacher> teacherLambdaQueryWrapper = new LambdaQueryWrapper<>();
        return teacherMapper.selectOne(teacherLambdaQueryWrapper.eq(Teacher::getTeacherId,teacherId));
    }

    /**
     * 分页查询
     *
     * @param teacher 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    public Page<Teacher> paginQuery(Teacher teacher, long current, long size){
        //1. 构建动态查询条件
        LambdaUpdateChainWrapper<Teacher> queryWrapper = queryConditions(teacher);
        //2. 执行分页查询
        Page<Teacher> pagin = new Page<>(current , size , true);
        IPage<Teacher> selectResult = teacherMapper.selectByPage(pagin , queryWrapper);
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
    public Teacher insert(Teacher teacher){
        teacherMapper.insert(teacher);
        return teacher;
    }

    /**
     * 更新数据
     *
     * @param teacher 实例对象
     * @return 实例对象
     */
    public boolean update(Teacher teacher){
        //1. 根据条件动态更新
        LambdaQueryWrapper<Teacher> teacherLambdaQueryWrapper = new LambdaQueryWrapper<>();
        teacherLambdaQueryWrapper.eq(Teacher::getTeacherId,teacher.getTeacherId());
        //2. 设置主键，并更新
        int update = teacherMapper.update(teacher, teacherLambdaQueryWrapper);
        return update>0;
    }

    /**
     * 通过主键删除数据
     *
     * @param teacherId 主键
     * @return 是否成功
     */
    public boolean deleteByTeacherId(String teacherId){
        LambdaQueryWrapper<Teacher> teacherLambdaQueryWrapper = new LambdaQueryWrapper<>();
        teacherLambdaQueryWrapper.eq(Teacher::getTeacherId,teacherId);
        int total = teacherMapper.delete(teacherLambdaQueryWrapper);
        return total > 0;
    }

    public LambdaUpdateChainWrapper<Teacher> queryConditions(Teacher teacher){
        LambdaUpdateChainWrapper<Teacher> chainWrapper = new LambdaUpdateChainWrapper<Teacher>(teacherMapper);
        if(StrUtil.isNotBlank(teacher.getCollegeId())){
            chainWrapper.eq(Teacher::getCollegeId, teacher.getCollegeId());
        }
        if(StrUtil.isNotBlank(teacher.getTeacherId())){
            chainWrapper.eq(Teacher::getTeacherId, teacher.getTeacherId());
        }
        if(StrUtil.isNotBlank(teacher.getTeacherName())){
            chainWrapper.eq(Teacher::getTeacherName, teacher.getTeacherName());
        }
        if(StrUtil.isNotBlank(teacher.getTeacherPassword())){
            chainWrapper.eq(Teacher::getTeacherPassword, teacher.getTeacherPassword());
        }
        if(StrUtil.isNotBlank(teacher.getIdCardNo())){
            chainWrapper.eq(Teacher::getIdCardNo, teacher.getIdCardNo());
        }
        if(StrUtil.isNotBlank(teacher.getMobilePhone())){
            chainWrapper.eq(Teacher::getMobilePhone, teacher.getMobilePhone());
        }
        if(StrUtil.isNotBlank(teacher.getAuthorityId())){
            chainWrapper.eq(Teacher::getAuthorityId, teacher.getAuthorityId());
        }
        if(StrUtil.isNotBlank(teacher.getStatus())){
            chainWrapper.eq(Teacher::getStatus, teacher.getStatus());
        }
        if(StrUtil.isNotBlank(teacher.getCreateTime())){
            chainWrapper.eq(Teacher::getCreateTime, teacher.getCreateTime());
        }
        if(StrUtil.isNotBlank(teacher.getUpdateTime())){
            chainWrapper.eq(Teacher::getUpdateTime, teacher.getUpdateTime());
        }
        return chainWrapper;
    }
}