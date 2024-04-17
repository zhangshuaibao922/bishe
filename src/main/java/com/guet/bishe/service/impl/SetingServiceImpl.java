package com.guet.bishe.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.bishe.entity.Seting;
import com.guet.bishe.mapper.SetingMapper;
import com.guet.bishe.service.SetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
 /**
 * 考试设置;(seting)表服务实现类
 * @author : cardo
 * @date : 2024-4-17
 */
@Service
public class SetingServiceImpl extends ServiceImpl<SetingMapper, Seting> implements SetingService {
    @Autowired
    private SetingMapper setingMapper;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    public Seting queryById(Integer id){
//        LambdaQueryWrapper<Seting> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        lambdaQueryWrapper.eq();
        return setingMapper.selectById(id);
    }
    
    /** 
     * 新增数据
     *
     * @param seting 实例对象
     * @return 实例对象
     */
    public boolean insert(Seting seting){
        return setingMapper.insert(seting)>0;
    }
    
    /** 
     * 更新数据
     *
     * @param seting 实例对象
     * @return 实例对象
     */
    public boolean update(Seting seting){
        //1. 根据条件动态更新
        LambdaQueryWrapper<Seting> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Seting::getId,seting.getId());
        //2. 设置主键，并更新
        return setingMapper.update(seting, lambdaQueryWrapper)>0;
    }
    
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(Integer id){
//        LambdaQueryWrapper<Seting> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        lambdaQueryWrapper.eq();
        return setingMapper.deleteById(id)> 0;
    }
}