package com.guet.bishe.controller;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guet.bishe.entity.College;
import com.guet.bishe.service.CollegeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


 /**
 * 学院;(college)表控制层
 * @author : cardo
 * @date : 2024-2-27
 */
@Api(tags = "学院对象功能接口")
@RestController
@RequestMapping("/college")
public class CollegeController{
    @Autowired
    private CollegeService collegeService;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param collegeId 学院id
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{collegeId}")
    public ResponseEntity<College> queryById(String collegeId){
        return ResponseEntity.ok(collegeService.queryByCollegeId(collegeId));
    }
    
    /** 
     * 分页查询
     *
     * @param college 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @ApiOperation("分页查询")
    @GetMapping
    public ResponseEntity<PageImpl<College>> paginQuery(College college, PageRequest pageRequest){
        //1.分页参数
        long current = pageRequest.getPageNumber();
        long size = pageRequest.getPageSize();
        //2.分页查询
        /*把Mybatis的分页对象做封装转换，MP的分页对象上有一些SQL敏感信息，还是通过spring的分页模型来封装数据吧*/
        Page<College> pageResult = collegeService.paginQuery(college, current,size);
        //3. 分页结果组装
        List<College> dataList = pageResult.getRecords();
        long total = pageResult.getTotal();
        PageImpl<College> retPage = new PageImpl<College>(dataList,pageRequest,total);
        return ResponseEntity.ok(retPage);
    }
    
    /** 
     * 新增数据
     *
     * @param college 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping
    public ResponseEntity<College> add(College college){
        return ResponseEntity.ok(collegeService.insert(college));
    }
    
    /** 
     * 更新数据
     *
     * @param college 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PutMapping
    public ResponseEntity<College> edit(College college){
        return ResponseEntity.ok(collegeService.update(college));
    }
    
    /** 
     * 通过主键删除数据
     *
     * @param collegeId 主键
     * @return 是否成功
     */
    @ApiOperation("通过主键删除数据")
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(String collegeId){
        return ResponseEntity.ok(collegeService.deleteByCollegeId(collegeId));
    }
}