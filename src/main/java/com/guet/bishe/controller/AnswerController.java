package com.guet.bishe.controller;

import com.guet.bishe.entity.Answer;
import com.guet.bishe.entity.AnswerDto;
import com.guet.bishe.entity.Response;
import com.guet.bishe.entity.StudentScoreDto;
import com.guet.bishe.service.AnswerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 试卷;(answer)表控制层
 *
 * @author : cardo
 * @date : 2024-4-17
 */
@Api(tags = "试卷对象功能接口")
@RestController
@RequestMapping("/answer")
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{id}")
    public ResponseEntity<Answer> queryById(@PathVariable Integer id) {
        return ResponseEntity.ok(answerService.queryById(id));
    }

    /**
     * 通过ID查询单条数据
     *
     * @param examId  answerId
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping("/{examId}/{answerId}/{teacherId}")
    public Response<List<Answer>> queryByExamIdAndTeacherId(@PathVariable String examId, @PathVariable String answerId, @PathVariable String teacherId) {
        return answerService.queryByExamIdAndTeacherId(examId,answerId,teacherId);
    }

    /**
     * 新增数据
     *
     * @param answer 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping
    public ResponseEntity<Boolean> add(@RequestBody Answer answer) {
        return ResponseEntity.ok(answerService.insert(answer));
    }

    /**
     * 更新数据
     *
     * @param answer 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PutMapping
    public ResponseEntity<Boolean> edit(@RequestBody Answer answer) {
        return ResponseEntity.ok(answerService.update(answer));
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @ApiOperation("通过主键删除数据")
    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Integer id) {
        return ResponseEntity.ok(answerService.deleteById(id));
    }

    /**
     * 通过主键删除数据
     *
     * @param studentScoreDto 主键
     * @return 是否成功
     */
    @ApiOperation("学生每道题成绩")
    @PutMapping("/getStudentScore")
    public Response<List<AnswerDto>> getStudentScoreAllAnswer(@RequestBody StudentScoreDto studentScoreDto) {
        return answerService.getStudentScoreAllAnswer(studentScoreDto);
    }
}