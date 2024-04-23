package com.guet.bishe.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.bishe.entity.Answer;
import com.guet.bishe.entity.Response;
import com.guet.bishe.entity.Score;
import com.guet.bishe.mapper.AnswerMapper;
import com.guet.bishe.mapper.ScoreMapper;
import com.guet.bishe.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 得分;(score)表服务实现类
 *
 * @author : cardo
 * @date : 2024-4-17
 */
@Service
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements ScoreService {
    @Autowired
    private ScoreMapper scoreMapper;
    @Autowired
    private AnswerMapper answerMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public Score queryById(Integer id) {
//        LambdaQueryWrapper<Score> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        lambdaQueryWrapper.eq();
        return scoreMapper.selectById(id);
    }

    /**
     * 新增数据
     *
     * @param score 实例对象
     * @return 实例对象
     */
    public boolean insert(Score score) {
        return scoreMapper.insert(score) > 0;
    }

    /**
     * 更新数据
     *
     * @param score 实例对象
     * @return 实例对象
     */
    public boolean update(Score score) {
        //1. 根据条件动态更新
        LambdaQueryWrapper<Score> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Score::getId, score.getId());
        //2. 设置主键，并更新
        return scoreMapper.update(score, lambdaQueryWrapper) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(Integer id) {
//        LambdaQueryWrapper<Score> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        lambdaQueryWrapper.eq();
        return scoreMapper.deleteById(id) > 0;
    }

    @Override
    public Response<Boolean> edit(Score score) {
        Response<Boolean> response = new Response<>();
        LambdaQueryWrapper<Score> scoreLambdaQueryWrapper = new LambdaQueryWrapper<>();
        scoreLambdaQueryWrapper
                .eq(Score::getPaperId, score.getPaperId())
                .eq(Score::getAnswerId, score.getAnswerId())
                .eq(Score::getTeacherId, score.getTeacherId());
        Score score1 = scoreMapper.selectOne(scoreLambdaQueryWrapper);
        if (score1 == null) {
            scoreMapper.insert(score);
            if (score.getNum() == 1) {
                LambdaQueryWrapper<Answer> answerLambdaQueryWrapper = new LambdaQueryWrapper<>();
                answerLambdaQueryWrapper
                        .eq(Answer::getAnswerId, score.getAnswerId())
                        .eq(Answer::getPaperId, score.getPaperId());
                Answer answer = answerMapper.selectOne(answerLambdaQueryWrapper);
                answer.setIsScore(1);
                answerMapper.updateById(answer);
                response.setData(true);
                return response;
            } else {
                LambdaQueryWrapper<Score> scoreLambdaQueryWrapper1 = new LambdaQueryWrapper<>();
                scoreLambdaQueryWrapper1
                        .eq(Score::getPaperId, score.getPaperId())
                        .eq(Score::getAnswerId, score.getAnswerId());
                Integer integer = scoreMapper.selectCount(scoreLambdaQueryWrapper1);
                if (Objects.equals(integer, score.getNum())) {
                    LambdaQueryWrapper<Answer> answerLambdaQueryWrapper = new LambdaQueryWrapper<>();
                    answerLambdaQueryWrapper
                            .eq(Answer::getAnswerId, score.getAnswerId())
                            .eq(Answer::getPaperId, score.getPaperId());
                    Answer answer = answerMapper.selectOne(answerLambdaQueryWrapper);
                    answer.setIsScore(1);
                    answerMapper.updateById(answer);
                    response.setData(true);
                } else {
                    response.setData(true);
                }
                return response;
            }
        } else {
            score.setId(score1.getId());
            scoreMapper.updateById(score);
            if (score.getNum() == 1) {
                LambdaQueryWrapper<Answer> answerLambdaQueryWrapper = new LambdaQueryWrapper<>();
                answerLambdaQueryWrapper
                        .eq(Answer::getAnswerId, score.getAnswerId())
                        .eq(Answer::getPaperId, score.getPaperId());
                Answer answer = answerMapper.selectOne(answerLambdaQueryWrapper);
                answer.setIsScore(1);
                answerMapper.updateById(answer);
                response.setData(true);
                return response;
            } else {
                LambdaQueryWrapper<Score> scoreLambdaQueryWrapper1 = new LambdaQueryWrapper<>();
                scoreLambdaQueryWrapper1
                        .eq(Score::getPaperId, score.getPaperId())
                        .eq(Score::getAnswerId, score.getAnswerId());
                Integer integer = scoreMapper.selectCount(scoreLambdaQueryWrapper1);
                if (Objects.equals(integer, score.getNum())) {
                        LambdaQueryWrapper<Answer> answerLambdaQueryWrapper = new LambdaQueryWrapper<>();
                        answerLambdaQueryWrapper
                                .eq(Answer::getAnswerId, score.getAnswerId())
                                .eq(Answer::getPaperId, score.getPaperId());
                        Answer answer = answerMapper.selectOne(answerLambdaQueryWrapper);
                        answer.setIsScore(1);
                        answerMapper.updateById(answer);
                        response.setData(true);
                    } else {
                    response.setData(true);
                }
                return response;
            }
        }
    }
}