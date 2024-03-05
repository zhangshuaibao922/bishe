package com.guet.bishe.service;

import com.guet.bishe.entity.Student;

 /**
 * 学生;(student)表服务接口
 * @author : cardo
 * @date : 2024-3-5
 */
public interface StudentService{
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param studentId 主键
     * @return 实例对象
     */
    Student queryByStudentId(String studentId);

    /** 
     * 新增数据
     *
     * @param student 实例对象
     * @return 实例对象
     */
    boolean insert(Student student);
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
}