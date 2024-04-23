package com.guet.bishe.controller;


import com.guet.bishe.entity.Response;
import com.guet.bishe.entity.Setting;
import com.guet.bishe.entity.SettingDto;
import com.guet.bishe.service.SettingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 考试设置;(setting)表控制层
 * @author : cardo
 * @date : 2024-4-17
 */
@Api(tags = "考试设置对象功能接口")
@RestController
@RequestMapping("/setting")
public class SettingController {
    @Autowired
    private SettingService settingService;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{id}")
    public ResponseEntity<Setting> queryById(@PathVariable Integer id){
        return ResponseEntity.ok(settingService.queryById(id));
    }


     @ApiOperation("通过ID查询单条数据")
     @GetMapping("/{teacherId}/{examId}")
     public Response<List<SettingDto>> queryByTeacherIdAndExamSet(@PathVariable String teacherId, @PathVariable String examId){
         return settingService.queryByTeacherIdAndExamSet(teacherId,examId);
     }
    
    /** 
     * 新增数据
     *
     * @param setting 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping
    public ResponseEntity<Boolean> add(@RequestBody Setting setting){
        return ResponseEntity.ok(settingService.insert(setting));
    }
    
    /** 
     * 更新数据
     *
     * @param setting 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PutMapping
    public ResponseEntity<Boolean> edit(@RequestBody Setting setting){
        return ResponseEntity.ok(settingService.update(setting));
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
        return ResponseEntity.ok(settingService.deleteById(id));
    }
}