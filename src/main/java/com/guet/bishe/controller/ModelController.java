package com.guet.bishe.controller;


import com.guet.bishe.entity.Model;
import com.guet.bishe.entity.Response;
import com.guet.bishe.entity.TestModelDto;
import com.guet.bishe.service.ModelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 模版;(model)表控制层
 * @author : cardo
 * @date : 2024-4-18
 */
@Api(tags = "模版对象功能接口")
@RestController
@RequestMapping("/model")
public class ModelController{
    @Autowired
    private ModelService modelService;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{id}")
    public ResponseEntity<Model> queryById(@PathVariable Integer id){
        return ResponseEntity.ok(modelService.queryById(id));
    }

     /**
      * 查询全部数据
      *
      * @param id 类别 1 测试 2 考试
      * @return 实例对象
      */
     @ApiOperation("通过ID查询单条数据")
     @GetMapping("/all/{id}")
     public Response<List<Model>> queryAllById(@PathVariable String id){
         return modelService.queryAllById(id);
     }
    
    /** 
     * 新增数据
     *
     * @param model 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping
    public ResponseEntity<Boolean> add(@RequestBody Model model){
        return ResponseEntity.ok(modelService.insert(model));
    }

    /**
     * 新增数据
     *
     * @param testModelDto 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping("/test")
    public Response<Boolean> InsertTestModelDto(@RequestBody TestModelDto testModelDto){
        return modelService.insertTestModelDto(testModelDto);
    }

    
    /** 
     * 更新数据
     *
     * @param model 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PutMapping
    public ResponseEntity<Boolean> edit(@RequestBody Model model){
        return ResponseEntity.ok(modelService.update(model));
    }
    
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @ApiOperation("通过主键删除数据")
    @DeleteMapping("{id}")
    public Response<Boolean> delete(@PathVariable Integer id){
        return modelService.delete(id);
    }
}