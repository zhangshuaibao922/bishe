package com.guet.bishe.service.impl;

import cn.hutool.core.util.StrUtil;
import com.guet.bishe.entity.College;
import com.guet.bishe.mapper.CollegeMapper;
import com.guet.bishe.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;

 /**
 * 学院;(college)表服务实现类
 * @author : cardo
 * @date : 2024-2-27
 */
@Service
public class CollegeServiceImpl implements CollegeService {
    @Autowired
    private CollegeMapper collegeMapper;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param collegeId 主键
     * @return 实例对象
     */
    public College queryByCollegeId(String collegeId){
        LambdaQueryWrapper<College> collegeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        return collegeMapper.selectOne(collegeLambdaQueryWrapper.eq(College::getCollegeId,collegeId));
    }

    /**
     * 分页查询
     *
     * @param college 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    public Page<College> paginQuery(College college, long current, long size){
        //1. 构建动态查询条件
        LambdaUpdateChainWrapper<College> queryWrapper = queryConditions(college);
        //2. 执行分页查询
        Page<College> paging = new Page<>(current , size , true);
        IPage<College> selectResult = collegeMapper.selectByPage(paging , queryWrapper);
        paging.setPages(selectResult.getPages());
        paging.setTotal(selectResult.getTotal());
        paging.setRecords(selectResult.getRecords());
        //3. 返回结果
        return paging;
    }
    
    /** 
     * 新增数据
     *
     * @param college 实例对象
     * @return 实例对象
     */
    public College insert(College college){
        collegeMapper.insert(college);
        return college;
    }
    
    /** 
     * 更新数据
     *
     * @param college 实例对象
     * @return 实例对象
     */
    public College update(College college){
        //1. 根据条件动态更新

        LambdaUpdateChainWrapper<College> chainWrapper = queryConditions(college);
        //2. 设置主键，并更新
        chainWrapper.set(College::getId, college.getId());
        boolean ret = chainWrapper.update();
        //3. 更新成功了，查询最最对象返回
        if(ret){
            return queryByCollegeId(college.getCollegeId());
        }else{
            return college;
        }
    }
    
    /**
     * 通过主键删除数据
     *
     * @param collegeId 主键
     * @return 是否成功
     */
    public boolean deleteByCollegeId(String collegeId){
        LambdaQueryWrapper<College> collegeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        int delete = collegeMapper.delete(collegeLambdaQueryWrapper.eq(College::getCollegeId, collegeId));
        return delete > 0;
    }

    public LambdaUpdateChainWrapper<College>  queryConditions(College college){
        LambdaUpdateChainWrapper<College> chainWrapper = new LambdaUpdateChainWrapper<College>(collegeMapper);
        if(StrUtil.isNotBlank(college.getCollegeId())){
            chainWrapper.eq(College::getCollegeId, college.getCollegeId());
        }
        if(StrUtil.isNotBlank(college.getCollegeName())){
            chainWrapper.eq(College::getCollegeName, college.getCollegeName());
        }
        if(StrUtil.isNotBlank(college.getCreateTime())){
            chainWrapper.eq(College::getCreateTime, college.getCreateTime());
        }
        if(StrUtil.isNotBlank(college.getUpdateTime())){
            chainWrapper.eq(College::getUpdateTime, college.getUpdateTime());
        }
        return chainWrapper;
    }
}