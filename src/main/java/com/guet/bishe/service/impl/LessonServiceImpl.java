package com.guet.bishe.service.impl;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.bishe.entity.Lesson;
import com.guet.bishe.entity.Response;
import com.guet.bishe.mapper.LessonMapper;
import com.guet.bishe.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;

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
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    public Lesson queryById(Integer id){
        return lessonMapper.selectById(id);
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
        LambdaQueryWrapper<Lesson> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Lesson::getId,lesson.getId());
        //2. 设置主键，并更新
        return lessonMapper.update(lesson, lambdaQueryWrapper)>0;
    }
    
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(Integer id){
        LambdaQueryWrapper<Lesson> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Lesson::getId,id);
        return lessonMapper.delete(lambdaQueryWrapper)> 0;
    }

    @Override
    public Response<List<Lesson>> queryAll() {
        List<Lesson> list = lessonMapper.selectList(null);
        Response<List<Lesson>> listResponse = new Response<>();
        listResponse.setMessage("查询成功");
        listResponse.setData(list);
        return listResponse;
    }
}