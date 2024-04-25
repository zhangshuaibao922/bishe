package com.guet.bishe.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.bishe.entity.*;
import com.guet.bishe.mapper.*;
import com.guet.bishe.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.util.ArrayList;
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
    @Autowired
    private PaperMapper paperMapper;
    @Autowired
    private ExamMapper examMapper;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ChooseMapper chooseMapper;
    @Autowired
    private StudentMapper studentMapper;

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

    @Override
    public Response<List<StudentScoreDto>> queryByAllScore(String examId,String lessonId,String studentId) {
        //计算得分
        List<Paper> papers = paperMapper.selectList(new LambdaQueryWrapper<Paper>().eq(Paper::getExamId, examId));
        for (Paper paper : papers){
            if(paper.totalScore>0){
            }else {
                List<Answer> answers = answerMapper.selectList(new LambdaQueryWrapper<Answer>()
                        .eq(Answer::getPaperId, paper.getPaperId()));
                List<String> answerIds = answers.stream().map(Answer::getAnswerId).distinct().toList();
                List<Score> scores = scoreMapper.selectList(new LambdaQueryWrapper<Score>()
                        .eq(Score::getPaperId, paper.getPaperId())
                        .in(Score::getAnswerId, answerIds)
                        .orderByAsc(Score::getAnswerId));
                double allCount = 0.000000;
                for (int i = 0; i < scores.size();) {
                    Integer num = scores.get(i).getNum();
                    double number=0.000000;
                    for (int j = 0; j < num; j++) {
                        number=number+scores.get(i+j).getAnswerScore();
                    }
                    i=i+num;
                    allCount=allCount+number/num;
                }
                if (allCount!=0){
                    paper.setTotalScore(allCount);
                    paperMapper.updateById(paper);
                }
            }
        }
        //查询结果返回
        Response<List<StudentScoreDto>> listResponse = new Response<>();
        ArrayList<StudentScoreDto> studentScoreDtos = new ArrayList<StudentScoreDto>();
        List<Choose> chooses;
        if(Objects.equals(studentId, "1")){
            chooses = chooseMapper.selectList(new LambdaQueryWrapper<Choose>().eq(Choose::getLessonId,lessonId));

        }else {
            chooses = chooseMapper.selectList(new LambdaQueryWrapper<Choose>().eq(Choose::getLessonId,lessonId)
                    .eq(Choose::getStudentId,studentId));
        }
        List<String> list = chooses.stream().map(Choose::getStudentId).toList();
        List<Student> students = studentMapper.selectList(new LambdaQueryWrapper<Student>().in(Student::getStudentId, list));
        for (Student student : students) {
            StudentScoreDto studentScoreDto = new StudentScoreDto();
            studentScoreDto.setStudentName(student.getStudentName());
            studentScoreDto.setStudentId(student.getStudentId());
            studentScoreDto.setExamId(examId);

            LambdaQueryWrapper<Paper> paperLambdaQueryWrapper = new LambdaQueryWrapper<>();
            paperLambdaQueryWrapper.eq(Paper::getStudentId, student.getStudentId());
            paperLambdaQueryWrapper.eq(Paper::getExamId, examId);
            paperLambdaQueryWrapper.orderByAsc(Paper::getSequence);
            List<Paper> studenpaper = paperMapper.selectList(paperLambdaQueryWrapper);
            double avgScore = 0.00000;
            for (Paper paper :studenpaper) {
                avgScore=avgScore+paper.getTotalScore();
            }
            studentScoreDto.setTotalScore(avgScore);

            studentScoreDtos.add(studentScoreDto);
        }
        listResponse.setData(studentScoreDtos);
        return listResponse;
    }
}