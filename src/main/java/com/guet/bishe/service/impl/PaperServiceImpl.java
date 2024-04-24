package com.guet.bishe.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.bishe.Utils.SnowflakeIdGenerator;
import com.guet.bishe.entity.*;
import com.guet.bishe.mapper.ChooseMapper;
import com.guet.bishe.mapper.ModelMapper;
import com.guet.bishe.mapper.PaperMapper;
import com.guet.bishe.mapper.StudentMapper;
import com.guet.bishe.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 答题卡;(paper)表服务实现类
 *
 * @author : cardo
 * @date : 2024-4-17
 */
@Service
public class PaperServiceImpl extends ServiceImpl<PaperMapper, Paper> implements PaperService {
    @Autowired
    private PaperMapper paperMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ChooseMapper chooseMapper;
    @Autowired
    private ModelMapper modelMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public Paper queryById(Integer id) {
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
    public boolean insert(Paper paper) {
        return paperMapper.insert(paper) > 0;
    }

    /**
     * 更新数据
     *
     * @param paper 实例对象
     * @return 实例对象
     */
    public boolean update(Paper paper) {
        //1. 根据条件动态更新
        LambdaQueryWrapper<Paper> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Paper::getId, paper.getId());
        //2. 设置主键，并更新
        return paperMapper.update(paper, lambdaQueryWrapper) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(Integer id) {
//        LambdaQueryWrapper<Paper> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        lambdaQueryWrapper.eq();
        return paperMapper.deleteById(id) > 0;
    }

    @Override
    public void insertByExamId(String lessonId,String examId, String objectName) {
        Paper paper = new Paper();
        paper.setExamId(examId);
        String[] split = objectName.split("/");
        String[] split1 = split[2].split("\\.");
        String[] split2 = split1[0].split("-");
        paper.setStudentId(split2[0]);
        paper.setSequence(split2[1]);
        paper.setPaperUrl(objectName);
        SnowflakeIdGenerator snowflakeIdGenerator = new SnowflakeIdGenerator(1);
        String s = snowflakeIdGenerator.nextIdAsString();
        paper.setPaperId(s);
        paper.setTotalScore(0.000000);
        paper.setCut("0");
        Paper paper1 = paperMapper.selectOne(new LambdaQueryWrapper<Paper>().eq(Paper::getExamId, examId).eq(Paper::getStudentId, paper.getStudentId()).eq(Paper::getSequence, paper.getSequence()));
        if(paper1==null){
            LambdaQueryWrapper<Choose> chooseLambdaQueryWrapper = new LambdaQueryWrapper<>();
            chooseLambdaQueryWrapper.eq(Choose::getLessonId,lessonId);
            List<Choose> chooses = chooseMapper.selectList(chooseLambdaQueryWrapper);
            List<String> list = chooses.stream().map(Choose::getStudentId).toList();
            if(list.contains(paper.getStudentId())){
                paperMapper.insert(paper);
            }
        }else {
            paper.setPaperId(paper1.getPaperId());
            LambdaQueryWrapper<Choose> chooseLambdaQueryWrapper = new LambdaQueryWrapper<>();
            chooseLambdaQueryWrapper.eq(Choose::getLessonId,lessonId);
            List<Choose> chooses = chooseMapper.selectList(chooseLambdaQueryWrapper);
            List<String> list = chooses.stream().map(Choose::getStudentId).toList();
            if(list.contains(paper.getStudentId())){
                paper.setId(paper1.getId());
                paperMapper.updateById(paper);
            }
        }

    }

    @Override
    public Response<List<StudentDto>> queryByIdExam(String lessonId, String examId) {
        LambdaQueryWrapper<Choose> chooseLambdaQueryWrapper = new LambdaQueryWrapper<>();
        chooseLambdaQueryWrapper.eq(Choose::getLessonId, lessonId);
        List<Choose> chooses = chooseMapper.selectList(chooseLambdaQueryWrapper);
        List<String> studenIds = chooses.stream().map(choose -> choose.studentId).toList();
        Response<List<StudentDto>> listResponse = new Response<>();
        ArrayList<StudentDto> studentDtos = new ArrayList<>();
        if(studenIds.size()<=0){
            listResponse.setMessage("没有学生选择该课程");
            listResponse.setData(null);
        }else {
            LambdaQueryWrapper<Student> studentLambdaQueryWrapper = new LambdaQueryWrapper<>();
            studentLambdaQueryWrapper.in(Student::getStudentId, studenIds);
            List<Student> studentList = studentMapper.selectList(studentLambdaQueryWrapper);
            if (studentList.size()<=0){
                listResponse.setMessage("没有学生选择该课程");
                listResponse.setData(null);
            }else {
                for (Student data : studentList) {
                    LambdaQueryWrapper<Paper> paperLambdaQueryWrapper = new LambdaQueryWrapper<>();
                    paperLambdaQueryWrapper.eq(Paper::getStudentId, data.getStudentId());
                    paperLambdaQueryWrapper.eq(Paper::getExamId, examId);
                    paperLambdaQueryWrapper.orderByAsc(Paper::getSequence);
                    List<Paper> papers = paperMapper.selectList(paperLambdaQueryWrapper);
                    if(papers.size()==0){
                        StudentDto studentDto = new StudentDto();
                        studentDto.setStudentId(data.getStudentId());
                        studentDto.setStudentName(data.getStudentName());
                        studentDto.setPaperUrl("");
                        listResponse.setMessage("部分未上传");
                        studentDtos.add(studentDto);
                    } else {
                        for (Paper paper :papers) {
                            StudentDto studentDto = new StudentDto();
                            studentDto.setStudentId(data.getStudentId());
                            studentDto.setStudentName(data.getStudentName());
                            studentDto.setPaperUrl("http://zhangshuaibao.top/"+paper.getPaperUrl());
                            studentDtos.add(studentDto);
                        }
                    }
                }
                listResponse.setData(studentDtos);
            }
        }

        return listResponse;
    }
}