package com.guet.bishe.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.bishe.entity.Clas;
import com.guet.bishe.mapper.ClasMapper;
import com.guet.bishe.service.ClasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

 /**
 * 班级;(clas)表服务实现类
 * @author : cardo
 * @date : 2024-3-4
 */
@Service
public class ClasServiceImpl extends ServiceImpl<ClasMapper, Clas> implements ClasService {
    @Autowired
    private ClasMapper clasMapper;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param classId 主键
     * @return 实例对象
     */
    public Clas queryByClassId(String classId){
        LambdaQueryWrapper<Clas> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Clas::getClassId,classId);
        return clasMapper.selectOne(lambdaQueryWrapper);
    }
    
    /** 
     * 新增数据
     *
     * @param clas 实例对象
     * @return 实例对象
     */
    public boolean insert(Clas clas){
        return clasMapper.insert(clas)>0;
    }
    
    /** 
     * 更新数据
     *
     * @param clas 实例对象
     * @return 实例对象
     */
    public boolean update(Clas clas){
        //1. 根据条件动态更新
        LambdaQueryWrapper<Clas> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Clas::getClassId,clas.getClassId());
        //2. 设置主键，并更新
        return clasMapper.update(clas,lambdaQueryWrapper)>0;
    }
    
    /** 
     * 通过主键删除数据
     *
     * @param classId 主键
     * @return 是否成功
     */
    public boolean deleteByClassId(String classId){
        LambdaQueryWrapper<Clas> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Clas::getClassId,classId);
        return clasMapper.delete(lambdaQueryWrapper)> 0;
    }
}