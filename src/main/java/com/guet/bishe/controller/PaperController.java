package com.guet.bishe.controller;


import com.guet.bishe.entity.Paper;
import com.guet.bishe.entity.Response;
import com.guet.bishe.entity.StudentDto;
import com.guet.bishe.service.PaperService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 答题卡;(paper)表控制层
 * @author : cardo
 * @date : 2024-4-17
 */
@Api(tags = "答题卡对象功能接口")
@RestController
@RequestMapping("/paper")
public class PaperController{
    @Autowired
    private PaperService paperService;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{id}")
    public ResponseEntity<Paper> queryById(@PathVariable Integer id){
        return ResponseEntity.ok(paperService.queryById(id));
    }

     @ApiOperation("通过ID查询单条数据")
     @GetMapping("/exam/{lessonId}/{examId}")
     public Response<List<StudentDto>> queryByIdExam(@PathVariable String lessonId,@PathVariable String examId){
         return paperService.queryByIdExam(lessonId,examId);
     }

    
    /** 
     * 新增数据
     *
     * @param paper 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping
    public ResponseEntity<Boolean> add(@RequestBody Paper paper){
        return ResponseEntity.ok(paperService.insert(paper));
    }
    
    /** 
     * 更新数据
     *
     * @param paper 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PutMapping
    public ResponseEntity<Boolean> edit(@RequestBody Paper paper){
        return ResponseEntity.ok(paperService.update(paper));
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
        return ResponseEntity.ok(paperService.deleteById(id));
    }
}