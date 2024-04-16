package com.guet.bishe.controller;


import com.guet.bishe.entity.Student;
import com.guet.bishe.entity.StudentInfo;
import com.guet.bishe.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 学生;(student)表控制层
 * @author : cardo
 * @date : 2024-3-5
 */
@Api(tags = "学生对象功能接口")
@RestController
@RequestMapping("/student")
public class StudentController{
    @Autowired
    private StudentService studentService;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param studentInfo 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @PutMapping("/select")
    public ResponseEntity<List<Student>> queryById(@RequestBody StudentInfo studentInfo){
        return ResponseEntity.ok(studentService.queryByStudentId(studentInfo));
    }

    /** 
     * 新增数据
     *
     * @param student 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping
    public ResponseEntity<Boolean> add(@RequestBody Student student){
        return ResponseEntity.ok(studentService.insert(student));
    }
    /** 
     * 更新数据
     *
     * @param student 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PutMapping
    public ResponseEntity<Boolean> edit(@RequestBody Student student){
        return ResponseEntity.ok(studentService.update(student));
    }
    
    /** 
     * 通过主键删除数据
     *
     * @param studentId 主键
     * @return 是否成功
     */
    @ApiOperation("通过主键删除数据")
    @DeleteMapping("{studentId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable String studentId){
        return ResponseEntity.ok(studentService.deleteByStudentId(studentId));
    }
}