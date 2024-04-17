package com.guet.bishe.service.impl;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.bishe.entity.Instruct;
import com.guet.bishe.entity.Teacher;
import com.guet.bishe.mapper.InstructMapper;
import com.guet.bishe.mapper.TeacherMapper;
import com.guet.bishe.service.InstructService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
 /**
 * 授课;(instruct)表服务实现类
 * @author : cardo
 * @date : 2024-4-16
 */
@Service
public class InstructServiceImpl extends ServiceImpl<InstructMapper, Instruct> implements InstructService {
    @Autowired
    private InstructMapper instructMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param lessonId 主键
     * @return 实例对象
     */
    public Teacher queryById(String lessonId){
        LambdaQueryWrapper<Instruct> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Instruct::getLessonId,lessonId);
        Instruct instruct = instructMapper.selectOne(lambdaQueryWrapper);
        if(instruct==null){
            return null;
        }else {
            LambdaQueryWrapper<Teacher> teacherLambdaQueryWrapper = new LambdaQueryWrapper<>();
            teacherLambdaQueryWrapper.eq(Teacher::getTeacherId,instruct.getTeacherId());
            return teacherMapper.selectOne(teacherLambdaQueryWrapper);
        }
    }

    
    /** 
     * 新增数据
     *
     * @param instruct 实例对象
     * @return 实例对象
     */
    public boolean insert(Instruct instruct){
        return instructMapper.insert(instruct)>0;
    }
    
    /** 
     * 更新数据
     *
     * @param instruct 实例对象
     * @return 实例对象
     */
    public boolean update(Instruct instruct){
        //1. 根据条件动态更新

        //2. 设置主键，并更新
        return instructMapper.updateById(instruct)>0;
    }
    
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(Integer id){
//        LambdaQueryWrapper<Instruct> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        return instructMapper.deleteById(id)> 0;
    }
}