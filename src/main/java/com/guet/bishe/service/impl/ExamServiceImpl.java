package com.guet.bishe.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.bishe.Utils.SnowflakeIdGenerator;
import com.guet.bishe.entity.Exam;
import com.guet.bishe.entity.ExamDto;
import com.guet.bishe.entity.Model;
import com.guet.bishe.entity.Response;
import com.guet.bishe.mapper.ExamMapper;
import com.guet.bishe.mapper.ModelMapper;
import com.guet.bishe.service.ExamService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 考试;(exam)表服务实现类
 * @author : cardo
 * @date : 2024-4-17
 */
@Service
public class ExamServiceImpl extends ServiceImpl<ExamMapper, Exam> implements ExamService {
    @Autowired
    private ExamMapper examMapper;
    @Autowired
    private ModelMapper modelMapper;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    public Exam queryById(Integer id){
        return examMapper.selectById(id);
    }

    
    /** 
     * 新增数据
     *
     * @param exam 实例对象
     * @return 实例对象
     */
    public boolean insert(Exam exam){
        if("1".equals(exam.getExamClass())){
            SnowflakeIdGenerator snowflakeIdGenerator = new SnowflakeIdGenerator(1);
            String examId = snowflakeIdGenerator.nextIdAsString();
            exam.setExamId(examId);
            return examMapper.insert(exam)>0;
        }else {
            return false;
        }
    }
    
    /** 
     * 更新数据
     *
     * @param exam 实例对象
     * @return 实例对象
     */
    public boolean update(Exam exam){
        //1. 根据条件动态更新
        LambdaQueryWrapper<Exam> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Exam::getExamId,exam.getExamId());
        //2. 设置主键，并更新
        return examMapper.update(exam, lambdaQueryWrapper)>0;
    }
    
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(Integer id){
//        LambdaQueryWrapper<Exam> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        return examMapper.deleteById(id)> 0;
    }

    @Override
    public Response<List<ExamDto>> queryByExamClass(String examClass) {
        LambdaQueryWrapper<Exam> examLambdaQueryWrapper = new LambdaQueryWrapper<>();
        examLambdaQueryWrapper.eq(Exam::getExamClass,examClass);
        List<Exam> exams = examMapper.selectList(examLambdaQueryWrapper);
        Response<List<ExamDto>> listResponse = new Response<>();
        ArrayList<ExamDto> examDtos = new ArrayList<>();
        for (Exam exam : exams) {
            ExamDto examDto = new ExamDto();
            BeanUtils.copyProperties(exam, examDto);
            examDtos.add(examDto);
        }
        if(exams.size() == 0){
            listResponse.setData(null);
            listResponse.setMessage("不存在");
        }else {
            LambdaQueryWrapper<Model> modelLambdaQueryWrapper = new LambdaQueryWrapper<>();
            modelLambdaQueryWrapper.eq(Model::getModelClass,"1");
            List<Model> models = modelMapper.selectList(modelLambdaQueryWrapper);
            HashMap<String, String> map = new HashMap<String, String>();
            for (Model data : models) {
                map.put(data.getPaperClassId(),data.getModelName());
            }
            for (ExamDto examDto :examDtos) {
                String paperClassId = examDto.getPaperClassId();
                if(paperClassId.length() == 0){
                }else {
                    String s = map.get(paperClassId);
                    examDto.setPaperClassId(s);
                }
            }
            listResponse.setData(examDtos);
            listResponse.setMessage("查询成功");
        }
        return listResponse;
    }

    @Override
    public Response<List<Exam>> queryByExamName(String examName) {
        LambdaQueryWrapper<Exam> examLambdaQueryWrapper = new LambdaQueryWrapper<>();
        examLambdaQueryWrapper.like(Exam::getExamName,examName);
        List<Exam> exams = examMapper.selectList(examLambdaQueryWrapper);
        Response<List<Exam>> listResponse = new Response<>();
        if(exams.size() == 0){
            listResponse.setData(null);
            listResponse.setMessage("不存在");
            listResponse.setCode(201);
        }else {
            listResponse.setData(exams);
            listResponse.setMessage("查询成功");
        }
        return listResponse;
    }
}