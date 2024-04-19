package com.guet.bishe.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.bishe.Utils.SnowflakeIdGenerator;
import com.guet.bishe.entity.*;
import com.guet.bishe.mapper.ExamMapper;
import com.guet.bishe.mapper.ModelMapper;
import com.guet.bishe.mapper.SetingMapper;
import com.guet.bishe.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
    private SetingMapper setingMapper;
    @Autowired
    private ExamMapper examMapper;
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
        modelLambdaQueryWrapper.eq(Model::getModelClass,id);
        List<Model> models = modelMapper.selectList(modelLambdaQueryWrapper);
        Response<List<Model>> listResponse = new Response<>();
        if(models.size() == 0){
            listResponse.setData(null);
            listResponse.setMessage("不存在");
        }else {
            listResponse.setData(models);
            listResponse.setMessage("查询成功");
        }
        return listResponse;
    }

    @Override
    public Response<Boolean> insertTestModelDto(TestModelDto testModelDto) {
        LambdaQueryWrapper<Model> modelLambdaQueryWrapper = new LambdaQueryWrapper<>();
        modelLambdaQueryWrapper.eq(Model::getPaperClassId,testModelDto.getPaperClassId());
        Model model = modelMapper.selectOne(modelLambdaQueryWrapper);
        SnowflakeIdGenerator snowflakeIdGenerator = new SnowflakeIdGenerator(1);
        String examSet = snowflakeIdGenerator.nextIdAsString();
        Response<Boolean> response = new Response<>();
        for (int i=0;i<Integer.parseInt(model.getModelNumber());i++){
            Seting seting = new Seting();
            seting.setExamSet(examSet);
            seting.setTeacherId(testModelDto.getTeacherId());
            seting.setAnswerId(String.valueOf(i+1));
            seting.setNum(1);
            seting.setCount(10);
            int insert = setingMapper.insert(seting);
            if(insert<=0){
                response.setData(false);
                return response;
            }
        }
        Exam exam = examMapper.selectById(testModelDto.getId());
        exam.setExamSet(examSet);
        exam.setPaperClassId(testModelDto.getPaperClassId());
        LambdaQueryWrapper<Exam> examLambdaQueryWrapper = new LambdaQueryWrapper<>();
        examLambdaQueryWrapper.eq(Exam::getId,testModelDto.getId());
        int update = examMapper.update(exam, examLambdaQueryWrapper);
        if(update<=0){
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
        if(exam.getExamSet().length() != 0){
            LambdaQueryWrapper<Seting> setingLambdaQueryWrapper = new LambdaQueryWrapper<>();
            setingLambdaQueryWrapper.eq(Seting::getExamSet,exam.getExamSet());
            int delete = setingMapper.delete(setingLambdaQueryWrapper);
            if(delete<=0){
                response.setData(false);
                return response;
            }
        }
        int delete = examMapper.deleteById(id);
        if(delete < 0){
            response.setData(false);
            return response;
        }else {
            response.setData(true);
            return response;
        }
    }
}