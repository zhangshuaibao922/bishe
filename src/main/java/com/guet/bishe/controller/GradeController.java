package com.guet.bishe.controller;

import com.guet.bishe.entity.Grade;
import com.guet.bishe.service.GradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


 /**
 * 年级;(grade)表控制层
 * @author : cardo
 * @date : 2024-3-4
 */
@Api(tags = "年级对象功能接口")
@RestController
@RequestMapping("/grade")
public class GradeController{
    @Autowired
    private GradeService gradeService;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param gradeId 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{gradeId}")
    public ResponseEntity<Grade> queryById(@PathVariable String gradeId){
        return ResponseEntity.ok(gradeService.queryByGradeId(gradeId));
    }

    
    /** 
     * 新增数据
     *
     * @param grade 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping
    public ResponseEntity<Boolean> add(@RequestBody Grade grade){
        return ResponseEntity.ok(gradeService.insert(grade));
    }
    
    /** 
     * 更新数据
     *
     * @param grade 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PutMapping
    public ResponseEntity<Boolean> edit(@RequestBody Grade grade){
        return ResponseEntity.ok(gradeService.update(grade));
    }
    
    /** 
     * 通过主键删除数据
     *
     * @param gradeId 主键
     * @return 是否成功
     */
    @ApiOperation("通过主键删除数据")
    @DeleteMapping("{gradeId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable String gradeId){
        return ResponseEntity.ok(gradeService.deleteByGradeId(gradeId));
    }
}