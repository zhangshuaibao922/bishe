package com.guet.bishe.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.bishe.entity.Paper;
import com.guet.bishe.mapper.PaperMapper;
import com.guet.bishe.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

/**
 * 答题卡;(paper)表服务实现类
 * @author : cardo
 * @date : 2024-4-17
 */
@Service
public class PaperServiceImpl extends ServiceImpl<PaperMapper, Paper> implements PaperService {
    @Autowired
    private PaperMapper paperMapper;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    public Paper queryById(Integer id){
//        LambdaQueryWrapper<Paper> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        lambdaQueryWrapper.eq();
        return paperMapper.selectById(id);
    }

    
    /** 
     * 新增数据
     *
     * @param paper 实例对象
     * @return 实例对象
     */
    public boolean insert(Paper paper){
        return paperMapper.insert(paper)>0;
    }
    
    /** 
     * 更新数据
     *
     * @param paper 实例对象
     * @return 实例对象
     */
    public boolean update(Paper paper){
        //1. 根据条件动态更新
        LambdaQueryWrapper<Paper> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Paper::getId,paper.getId());
        //2. 设置主键，并更新
        return paperMapper.update(paper, lambdaQueryWrapper)>0;
    }
    
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(Integer id){
//        LambdaQueryWrapper<Paper> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        lambdaQueryWrapper.eq();
        return paperMapper.deleteById(id)> 0;
    }
}