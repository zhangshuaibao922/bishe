package com.guet.bishe.controller;



import com.guet.bishe.entity.*;
import com.guet.bishe.service.ScoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 得分;(score)表控制层
 * @author : cardo
 * @date : 2024-4-17
 */
@Api(tags = "得分对象功能接口")
@RestController
@RequestMapping("/score")
public class ScoreController{
    @Autowired
    private ScoreService scoreService;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{id}")
    public ResponseEntity<Score> queryById(@PathVariable Integer id){
        return ResponseEntity.ok(scoreService.queryById(id));
    }
    
    /** 
     * 新增数据
     *
     * @param score 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping
    public ResponseEntity<Boolean> add(@RequestBody Score score){
        return ResponseEntity.ok(scoreService.insert(score));
    }
    
    /** 
     * 更新数据
     *
     * @param score 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PutMapping
    public Response<Boolean> edit(@RequestBody Score score){
        return scoreService.edit(score);
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
        return ResponseEntity.ok(scoreService.deleteById(id));
    }

     @ApiOperation("通过ID查询单条数据")
     @GetMapping("/getScore/{examId}/{lessonId}")
     public Response<List<StudentScoreDto>> queryAllScore(@PathVariable String examId,@PathVariable String lessonId){
         return scoreService.queryByAllScore(examId,lessonId);
     }
}