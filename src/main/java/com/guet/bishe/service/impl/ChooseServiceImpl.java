package com.guet.bishe.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.bishe.entity.Choose;
import com.guet.bishe.entity.Response;
import com.guet.bishe.entity.Student;
import com.guet.bishe.mapper.ChooseMapper;
import com.guet.bishe.mapper.StudentMapper;
import com.guet.bishe.service.ChooseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 选课;(choose)表服务实现类
 * @author : cardo
 * @date : 2024-4-10
 */
@Service
public class ChooseServiceImpl extends ServiceImpl<ChooseMapper, Choose> implements ChooseService {
    @Autowired
    private ChooseMapper chooseMapper;
    @Autowired
    private StudentMapper studentMapper;
    /**
     * 通过ID查询单条数据
     *
     * @param studentId 主键
     * @return 实例对象
     */
    public List<Student> queryById(String  studentId){
        LambdaQueryWrapper<Student> studentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        studentLambdaQueryWrapper.like(Student::getStudentId,studentId).eq(Student::getStatus,"1");
        return studentMapper.selectList(studentLambdaQueryWrapper);
    }

    
    /** 
     * 新增数据
     *
     * @param choose 实例对象
     * @return 实例对象
     */
    public boolean insert(Choose choose){
        LambdaQueryWrapper<Choose> wrapper = new LambdaQueryWrapper<Choose>();
        wrapper.eq(Choose::getStudentId,choose.studentId);
        wrapper.eq(Choose::getLessonId,choose.lessonId);
        Choose choose1 = chooseMapper.selectOne(wrapper);
        if(choose1==null){
            return chooseMapper.insert(choose)>0;
        }else {
            return false;
        }
    }
    
    /** 
     * 更新数据
     *
     * @param choose 实例对象
     * @return 实例对象
     */
    public boolean update(Choose choose){
        //1. 根据条件动态更新
        //2. 设置主键，并更新
        return chooseMapper.updateById(choose)>0;
    }
    
    /** 
     * 通过主键删除数据
     *
     * @param studentId 主键
     * @return 是否成功
     */
    public boolean deleteById(String studentId){
        LambdaQueryWrapper<Choose> chooseLambdaQueryWrapper = new LambdaQueryWrapper<>();
        chooseLambdaQueryWrapper.eq(Choose::getStudentId,studentId);
        int delete = chooseMapper.delete(chooseLambdaQueryWrapper);
        return delete>0;
    }


    public Response<List<Student>> queryAll(String lessonId) {
        LambdaQueryWrapper<Choose> lessonLambdaQueryWrapper = new LambdaQueryWrapper<>();
        lessonLambdaQueryWrapper.eq(Choose::getLessonId,lessonId);
        List<Choose> chooses = chooseMapper.selectList(lessonLambdaQueryWrapper);
        if(chooses.size()==0){
            Response<List<Student>> listResponse = new Response<>();
            listResponse.setCode(201);
            listResponse.setMessage("查询成功");
            return listResponse;
        }else {
            //拿到chooses中的所有studentid
            List<String> studentIds = chooses.stream().map(Choose::getStudentId).toList();

            LambdaQueryWrapper<Student> studentLambdaQueryWrapper = new LambdaQueryWrapper<>();
            studentLambdaQueryWrapper.in(Student::getStudentId,studentIds);
            List<Student> students = studentMapper.selectList(studentLambdaQueryWrapper);

            Response<List<Student>> listResponse = new Response<>();
            listResponse.setMessage("查询成功");
            listResponse.setData(students);
            return listResponse;
        }
    }
}