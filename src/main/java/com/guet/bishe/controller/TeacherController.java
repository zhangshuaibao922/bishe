package com.guet.bishe.controller;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.guet.bishe.entity.Teacher;
import com.guet.bishe.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


 /**
 * 教师;(teacher)表控制层
 * @author : cardo
 * @date : 2024-2-28
 */
@Api(tags = "教师对象功能接口")
@RestController
@RequestMapping("/teacher")
public class TeacherController{
    @Autowired
    private TeacherService teacherService;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param teacherId 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{teacherId}")
    public ResponseEntity<Teacher> queryById(@PathVariable String teacherId){
        LambdaQueryWrapper<Teacher> teacherLambdaQueryWrapper = new LambdaQueryWrapper<>();
        return ResponseEntity.ok(teacherService.selectByTeacherId(teacherId));
    }

    
//    /**
//     * 分页查询
//     *
//     * @param teacher 筛选条件
//     * @param pageRequest 分页对象
//     * @return 查询结果
//     */
////    @ApiOperation("分页查询")
////    @GetMapping
////    public ResponseEntity<PageImpl<Teacher>> paginQuery(Teacher teacher, PageRequest pageRequest){
////        //1.分页参数
////        long current = pageRequest.getPageNumber();
////        long size = pageRequest.getPageSize();
////        //2.分页查询
////        /*把Mybatis的分页对象做封装转换，MP的分页对象上有一些SQL敏感信息，还是通过spring的分页模型来封装数据吧*/
////        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Teacher> pageResult = teacherService.paginQuery(teacher, current,size);
////        //3. 分页结果组装
////        List<Teacher> dataList = pageResult.getRecords();
////        long total = pageResult.getTotal();
////        PageImpl<Teacher> retPage = new PageImpl<Teacher>(dataList,pageRequest,total);
////        return ResponseEntity.ok(retPage);
////    }
//
//    /**
//     * 新增数据
//     *
//     * @param teacher 实例对象
//     * @return 实例对象
//     */
//    @ApiOperation("新增数据")
//    @PostMapping
//    public ResponseEntity<Teacher> add(Teacher teacher){
//        return ResponseEntity.ok(teacherService.insert(teacher));
//    }
//
//    /**
//     * 更新数据
//     *
//     * @param teacher 实例对象
//     * @return 实例对象
//     */
//    @ApiOperation("更新数据")
//    @PutMapping
//    public ResponseEntity<Teacher> edit(Teacher teacher){
//        return ResponseEntity.ok(teacherService.update(teacher));
//    }
//
//    /**
//     * 通过主键删除数据
//     *
//     * @param teacherId 主键
//     * @return 是否成功
//     */
//    @ApiOperation("通过主键删除数据")
//    @DeleteMapping
//    public ResponseEntity<Boolean> deleteById(String teacherId){
//        return ResponseEntity.ok(teacherService.deleteByTeacherId(teacherId));
//    }
}