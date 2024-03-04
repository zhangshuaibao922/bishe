package com.guet.bishe.controller;


import com.guet.bishe.entity.Major;
import com.guet.bishe.service.MajorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 专业;(major)表控制层
 * @author : cardo
 * @date : 2024-3-4
 */
@Api(tags = "专业对象功能接口")
@RestController
@RequestMapping("/major")
public class MajorController{
    @Autowired
    private MajorService majorService;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param majorId 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{majorId}")
    public ResponseEntity<Major> queryById(@PathVariable String majorId){
        return ResponseEntity.ok(majorService.queryByMajorId(majorId));
    }
    
    /** 
     * 分页查询
     *
     * @param majorId 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return 查询结果
     */
    @ApiOperation("分页查询")
    @GetMapping
    public ResponseEntity<PageImpl<Major>> paginQuery(@RequestParam(required = false) String majorId,
                                                      @RequestParam(required = false) Long current,
                                                      @RequestParam(required = false) Long size){
        //1.分页参数
        PageRequest pageRequest = PageRequest.of(current.intValue(), size.intValue());
        //2.分页查询
        /*把Mybatis的分页对象做封装转换，MP的分页对象上有一些SQL敏感信息，还是通过spring的分页模型来封装数据吧*/
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Major> pageResult = majorService.paginQuery(majorId, current,size);
        //3. 分页结果组装
        List<Major> dataList = pageResult.getRecords();
        long total = pageResult.getTotal();
        PageImpl<Major> retPage = new PageImpl<Major>(dataList,pageRequest,total);
        return ResponseEntity.ok(retPage);
    }
    
    /** 
     * 新增数据
     *
     * @param major 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping
    public ResponseEntity<Boolean> add(@RequestBody Major major){
        return ResponseEntity.ok(majorService.insert(major));
    }
    
    /** 
     * 更新数据
     *
     * @param major 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PutMapping
    public ResponseEntity<Boolean> edit(@RequestBody Major major){
        return ResponseEntity.ok(majorService.update(major));
    }
    
    /** 
     * 通过主键删除数据
     *
     * @param majorId 主键
     * @return 是否成功
     */
    @ApiOperation("通过主键删除数据")
    @DeleteMapping("{majorId}")
    public ResponseEntity<Boolean> deleteByMajorId(@PathVariable String majorId){
        return ResponseEntity.ok(majorService.deleteByMajorId(majorId));
    }
}