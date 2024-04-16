package com.guet.bishe.controller;
import java.util.List;

import com.guet.bishe.entity.Choose;
import com.guet.bishe.entity.Lesson;
import com.guet.bishe.entity.Response;
import com.guet.bishe.entity.Student;
import com.guet.bishe.service.ChooseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


 /**
 * 选课;(choose)表控制层
 * @author : cardo
 * @date : 2024-4-10
 */
@Api(tags = "选课对象功能接口")
@RestController
@RequestMapping("/choose")
public class ChooseController{
    @Autowired
    private ChooseService chooseService;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param studentId 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{studentId}")
    public ResponseEntity<List<Student>> queryById(@PathVariable String studentId){
        return ResponseEntity.ok(chooseService.queryById(studentId));
    }

     /**
      * 查询全部课程
      */
     @ApiOperation("返回课程列表")
     @GetMapping("/all/{lessonId}")
     public Response<List<Student>> queryAll(@PathVariable String lessonId) {
         return chooseService.queryAll(lessonId);
     }


     /**
     * 新增数据
     *
     * @param choose 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping
    public ResponseEntity<Boolean> add(@RequestBody Choose choose){
        return ResponseEntity.ok(chooseService.insert(choose));
    }
    
    /** 
     * 更新数据
     *
     * @param choose 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PutMapping
    public ResponseEntity<Boolean> edit(@RequestBody Choose choose){
        return ResponseEntity.ok(chooseService.update(choose));
    }
    
    /** 
     * 通过主键删除数据
     *
     * @param studentId 主键
     * @return 是否成功
     */
    @ApiOperation("通过主键删除数据")
    @DeleteMapping("{studentId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable String studentId){
        return ResponseEntity.ok(chooseService.deleteById(studentId));
    }
}