package com.guet.bishe.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.bishe.entity.Response;
import com.guet.bishe.entity.Student;
import com.guet.bishe.mapper.StudentMapper;
import com.guet.bishe.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;

import java.util.List;

/**
 * 学生;(student)表服务实现类
 * @author : cardo
 * @date : 2024-3-5
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param studentId 主键
     * @return 实例对象
     */
    public     Student queryByStudentId(String studentId){
        LambdaQueryWrapper<Student> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Student::getStudentId,studentId);
        return studentMapper.selectOne(lambdaQueryWrapper);
    }
    
    /** 
     * 新增数据
     *
     * @param student 实例对象
     * @return 实例对象
     */
    public boolean insert(Student student){
        return studentMapper.insert(student)>0;
    }
    
    /** 
     * 更新数据
     *
     * @param student 实例对象
     * @return 实例对象
     */
    public boolean update(Student student){
        //1. 根据条件动态更新
        LambdaQueryWrapper<Student> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Student::getStudentId,student.studentId);
        //2. 设置主键，并更新
        return studentMapper.update(student, lambdaQueryWrapper)>0;
    }
    
    /** 
     * 通过主键删除数据
     *
     * @param studentId 主键
     * @return 是否成功
     */
    public boolean deleteByStudentId(String studentId){
        LambdaQueryWrapper<Student> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Student::getStudentId,studentId);
        return studentMapper.delete(lambdaQueryWrapper)> 0;
    }
}