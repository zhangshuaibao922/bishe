package com.guet.bishe.service.impl;

import cn.hutool.core.util.StrUtil;
import com.guet.bishe.entity.Major;
import com.guet.bishe.mapper.MajorMapper;
import com.guet.bishe.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
 /**
 * 专业;(major)表服务实现类
 * @author : cardo
 * @date : 2024-3-1
 */
@Service
public class MajorServiceImpl implements MajorService {
    @Autowired
    private MajorMapper majorMapper;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    public Major queryById(Integer id){
        return majorMapper.selectById(id);
    }
    
    /**
     * 分页查询
     *
     * @param major 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    public Page<Major> paginQuery(Major major, long current, long size){
        //1. 构建动态查询条件

        //2. 执行分页查询
        Page<Major> pagin = new Page<>(current , size , true);
        IPage<Major> selectResult = majorMapper.selectByPage(pagin , queryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return pagin;
    }
    
    /** 
     * 新增数据
     *
     * @param major 实例对象
     * @return 实例对象
     */
    public Major insert(Major major){
        majorMapper.insert(major);
        return major;
    }
    
    /** 
     * 更新数据
     *
     * @param major 实例对象
     * @return 实例对象
     */
    public Major update(Major major){
        //1. 根据条件动态更新
        LambdaUpdateChainWrapper<Major> chainWrapper = new LambdaUpdateChainWrapper<Major>(majorMapper);
        if(StrUtil.isNotBlank(major.getMajorId())){
            chainWrapper.eq(Major::getMajorId, major.getMajorId());
        }
        if(StrUtil.isNotBlank(major.getMajorName())){
            chainWrapper.eq(Major::getMajorName, major.getMajorName());
        }
        if(StrUtil.isNotBlank(major.getCreateTime())){
            chainWrapper.eq(Major::getCreateTime, major.getCreateTime());
        }
        if(StrUtil.isNotBlank(major.getUpdateTime())){
            chainWrapper.eq(Major::getUpdateTime, major.getUpdateTime());
        }
        //2. 设置主键，并更新
        chainWrapper.set(Major::getId, major.getId());
        boolean ret = chainWrapper.update();
        //3. 更新成功了，查询最最对象返回
        if(ret){
            return queryById(major.getId());
        }else{
            return major;
        }
    }
    
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(Integer id){
        int total = majorMapper.deleteById(id);
        return total > 0;
    }
}