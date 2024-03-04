package com.guet.bishe.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
     * @param majorId 主键
     * @return 实例对象
     */
    public Major queryByMajorId(String majorId){
        LambdaQueryWrapper<Major> majorLambdaQueryWrapper = new LambdaQueryWrapper<>();
        majorLambdaQueryWrapper.eq(Major::getMajorId,majorId);
        return majorMapper.selectOne(majorLambdaQueryWrapper);
    }
    
    /**
     * 分页查询
     *
     * @param majorName 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    public Page<Major> paginQuery(String majorName, long current, long size){
        //1. 构建动态查询条件
        LambdaQueryWrapper<Major> majorLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(StrUtil.isNotBlank(majorName)){
            majorLambdaQueryWrapper.eq(Major::getMajorName,majorName);
        }
        //2. 执行分页查询
        Page<Major> pagin = new Page<>(current , size , true);
        IPage<Major> selectResult = majorMapper.selectPage(pagin , majorLambdaQueryWrapper);
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
    public boolean insert(Major major){
        int insert = majorMapper.insert(major);
        return insert>0;
    }
    
    /** 
     * 更新数据
     *
     * @param major 实例对象
     * @return 实例对象
     */
    public boolean update(Major major){
        //1. 根据条件动态更新
        LambdaQueryWrapper<Major> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Major::getMajorId,major.getMajorId());
        //2. 设置主键，并更新
        int update = majorMapper.update(major, wrapper);
        return update>0;
    }
    
    /** 
     * 通过主键删除数据
     *
     * @param majorId 主键
     * @return 是否成功
     */
    public boolean deleteByMajorId(String majorId){
        LambdaQueryWrapper<Major> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Major::getMajorId,majorId);
        int total = majorMapper.delete(wrapper);
        return total > 0;
    }
}