package com.guet.bishe.service;

import com.guet.bishe.entity.*;

import java.util.List;

/**
 * 学生;(student)表服务接口
 * @author : cardo
 * @date : 2024-3-5
 */
public interface StudentService{
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param studentInfo 主键
     * @return 实例对象
     */
    List<Student> queryByStudentId(StudentInfo studentInfo);

    /** 
     * 新增数据
     *
     * @param student 实例对象
     * @return 实例对象
     */
    boolean insert(StudentCreateDto student);
    /** 
     * 更新数据
     *
     * @param student 实例对象
     * @return 实例对象
     */
    boolean update(Student student);
    /** 
     * 通过主键删除数据
     *
     * @param studentId 主键
     * @return 是否成功
     */
    boolean deleteByStudentId(String studentId);

    Response<Student> getStudentInfo(String studentId);

    Response<List<Student>> getAll();

    Response<Boolean> delete(Integer id);

    Response<List<Student>> getLikeStudents(String studentId);

    Response<List<Student>> queryAllByCollegeId(String collegeId);
}