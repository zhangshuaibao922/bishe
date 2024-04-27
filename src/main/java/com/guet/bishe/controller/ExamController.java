package com.guet.bishe.controller;


import com.guet.bishe.entity.Exam;
import com.guet.bishe.entity.ExamDto;
import com.guet.bishe.entity.Response;
import com.guet.bishe.service.ExamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 考试;(exam)表控制层
 * @author : cardo
 * @date : 2024-4-17
 */
@Api(tags = "考试对象功能接口")
@RestController
@RequestMapping("/exam")
public class ExamController{
    @Autowired
    private ExamService examService;


    /**
     * 通过ID查询单条数据
     *
     * @param examId 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping("getModelClass/{examId}")
    public Response<String> getModelClass(@PathVariable String examId){
        return examService.getModelClass(examId);
    }
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param examName 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{examName}")
    public Response<List<Exam>> queryByExamName(@PathVariable String examName){
        return examService.queryByExamName(examName);
    }

     /**
      * 通过ID查询单条数据
      *
      * @param examClass 主键
      * @return 实例对象
      */
     @ApiOperation("通过ID查询单条数据")
     @GetMapping("/all/{examClass}/{teacherId}/{id}")
     public Response<List<ExamDto>> queryByExamClass(@PathVariable String examClass,@PathVariable String teacherId,@PathVariable String id){
         return examService.queryByExamClass(examClass,teacherId,id);
     }

    /**
     * 通过ID查询单条数据
     *
     * @param examClass 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping("/all/score/{examClass}")
    public Response<List<ExamDto>> scoreQueryByExamClass(@PathVariable String examClass){
        return examService.scoreQueryByExamClass(examClass);
    }

    /** 
     * 新增数据
     *
     * @param exam 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping
    public ResponseEntity<Boolean> add(@RequestBody Exam exam){
        return ResponseEntity.ok(examService.insert(exam));
    }
    
    /** 
     * 更新数据
     *
     * @param exam 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PutMapping
    public ResponseEntity<Boolean> edit(@RequestBody Exam exam){
        return ResponseEntity.ok(examService.update(exam));
    }
    
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @ApiOperation("通过主键删除数据")
    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Integer id){
        return ResponseEntity.ok(examService.deleteById(id));
    }
}