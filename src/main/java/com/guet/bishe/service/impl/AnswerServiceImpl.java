package com.guet.bishe.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.bishe.entity.Answer;
import com.guet.bishe.mapper.AnswerMapper;
import com.guet.bishe.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

 /**
 * 试卷;(answer)表服务实现类
 * @author : cardo
 * @date : 2024-4-17
 */
@Service
public class AnswerServiceImpl extends ServiceImpl<AnswerMapper, Answer> implements AnswerService {
    @Autowired
    private AnswerMapper answerMapper;
    
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
}