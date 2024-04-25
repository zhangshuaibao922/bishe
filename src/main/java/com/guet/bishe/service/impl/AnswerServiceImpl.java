package com.guet.bishe.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.bishe.entity.*;
import com.guet.bishe.mapper.*;
import com.guet.bishe.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 试卷;(answer)表服务实现类
 * @author : cardo
 * @date : 2024-4-17
 */
@Service
public class AnswerServiceImpl extends ServiceImpl<AnswerMapper, Answer> implements AnswerService {
    @Autowired
    private AnswerMapper answerMapper;
    @Autowired
    private ExamMapper examMapper;
    @Autowired
    private PaperMapper paperMapper;
    @Autowired
    private SettingMapper settingMapper;
    @Autowired
    private ScoreMapper scoreMapper;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    public Answer queryById(Integer id){
//        LambdaQueryWrapper<Answer> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        lambdaQueryWrapper.eq();
        return answerMapper.selectById(id);
    }

    
    /** 
     * 新增数据
     *
     * @param answer 实例对象
     * @return 实例对象
     */
    public boolean insert(Answer answer){
        return answerMapper.insert(answer)>0;
    }
    
    /** 
     * 更新数据
     *
     * @param answer 实例对象
     * @return 实例对象
     */
    public boolean update(Answer answer){
        //1. 根据条件动态更新
        LambdaQueryWrapper<Answer> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Answer::getAnswerId,answer.getAnswerId());
        //2. 设置主键，并更新
        return answerMapper.update(answer, lambdaQueryWrapper)>0;
    }
    
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(Integer id){
//        LambdaQueryWrapper<Answer> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        lambdaQueryWrapper.eq();
        return answerMapper.deleteById(id)> 0;
    }

    @Override
    public Response<List<Answer>> queryByExamIdAndTeacherId(String examId, String answerId,String teacherId) {
        Response<List<Answer>> listResponse = new Response<>();
        LambdaQueryWrapper<Exam> examLambdaQueryWrapper = new LambdaQueryWrapper<>();
        examLambdaQueryWrapper.eq(Exam::getExamId,examId);
        Exam exam = examMapper.selectOne(examLambdaQueryWrapper);
        if (exam != null){
            LambdaQueryWrapper<Paper> paperLambdaQueryWrapper = new LambdaQueryWrapper<Paper>();
            paperLambdaQueryWrapper.eq(Paper::getExamId,exam.getExamId());
            List<Paper> papers = paperMapper.selectList(paperLambdaQueryWrapper);
            if (papers != null){
                List<String> list = papers.stream().map(Paper::getPaperId).toList();
                LambdaQueryWrapper<Answer> answerLambdaQueryWrapper = new LambdaQueryWrapper<>();
                answerLambdaQueryWrapper
                        .in(Answer::getPaperId,list)
                        .eq(Answer::getAnswerId,answerId)
                        .isNull(Answer::getIsScore)
                        .last("LIMIT 20");
                List<Answer> answers = answerMapper.selectList(answerLambdaQueryWrapper);
                if(answers.size() == 0){
                    LambdaQueryWrapper<Answer> answerLambdaQueryWrapper1 = new LambdaQueryWrapper<>();
                    answerLambdaQueryWrapper1
                            .in(Answer::getPaperId,list)
                            .eq(Answer::getAnswerId,answerId)
                            .eq(Answer::getIsScore,1)
                            .last("LIMIT 20");
                    List<Answer> answers1 = answerMapper.selectList(answerLambdaQueryWrapper1);
                    if(answers1.size()!=0){
                        Exam exam1 = examMapper.selectOne(new LambdaQueryWrapper<Exam>()
                                .eq(Exam::getExamId, examId));
                        List<Setting> settings = settingMapper.selectList(new LambdaQueryWrapper<Setting>()
                                .eq(Setting::getExamSet, exam1.getExamSet())
                                .eq(Setting::getAnswerId, answerId));
                        for (Setting setting : settings){
                            setting.setAllScore("1");
                            settingMapper.updateById(setting);
                        }
                        listResponse.setData(null);
                        listResponse.setCode(250);
                    }
                }else {
                    LambdaQueryWrapper<Score> scoreLambdaQueryWrapper = new LambdaQueryWrapper<>();
                    scoreLambdaQueryWrapper
                            .eq(Score::getAnswerId,answerId)
                            .in(Score::getPaperId,list)
                            .eq(Score::getTeacherId,teacherId);
                    Integer integer = scoreMapper.selectCount(scoreLambdaQueryWrapper);
                    LambdaQueryWrapper<Answer> answerLambdaQueryWrapper1 = new LambdaQueryWrapper<>();
                    answerLambdaQueryWrapper1
                            .in(Answer::getPaperId,list)
                            .eq(Answer::getAnswerId,answerId);
                    Integer integer1 = answerMapper.selectCount(answerLambdaQueryWrapper1);
                    if(Objects.equals(integer, integer1)){
                        LambdaQueryWrapper<Setting> settingLambdaQueryWrapper = new LambdaQueryWrapper<>();
                        settingLambdaQueryWrapper
                                .eq(Setting::getExamSet,exam.getExamSet())
                                .eq(Setting::getAnswerId,answerId)
                                .eq(Setting::getTeacherId,teacherId);
                        Setting setting = settingMapper.selectOne(settingLambdaQueryWrapper);
                        setting.setAllScore("1");
                        settingMapper.updateById(setting);
                        listResponse.setData(null);
                        listResponse.setCode(250);
                    }else {
                        answers.forEach(answer -> {answer.setAnswerUrl("http://zhangshuaibao.top/"+answer.getAnswerUrl());});
                        listResponse.setData(answers);
                    }
                }
            }else {
                listResponse.setData(null);
                listResponse.setCode(201);
            }
        }else {
            listResponse.setData(null);
            listResponse.setCode(201);
        }
        return listResponse;
    }

    @Override
    public Response<List<AnswerDto>> getStudentScoreAllAnswer(StudentScoreDto studentScoreDto) {
        List<Paper> papers = paperMapper.selectList(new LambdaQueryWrapper<Paper>()
                .eq(Paper::getExamId, studentScoreDto.examId)
                .eq(Paper::getStudentId, studentScoreDto.studentId));
        Response<List<AnswerDto>> listResponse = new Response<List<AnswerDto>>();
        ArrayList<AnswerDto> answerDtos = new ArrayList<AnswerDto>();
        for (Paper paper :papers){
            List<Answer> answers = answerMapper.selectList(new LambdaQueryWrapper<Answer>()
                    .eq(Answer::getPaperId, paper.paperId)
                    .orderByAsc(Answer::getAnswerId));
            for (Answer answer :answers) {
                AnswerDto answerDto = new AnswerDto();
                answerDto.setAnswerId(answer.answerId);
                answerDto.setPaperId(paper.paperId);
                answerDto.setAnswerUrl("http://zhangshuaibao.top/"+answer.answerUrl);
                List<Score> scores = scoreMapper.selectList(new LambdaQueryWrapper<Score>()
                        .eq(Score::getPaperId, paper.paperId)
                        .eq(Score::getAnswerId, answer.answerId));
                double AllScore =0.000000;
                for (Score c : scores){
                    AllScore=AllScore+c.answerScore;
                }
                answerDto.setAvgScore(AllScore/scores.size());
                answerDtos.add(answerDto);
            }
        }
        listResponse.setData(answerDtos);
        return listResponse;
    }
}