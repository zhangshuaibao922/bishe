package com.guet.bishe.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guet.bishe.entity.Answer;
import com.guet.bishe.entity.AnswerDto;
import com.guet.bishe.entity.Response;
import com.guet.bishe.entity.StudentScoreDto;

import java.util.List;


/**
 * 试卷;(answer)表服务接口
 *
 * @author : cardo
 * @date : 2024-4-17
 */
public interface AnswerService extends IService<Answer> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Answer queryById(Integer id);

    /**
     * 新增数据
     *
     * @param answer 实例对象
     * @return 实例对象
     */
    boolean insert(Answer answer);

    /**
     * 更新数据
     *
     * @param answer 实例对象
     * @return 实例对象
     */
    boolean update(Answer answer);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    Response<List<Answer>> queryByExamIdAndTeacherId(String examId, String answerId, String teacherId);

    Response<List<AnswerDto>> getStudentScoreAllAnswer(StudentScoreDto studentScoreDto);
}