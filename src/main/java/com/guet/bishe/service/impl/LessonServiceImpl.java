package com.guet.bishe.service.impl;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.bishe.entity.*;
import com.guet.bishe.mapper.InstructMapper;
import com.guet.bishe.mapper.LessonMapper;
import com.guet.bishe.mapper.TeacherMapper;
import com.guet.bishe.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * 课程;(lesson)表服务实现类
 * @author : cardo
 * @date : 2024-4-8
 */
@Service
public class LessonServiceImpl extends ServiceImpl<LessonMapper, Lesson> implements LessonService {
    @Autowired
    private LessonMapper lessonMapper;

    @Autowired
    private InstructMapper instructMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param LessonName 主键
     * @return 实例对象
     */
    public List<Lesson> queryById(String LessonName){
        LambdaQueryWrapper<Lesson> lessonLambdaQueryWrapper = new LambdaQueryWrapper<>();
        lessonLambdaQueryWrapper.like(Lesson::getLessonName,LessonName);
        return lessonMapper.selectList(lessonLambdaQueryWrapper);
    }

    
    /** 
     * 新增数据
     *
     * @param lesson 实例对象
     * @return 实例对象
     */
    public boolean insert(Lesson lesson){
        return lessonMapper.insert(lesson)>0;
    }
    
    /** 
     * 更新数据
     *
     * @param lesson 实例对象
     * @return 实例对象
     */
    public boolean update(Lesson lesson){
        //1. 根据条件动态更新

        //2. 设置主键，并更新
        return lessonMapper.updateById(lesson)>0;
    }
    
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(Integer id){
        return lessonMapper.deleteById(id)> 0;
    }

    @Override
    public Response<List<LessonDto>> queryAll() {
        List<Lesson> list = lessonMapper.selectList(null);
        Response<List<LessonDto>> listResponse = new Response<>();
        if (list.size() == 0){
            listResponse.setMessage("查询失败");
            listResponse.setCode(201);
            listResponse.setData(null);
            return listResponse;
        }else{
            ArrayList<LessonDto> lessonDtos = new ArrayList<>();
            //将list复制到lessonDtos
            list.forEach(lesson -> {
                LessonDto lessonDto = new LessonDto();
                lessonDto.setId(lesson.getId());
                lessonDto.setLessonId(lesson.getLessonId());
                lessonDto.setLessonName(lesson.getLessonName());
                lessonDto.setHours(lesson.getHours());
                lessonDto.setScore(lesson.getScore());
                lessonDtos.add(lessonDto);
           });
            for (LessonDto lessonDto : lessonDtos) {
                LambdaQueryWrapper<Instruct> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                lambdaQueryWrapper.eq(Instruct::getLessonId,lessonDto.getLessonId());
                Instruct instruct = instructMapper.selectOne(lambdaQueryWrapper);
                if (instruct != null){
                    lessonDto.setTeacherId(instruct.getTeacherId());
                    Teacher teacher = teacherMapper.selectOne(new LambdaQueryWrapper<Teacher>().eq(Teacher::getTeacherId, instruct.getTeacherId()));
                    if (teacher != null){
                        lessonDto.setTeacherName(teacher.getTeacherName());
                    }else {
                        lessonDto.setTeacherName("");
                    }
                }else {
                    lessonDto.setTeacherId("");
                    lessonDto.setTeacherName("");
                }
            }
            listResponse.setMessage("查询成功");
            listResponse.setData(lessonDtos);
            return listResponse;
        }

    }

    @Override
    public Response<List<LessonDto>> queryAllByTeacherId(String teacherId) {
        List<Instruct> instructs = instructMapper.selectList(new LambdaQueryWrapper<Instruct>().eq(Instruct::getTeacherId, teacherId));
        LambdaQueryWrapper<Lesson> lessonLambdaQueryWrapper = new LambdaQueryWrapper<>();
        lessonLambdaQueryWrapper.in(Lesson::getLessonId,instructs.stream().map(Instruct::getLessonId).toList());
        List<Lesson> list = lessonMapper.selectList(lessonLambdaQueryWrapper);
        Response<List<LessonDto>> listResponse = new Response<>();
        if (list.size() == 0){
            listResponse.setMessage("查询失败");
            listResponse.setCode(201);
            listResponse.setData(null);
            return listResponse;
        }else{
            ArrayList<LessonDto> lessonDtos = new ArrayList<>();
            //将list复制到lessonDtos
            list.forEach(lesson -> {
                LessonDto lessonDto = new LessonDto();
                lessonDto.setId(lesson.getId());
                lessonDto.setLessonId(lesson.getLessonId());
                lessonDto.setLessonName(lesson.getLessonName());
                lessonDto.setHours(lesson.getHours());
                lessonDto.setScore(lesson.getScore());
                lessonDtos.add(lessonDto);
            });
            for (LessonDto lessonDto : lessonDtos) {
                LambdaQueryWrapper<Instruct> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                lambdaQueryWrapper.eq(Instruct::getLessonId,lessonDto.getLessonId());
                Instruct instruct = instructMapper.selectOne(lambdaQueryWrapper);
                if (instruct != null){
                    lessonDto.setTeacherId(instruct.getTeacherId());
                    Teacher teacher = teacherMapper.selectOne(new LambdaQueryWrapper<Teacher>().eq(Teacher::getTeacherId, instruct.getTeacherId()));
                    if (teacher != null){
                        lessonDto.setTeacherName(teacher.getTeacherName());
                    }else {
                        lessonDto.setTeacherName("");
                    }
                }else {
                    lessonDto.setTeacherId("");
                    lessonDto.setTeacherName("");
                }
            }
            listResponse.setMessage("查询成功");
            listResponse.setData(lessonDtos);
            return listResponse;
        }
    }
}