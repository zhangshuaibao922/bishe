package com.guet.bishe.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.bishe.entity.*;
import com.guet.bishe.mapper.*;
import com.guet.bishe.service.SettingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 考试设置;(seting)表服务实现类
 *
 * @author : cardo
 * @date : 2024-4-17
 */
@Service
public class SettingServiceImpl extends ServiceImpl<SettingMapper, Setting> implements SettingService {
    @Autowired
    private SettingMapper settingMapper;

    @Autowired
    private ExamMapper examMapper;
    @Autowired
    private PaperMapper paperMapper;

    @Autowired
    private AnswerMapper answerMapper;
    @Autowired
    private ScoreMapper scoreMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public Setting queryById(Integer id) {
//        LambdaQueryWrapper<Setting> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        lambdaQueryWrapper.eq();
        return settingMapper.selectById(id);
    }

    /**
     * 新增数据
     *
     * @param setting 实例对象
     * @return 实例对象
     */
    public boolean insert(Setting setting) {
        return settingMapper.insert(setting) > 0;
    }

    /**
     * 更新数据
     *
     * @param setting 实例对象
     * @return 实例对象
     */
    public boolean update(Setting setting) {
        //1. 根据条件动态更新
        LambdaQueryWrapper<Setting> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Setting::getTeacherId, setting.getTeacherId())
                .eq(Setting::getExamSet, setting.getExamSet())
                .eq(Setting::getAnswerId, setting.getAnswerId());
        setting.setId(settingMapper.selectOne(lambdaQueryWrapper).getId());
        //2. 设置主键，并更新
        return settingMapper.updateById(setting) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(Integer id) {
//        LambdaQueryWrapper<Setting> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        lambdaQueryWrapper.eq();
        return settingMapper.deleteById(id) > 0;
    }

    @Override
    public Response<List<SettingDto>> queryByTeacherIdAndExamSet(String teacherId, String examId) {
        Exam exam = examMapper.selectOne(new LambdaQueryWrapper<Exam>().eq(Exam::getExamId, examId));

        LambdaQueryWrapper<Setting> setingLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setingLambdaQueryWrapper.eq(Setting::getTeacherId, teacherId)
                .eq(Setting::getExamSet, exam.getExamSet())
                .orderByAsc(Setting::getAnswerId)
                .isNull(Setting::getAllScore);
        List<Setting> settings = settingMapper.selectList(setingLambdaQueryWrapper);
        Response<List<SettingDto>> listResponse = new Response<>();

        ArrayList<SettingDto> settingDtos = new ArrayList<>();
        for (Setting setting : settings) {
            SettingDto newDto = new SettingDto();
            BeanUtils.copyProperties(setting, newDto);
            LambdaQueryWrapper<Paper> paperLambdaQueryWrapper = new LambdaQueryWrapper<>();
            paperLambdaQueryWrapper.eq(Paper::getExamId, examId);
            List<Paper> papers = paperMapper.selectList(paperLambdaQueryWrapper);
            List<String> list = papers.stream().map(Paper::getPaperId).toList();
            if (list.isEmpty()) {
                listResponse.setData(null);
                listResponse.setCode(201);
                return listResponse;
            } else {
                LambdaQueryWrapper<Answer> answerLambdaQueryWrapper = new LambdaQueryWrapper<>();
                answerLambdaQueryWrapper.in(Answer::getPaperId, list).eq(Answer::getAnswerId, setting.getAnswerId()).groupBy(Answer::getAnswerId);
                Integer integer = answerMapper.selectCount(answerLambdaQueryWrapper);
                newDto.setAnswerNumbers(integer);
                settingDtos.add(newDto);

            }
        }
        listResponse.setData(settingDtos);
        LambdaQueryWrapper<Setting> settingLambdaQueryWrapper = new LambdaQueryWrapper<>();
        settingLambdaQueryWrapper.eq(Setting::getExamSet,exam.getExamSet())
                .eq(Setting::getAllScore,"1");
        List<Setting> settings1 = settingMapper.selectList(settingLambdaQueryWrapper);
        if(settings1.size() !=0){
            if(settings1.get(0).getNum()==1){
                //不需要异常检查
            }else {

                LambdaQueryWrapper<Paper> paperLambdaQueryWrapper1 = new LambdaQueryWrapper<>();
                paperLambdaQueryWrapper1.eq(Paper::getExamId, examId);
                List<Paper> papers = paperMapper.selectList(paperLambdaQueryWrapper1);
                List<String> paperIds = papers.stream().map(Paper::getPaperId).toList();

                List<String> list = settings1.stream().map(Setting::getAnswerId).distinct().toList();
                //去重
                for (String answerId : list){
                    LambdaQueryWrapper<Answer> answerLambdaQueryWrapper = new LambdaQueryWrapper<>();
                    answerLambdaQueryWrapper
                            .in(Answer::getPaperId,paperIds)
                            .eq(Answer::getIsScore,1)
                            .eq(Answer::getAnswerId,answerId);
                    List<Answer> answers = answerMapper.selectList(answerLambdaQueryWrapper);
                    for (Answer  answer : answers){
                        LambdaQueryWrapper<Score> scoreLambdaQueryWrapper = new LambdaQueryWrapper<>();
                        scoreLambdaQueryWrapper.eq(Score::getPaperId,answer.getPaperId())
                                .eq(Score::getAnswerId,answerId);
                        List<Score> scores = scoreMapper.selectList(scoreLambdaQueryWrapper);
                        ArrayList<Integer> diff = new ArrayList<>();
                        for (int i = 0; i <scores.size(); i++) {
                            if(i>0){
                                Integer answerScore = scores.get(i).getAnswerScore();
                                Integer oldScore = scores.get(i-1).getAnswerScore();
                                diff.add(Math.abs(oldScore-answerScore));
                            }
                        }
                        for (Integer data : diff){
                            if(data>=5){
                                scoreMapper.delete(scoreLambdaQueryWrapper);
                                Answer answer1 = new Answer();

                                answer1.paperId=answer.paperId;
                                answer1.answerId=answer.answerId;
                                answer1.answerUrl=answer.answerUrl;

                                answerMapper.deleteById(answer);
                                answerMapper.insert(answer1);

                                LambdaQueryWrapper<Setting> settingLambdaQueryWrapper1 = new LambdaQueryWrapper<>();
                                settingLambdaQueryWrapper1.eq(Setting::getExamSet,exam.getExamSet())
                                        .eq(Setting::getAllScore,"1")
                                        .eq(Setting::getAnswerId,answer.answerId);
                                List<Setting> settings2 = settingMapper.selectList(settingLambdaQueryWrapper1);
                                for (Setting setting : settings2){
                                    Setting newSetting =  new Setting();
                                    newSetting.setNum(setting.num);
                                    newSetting.setExamSet(setting.examSet);
                                    newSetting.setAnswerId(setting.answerId);
                                    newSetting.setTeacherId(setting.teacherId);
                                    newSetting.setCount(setting.count);
                                    settingMapper.deleteById(setting);
                                    settingMapper.insert(newSetting);
                                }
                                listResponse.setMessage("存在异常得分情况，请重新批阅");
                                break;
                            }
                        }
                    }
                }
            }
        }

        return listResponse;
    }
}