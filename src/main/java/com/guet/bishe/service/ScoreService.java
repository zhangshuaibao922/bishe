package com.guet.bishe.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.guet.bishe.entity.*;

import java.util.List;

/**
 * 得分;(score)表服务接口
 * @author : cardo
 * @date : 2024-4-17
 */
public interface ScoreService extends IService<Score> {
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    Score queryById(Integer id);

    /** 
     * 新增数据
     *
     * @param score 实例对象
     * @return 实例对象
     */
    boolean insert(Score score);
    /** 
     * 更新数据
     *
     * @param score 实例对象
     * @return 实例对象
     */
    boolean update(Score score);
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

     Response<Boolean> edit(Score score);

     Response<List<StudentScoreDto>> queryByAllScore(String examId,String lessonId);
 }