package com.guet.bishe.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.bishe.entity.Exam;
import com.guet.bishe.mapper.ExamMapper;
import com.guet.bishe.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
 /**
 * 考试;(exam)表服务实现类
 * @author : cardo
 * @date : 2024-4-17
 */
@Service
public class ExamServiceImpl extends ServiceImpl<ExamMapper, Exam> implements ExamService {
    @Autowired
    private ExamMapper examMapper;
    
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
        return examMapper.insert(exam)>0;
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
}