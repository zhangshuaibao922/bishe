package com.guet.bishe.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.bishe.entity.Grade;
import com.guet.bishe.mapper.GradeMapper;
import com.guet.bishe.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 年级;(grade)表服务实现类
 * @author : cardo
 * @date : 2024-3-4
 */
@Service
public class GradeServiceImpl extends ServiceImpl<GradeMapper, Grade> implements GradeService {
    @Autowired
    private GradeMapper gradeMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param gradeId 主键
     * @return 实例对象
     */
    public Grade queryByGradeId(String gradeId) {
        LambdaQueryWrapper<Grade> gradeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        gradeLambdaQueryWrapper.eq(Grade::getGradeId,gradeId);
        return gradeMapper.selectOne(gradeLambdaQueryWrapper);
    }

    /**
     * 分页查询
     *
     * @param gradeYear 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    public Page<Grade> paginQuery(String gradeYear, long current, long size) {
        //1. 构建动态查询条件
        LambdaQueryWrapper<Grade> gradeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(StrUtil.isNotBlank(gradeYear)){
            gradeLambdaQueryWrapper.eq(Grade::getGradeYear,gradeYear);
        }
        //2. 执行分页查询
        Page<Grade> pagin = new Page<>(current , size , true);
        IPage<Grade> selectResult = gradeMapper.selectPage(pagin , gradeLambdaQueryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return pagin;
    }

    /**
     * 新增数据
     *
     * @param grade 实例对象
     * @return 实例对象
     */
    public boolean insert(Grade grade){
        int insert = gradeMapper.insert(grade);
        return insert>0;
    }

    /**
     * 更新数据
     *
     * @param grade 实例对象
     * @return 实例对象
     */
    public boolean update(Grade grade){
        //1. 根据条件动态更新
        LambdaQueryWrapper<Grade> gradeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        gradeLambdaQueryWrapper.eq(Grade::getGradeId,grade.getGradeId());
        //2. 设置主键，并更新
        int update = gradeMapper.update(grade, gradeLambdaQueryWrapper);
        return update>0;
    }

    /**
     * 通过主键删除数据
     *
     * @param gradeId 主键
     * @return 是否成功
     */
    public boolean deleteByGradeId(String gradeId) {
        LambdaQueryWrapper<Grade> gradeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        gradeLambdaQueryWrapper.eq(Grade::getGradeId,gradeId);
        int total = gradeMapper.delete(gradeLambdaQueryWrapper);
        return total > 0;
    }
}