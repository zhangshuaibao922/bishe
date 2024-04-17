package com.guet.bishe.controller;


import com.guet.bishe.entity.Seting;
import com.guet.bishe.service.SetingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

 /**
 * 考试设置;(seting)表控制层
 * @author : cardo
 * @date : 2024-4-17
 */
@Api(tags = "考试设置对象功能接口")
@RestController
@RequestMapping("/seting")
public class SetingController{
    @Autowired
    private SetingService setingService;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{id}")
    public ResponseEntity<Seting> queryById(@PathVariable Integer id){
        return ResponseEntity.ok(setingService.queryById(id));
    }
    
    /** 
     * 新增数据
     *
     * @param seting 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping
    public ResponseEntity<Boolean> add(@RequestBody Seting seting){
        return ResponseEntity.ok(setingService.insert(seting));
    }
    
    /** 
     * 更新数据
     *
     * @param seting 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PutMapping
    public ResponseEntity<Boolean> edit(@RequestBody Seting seting){
        return ResponseEntity.ok(setingService.update(seting));
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
        return ResponseEntity.ok(setingService.deleteById(id));
    }
}