package com.guet.bishe.controller;

import com.guet.bishe.entity.Instruct;
import com.guet.bishe.entity.Teacher;
import com.guet.bishe.service.InstructService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


 /**
 * 授课;(instruct)表控制层
 * @author : cardo
 * @date : 2024-4-16
 */
@Api(tags = "授课对象功能接口")
@RestController
@RequestMapping("/instruct")
public class InstructController{
    @Autowired
    private InstructService instructService;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param lessonId 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{lessonId}")
    public ResponseEntity<Teacher> queryById(@PathVariable String lessonId){
        return ResponseEntity.ok(instructService.queryById(lessonId));
    }

    
    /** 
     * 新增数据
     *
     * @param instruct 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping
    public ResponseEntity<Boolean> add(@RequestBody Instruct instruct){
        return ResponseEntity.ok(instructService.insert(instruct));
    }
    
    /** 
     * 更新数据
     *
     * @param instruct 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PutMapping
    public ResponseEntity<Boolean> edit(@RequestBody Instruct instruct){
        return ResponseEntity.ok(instructService.update(instruct));
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
        return ResponseEntity.ok(instructService.deleteById(id));
    }
}