package com.guet.bishe.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.bishe.Utils.MD5;
import com.guet.bishe.entity.*;
import com.guet.bishe.mapper.ChooseMapper;
import com.guet.bishe.mapper.StudentMapper;
import com.guet.bishe.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;

import java.util.ArrayList;
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
    @Autowired
    private ChooseMapper chooseMapper;
    /** 
     * 通过ID查询单条数据 
     *
     * @param studentInfo 主键
     * @return 实例对象
     */

    @Override
    public List<Student> queryByStudentId(StudentInfo studentInfo) {
        LambdaQueryWrapper<Choose> chooseLambdaQueryWrapper = new LambdaQueryWrapper<>();
        chooseLambdaQueryWrapper.eq(Choose::getLessonId,studentInfo.getLessonId())
                .like(Choose::getStudentId,studentInfo.getStudentId());
        List<Choose> chooses = chooseMapper.selectList(chooseLambdaQueryWrapper);
        List<Student> students = new ArrayList<>();
        if (chooses.size()>0){
            for (Choose choose : chooses) {
                LambdaQueryWrapper<Student> studentLambdaQueryWrapper = new LambdaQueryWrapper<>();
                studentLambdaQueryWrapper.eq(Student::getStudentId,choose.getStudentId());
                List<Student> students1 = studentMapper.selectList(studentLambdaQueryWrapper);
                if(students1.size()>0) {
                    students.addAll(students1);
                }
            }
        }
        return students;
    }

    /**
     * 新增数据
     *
     * @param student 实例对象
     * @return 实例对象
     */
    public boolean insert(Student student){
        String s = MD5.encrypt3ToMD5(student.getStudentPassword());
        student.setStudentPassword(s);
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

    @Override
    public Response<Student> getStudentInfo(String studentId) {
        LambdaQueryWrapper<Student> studentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        studentLambdaQueryWrapper.eq(Student::getStudentId,studentId);
        Student student = studentMapper.selectOne(studentLambdaQueryWrapper);
        Response<Student> studentResponse = new Response<>();
        studentResponse.setData(student);
        return studentResponse;
    }

    @Override
    public Response<List<Student>> getAll() {
        List<Student> students = studentMapper.selectList(null);
        Response<List<Student>> listResponse = new Response<>();
        listResponse.setData(students);
        return listResponse;
    }

    @Override
    public Response<Boolean> delete(Integer id) {
        int i = studentMapper.deleteById(id);
        Response<Boolean> response = new Response<>();
        if(i>0){
            response.setData(true);
        }else {
            response.setData(false);
        }
        return response;
    }

    @Override
    public Response<List<Student>> getLikeStudents(String studentId) {
        LambdaQueryWrapper<Student> studentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        studentLambdaQueryWrapper.like(Student::getStudentId,studentId);
        Response<List<Student>> listResponse = new Response<>();
        List<Student> students = studentMapper.selectList(studentLambdaQueryWrapper);
        listResponse.setData(students);
        return listResponse;
    }

    @Override
    public Response<List<Student>> queryAllByCollegeId(String collegeId) {
        LambdaQueryWrapper<Student> studentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        studentLambdaQueryWrapper.eq(Student::getCollegeId,collegeId);
        List<Student> students = studentMapper.selectList(studentLambdaQueryWrapper);
        Response<List<Student>> listResponse = new Response<>();
        listResponse.setData(students);
        return listResponse;
    }
}