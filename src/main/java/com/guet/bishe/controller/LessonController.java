package com.guet.bishe.controller;
import java.util.List;

import com.guet.bishe.entity.Lesson;
import com.guet.bishe.entity.LessonDto;
import com.guet.bishe.entity.Response;
import com.guet.bishe.service.LessonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


 /**
 * 课程;(lesson)表控制层
 * @author : cardo
 * @date : 2024-4-8
 */
@Api(tags = "课程对象功能接口")
@RestController
@RequestMapping("/lesson")
public class LessonController{
    @Autowired
    private LessonService lessonsService;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param LessonName 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{LessonName}")
    public ResponseEntity<List<Lesson>> queryById(@PathVariable String LessonName){
        return ResponseEntity.ok(lessonsService.queryById(LessonName));
    }

     /**
      * 查询全部课程
      */
     @ApiOperation("返回课程列表")
     @GetMapping("/all")
     public Response<List<LessonDto>> queryAll() {
         return lessonsService.queryAll();
     }
     @ApiOperation("返回课程列表")
     @GetMapping("/byCollegeId/{collegeId}")
     public Response<List<LessonDto>> queryAllByCollegeId(@PathVariable String collegeId){
         return lessonsService.queryAllByCollegeId(collegeId);
     }

     @ApiOperation("返回课程列表")
     @GetMapping("/all/{teacherId}")
     public Response<List<LessonDto>> queryAllByTeacherId(@PathVariable String teacherId) {
         return lessonsService.queryAllByTeacherId(teacherId);
     }


    /** 
     * 新增数据
     *
     * @param lesson 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping
    public ResponseEntity<Boolean> add(@RequestBody Lesson lesson){
        return ResponseEntity.ok(lessonsService.insert(lesson));
    }
    
    /** 
     * 更新数据
     *
     * @param lesson 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PutMapping
    public ResponseEntity<Boolean> edit(@RequestBody Lesson lesson){
        return ResponseEntity.ok(lessonsService.update(lesson));
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
        return ResponseEntity.ok(lessonsService.deleteById(id));
    }
}