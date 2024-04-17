package com.guet.bishe.controller;


import com.guet.bishe.entity.Exam;
import com.guet.bishe.service.ExamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
     * @param id 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{id}")
    public ResponseEntity<Exam> queryById(@PathVariable Integer id){
        return ResponseEntity.ok(examService.queryById(id));
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