package com.guet.bishe.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.bishe.Utils.SnowflakeIdGenerator;
import com.guet.bishe.entity.*;
import com.guet.bishe.mapper.*;
import com.guet.bishe.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 模版;(model)表服务实现类
 *
 * @author : cardo
 * @date : 2024-4-18
 */
@Service
public class ModelServiceImpl extends ServiceImpl<ModelMapper, Model> implements ModelService {
    @Autowired
    private ModelMapper modelMapper;
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
    public Model queryById(Integer id) {
//        LambdaQueryWrapper<Model> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        lambdaQueryWrapper.eq();
        return modelMapper.selectById(id);
    }

    /**
     * 新增数据
     *
     * @param model 实例对象
     * @return 实例对象
     */
    public boolean insert(Model model) {
        return modelMapper.insert(model) > 0;
    }

    /**
     * 更新数据
     *
     * @param model 实例对象
     * @return 实例对象
     */
    public boolean update(Model model) {
        //1. 根据条件动态更新
        LambdaQueryWrapper<Model> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Model::getId, model.getId());
        //2. 设置主键，并更新
        return modelMapper.update(model, lambdaQueryWrapper) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(Integer id) {
//        LambdaQueryWrapper<Model> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        lambdaQueryWrapper.eq();
        return modelMapper.deleteById(id) > 0;
    }

    @Override
    public Response<List<Model>> queryAllById(String id) {
        LambdaQueryWrapper<Model> modelLambdaQueryWrapper = new LambdaQueryWrapper<>();
        modelLambdaQueryWrapper.eq(Model::getModelClass, id);
        List<Model> models = modelMapper.selectList(modelLambdaQueryWrapper);
        Response<List<Model>> listResponse = new Response<>();
        if (models.size() == 0) {
            listResponse.setData(null);
            listResponse.setMessage("不存在");
        } else {
            listResponse.setData(models);
            listResponse.setMessage("查询成功");
        }
        return listResponse;
    }

    @Override
    public Response<Boolean> insertTestModelDto(TestModelDto testModelDto) {
        LambdaQueryWrapper<Model> modelLambdaQueryWrapper = new LambdaQueryWrapper<>();
        modelLambdaQueryWrapper.eq(Model::getPaperClassId, testModelDto.getPaperClassId());
        Model model = modelMapper.selectOne(modelLambdaQueryWrapper);
        SnowflakeIdGenerator snowflakeIdGenerator = new SnowflakeIdGenerator(1);
        String examSet = snowflakeIdGenerator.nextIdAsString();
        Response<Boolean> response = new Response<>();
        for (int i = 0; i < Integer.parseInt(model.getModelNumber()); i++) {
            Setting setting = new Setting();
            setting.setExamSet(examSet);
            setting.setTeacherId(testModelDto.getTeacherId());
            setting.setAnswerId(String.valueOf(i + 1));
            setting.setNum(1);
            setting.setCount(20);
            int insert = settingMapper.insert(setting);
            if (insert <= 0) {
                response.setData(false);
                return response;
            }
        }
        Exam exam = examMapper.selectById(testModelDto.getId());
        exam.setExamSet(examSet);
        exam.setPaperClassId(testModelDto.getPaperClassId());
        LambdaQueryWrapper<Exam> examLambdaQueryWrapper = new LambdaQueryWrapper<>();
        examLambdaQueryWrapper.eq(Exam::getId, testModelDto.getId());
        int update = examMapper.update(exam, examLambdaQueryWrapper);
        if (update <= 0) {
            response.setData(false);
            return response;
        }
        response.setData(true);
        return response;
    }

    @Override
    public Response<Boolean> delete(Integer id) {
        Exam exam = examMapper.selectById(id);
        Response<Boolean> response = new Response<>();
        if (exam.getExamSet().length() != 0) {
            LambdaQueryWrapper<Setting> setingLambdaQueryWrapper = new LambdaQueryWrapper<>();
            setingLambdaQueryWrapper.eq(Setting::getExamSet, exam.getExamSet());
            settingMapper.delete(setingLambdaQueryWrapper);

            LambdaQueryWrapper<Paper> paperLambdaQueryWrapper = new LambdaQueryWrapper<>();
            paperLambdaQueryWrapper.eq(Paper::getExamId,exam.getExamId());

            List<Paper> papers = paperMapper.selectList(paperLambdaQueryWrapper);
            paperMapper.delete(paperLambdaQueryWrapper);
            List<String> paperIds =  papers.stream().map(Paper::getPaperId).distinct().toList();

            LambdaQueryWrapper<Answer> answerLambdaQueryWrapper = new LambdaQueryWrapper<>();
            answerLambdaQueryWrapper.in(Answer::getPaperId,paperIds);
            answerMapper.delete(answerLambdaQueryWrapper);

            LambdaQueryWrapper<Score> scoreLambdaQueryWrapper = new LambdaQueryWrapper<>();
            scoreLambdaQueryWrapper.in(Score::getPaperId,paperIds);
            scoreMapper.delete(scoreLambdaQueryWrapper);
        }
        int delete = examMapper.deleteById(id);
        if (delete < 0) {
            response.setData(false);
            return response;
        } else {
            response.setData(true);
            return response;
        }
    }

    @Override
    public Response<Boolean> insertExamModelDto(ExamModelDto examModelDto) {
        LambdaQueryWrapper<Model> modelLambdaQueryWrapper = new LambdaQueryWrapper<>();
        modelLambdaQueryWrapper.eq(Model::getPaperClassId, examModelDto.getPaperClassId());
        Model model = modelMapper.selectOne(modelLambdaQueryWrapper);
        SnowflakeIdGenerator snowflakeIdGenerator = new SnowflakeIdGenerator(1);
        String examSet = snowflakeIdGenerator.nextIdAsString();
        Response<Boolean> response = new Response<>();
        for (int i = 0; i < Integer.parseInt(model.getModelNumber()); i++) {
            for (int j = 0; j < examModelDto.data[i].length; j++) {
                Setting setting = new Setting();
                setting.setExamSet(examSet);
                setting.setTeacherId(examModelDto.data[i][j]);
                setting.setAnswerId(String.valueOf(i + 1));
                setting.setNum(examModelDto.data[i].length);
                switch (i + 1) {
                    case 1:
                        setting.setCount(50);
                        break;
                    case 2:
                        setting.setCount(30);
                        break;
                    case 3:
                        setting.setCount(10);
                        break;
                    case 4:
                        setting.setCount(10);
                        break;
                    case 5:
                        setting.setCount(10);
                        break;
                    case 6:
                        setting.setCount(10);
                        break;
                    case 7:
                        setting.setCount(15);
                        break;
                    case 8:
                        setting.setCount(15);
                        break;
                }

                int insert = settingMapper.insert(setting);
                if (insert <= 0) {
                    response.setData(false);
                    return response;
                }
            }
        }
        Exam exam = examMapper.selectById(examModelDto.getId());
        exam.setExamSet(examSet);
        exam.setPaperClassId(examModelDto.getPaperClassId());
        int update = examMapper.updateById(exam);
        if (update <= 0) {
            response.setData(false);
            return response;
        }
        response.setData(true);
        return response;
    }
}