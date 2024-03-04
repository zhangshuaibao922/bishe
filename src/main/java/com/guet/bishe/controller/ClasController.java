package com.guet.bishe.controller;



import com.guet.bishe.entity.Clas;
import com.guet.bishe.service.ClasService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


 /**
 * 班级;(clas)表控制层
 * @author : cardo
 * @date : 2024-3-4
 */
@Api(tags = "班级对象功能接口")
@RestController
@RequestMapping("/clas")
public class ClasController{
    @Autowired
    private ClasService clasService;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param classId 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{classId}")
    public ResponseEntity<Clas> queryById(@PathVariable String classId){
        return ResponseEntity.ok(clasService.queryByClassId(classId));
    }

    
    /** 
     * 新增数据
     *
     * @param clas 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping
    public ResponseEntity<Boolean> add(@RequestBody Clas clas){
        return ResponseEntity.ok(clasService.insert(clas));
    }
    
    /** 
     * 更新数据
     *
     * @param clas 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PutMapping
    public ResponseEntity<Boolean> edit(@RequestBody Clas clas){
        return ResponseEntity.ok(clasService.update(clas));
    }
    
    /** 
     * 通过主键删除数据
     *
     * @param classId 主键
     * @return 是否成功
     */
    @ApiOperation("通过主键删除数据")
    @DeleteMapping("{classId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable String classId){
        return ResponseEntity.ok(clasService.deleteByClassId(classId));
    }
}