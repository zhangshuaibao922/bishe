package com.guet.bishe.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.bishe.Utils.EmailUtil;
import com.guet.bishe.Utils.SnowflakeIdGenerator;
import com.guet.bishe.entity.*;
import com.guet.bishe.mapper.*;
import com.guet.bishe.service.ExamService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

/**
 * 考试;(exam)表服务实现类
 *
 * @author : cardo
 * @date : 2024-4-17
 */
@Service
public class ExamServiceImpl extends ServiceImpl<ExamMapper, Exam> implements ExamService {
    @Autowired
    private ExamMapper examMapper;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private InstructMapper instructMapper;
    @Autowired
    private ModelurlMapper modelurlMapper;
    @Autowired
    private ChooseMapper chooseMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private EmailUtil emailUtil;
    @Autowired
    private LessonMapper lesserMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public Exam queryById(Integer id) {
        return examMapper.selectById(id);
    }


    /**
     * 新增数据
     *
     * @param exam 实例对象
     * @return 实例对象
     */
    public boolean insert(Exam exam) {
        SnowflakeIdGenerator snowflakeIdGenerator = new SnowflakeIdGenerator(1);
        String examId = snowflakeIdGenerator.nextIdAsString();
        exam.setExamId(examId);
        return examMapper.insert(exam) > 0;
    }

    /**
     * 更新数据
     *
     * @param exam 实例对象
     * @return 实例对象
     */
    public boolean update(Exam exam) {
        //1. 根据条件动态更新
        LambdaQueryWrapper<Exam> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Exam::getExamId, exam.getExamId());
        LambdaQueryWrapper<Choose> chooseLambdaQueryWrapper = new LambdaQueryWrapper<>();
        chooseLambdaQueryWrapper.eq(Choose::getLessonId,exam.getLessonId());
        List<Choose> chooses = chooseMapper.selectList(chooseLambdaQueryWrapper);
        List<String> list = chooses.stream().map(Choose::getStudentId).toList();
        LambdaQueryWrapper<Student> studentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        studentLambdaQueryWrapper.in(Student::getStudentId,list);
        List<Student> students = studentMapper.selectList(studentLambdaQueryWrapper);
        List<String> list1 = students.stream().map(Student::getStudentEmail).toList();
        String[] array = list1.toArray(new String[list1.size()]);
        LambdaQueryWrapper<Lesson> lessonLambdaQueryWrapper = new LambdaQueryWrapper<>();
        lessonLambdaQueryWrapper.eq(Lesson::getLessonId,exam.getLessonId());
        Lesson lesson = lesserMapper.selectOne(lessonLambdaQueryWrapper);
        emailUtil.sendSuccess(array,exam.getExamName(),lesson.getLessonName());
        //2. 设置主键，并更新
        return examMapper.update(exam, lambdaQueryWrapper) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(Integer id) {
//        LambdaQueryWrapper<Exam> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        return examMapper.deleteById(id) > 0;
    }

    @Override
    public Response<List<ExamDto>> queryByExamClass(String examClass,String teacherId,String id){
        LambdaQueryWrapper<Instruct> instructLambdaQueryWrapper = new LambdaQueryWrapper<>();
        instructLambdaQueryWrapper.eq(Instruct::getTeacherId,teacherId);
        List<Instruct> instructs = instructMapper.selectList(instructLambdaQueryWrapper);
        List<String> lessonIds;
        if(instructs.size()==0){
            List<Choose> chooses = chooseMapper.selectList(new LambdaQueryWrapper<Choose>()
                    .eq(Choose::getStudentId, teacherId));
            lessonIds=chooses.stream().map(Choose::getLessonId).distinct().toList();
        }else {
            lessonIds = instructs.stream().map(Instruct::getLessonId).distinct().toList();
        }
        LambdaQueryWrapper<Exam> examLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(id.equals("1")){
            examLambdaQueryWrapper
                    .eq(Exam::getExamClass, examClass)
                    .in(Exam::getLessonId,lessonIds)
                    .isNull(Exam::getIsDelete)
                    .orderByDesc(Exam::getExamData);
        }else if(id.equals("2")) {
            examLambdaQueryWrapper
                    .eq(Exam::getExamClass, examClass)
                    .in(Exam::getLessonId,lessonIds)
                    .eq(Exam::getIsDelete,1)
                    .orderByDesc(Exam::getExamData);
        }
        List<Exam> exams = examMapper.selectList(examLambdaQueryWrapper);
        Response<List<ExamDto>> listResponse = new Response<>();
        ArrayList<ExamDto> examDtos = new ArrayList<>();
        for (Exam exam : exams) {
            ExamDto examDto = new ExamDto();
            BeanUtils.copyProperties(exam, examDto);
            examDtos.add(examDto);
        }
        if (exams.size() == 0) {
            listResponse.setData(null);
            listResponse.setMessage("不存在");
        } else {
            LambdaQueryWrapper<Model> modelLambdaQueryWrapper = new LambdaQueryWrapper<>();
            modelLambdaQueryWrapper.eq(Model::getModelClass, examClass);
            List<Model> models = modelMapper.selectList(modelLambdaQueryWrapper);
            HashMap<String, String> map = new HashMap<String, String>();
            for (Model data : models) {
                map.put(data.getPaperClassId(), data.getModelName());
            }
            for (ExamDto examDto : examDtos) {
                String paperClassId = examDto.getPaperClassId();
                if (paperClassId.length() == 0) {
                    examDto.setModelName("");
                } else {
                    String s = map.get(paperClassId);
                    examDto.setModelName(s);
                }
                LambdaQueryWrapper<Modelurl> modelurlLambdaQueryWrapper = new LambdaQueryWrapper<>();
                modelurlLambdaQueryWrapper.eq(Modelurl::getPaperClassId,examDto.getPaperClassId());
                List<Modelurl> modelurls = modelurlMapper.selectList(modelurlLambdaQueryWrapper);
                if(modelurls.size()==0){
                    examDto.setModels(null);
                }else {
                    examDto.setModels(modelurls);
                }

            }
            listResponse.setData(examDtos);
            listResponse.setMessage("查询成功");
        }
        return listResponse;
    }

    @Override
    public Response<List<Exam>> queryByExamName(String examName) {
        LambdaQueryWrapper<Exam> examLambdaQueryWrapper = new LambdaQueryWrapper<>();
        examLambdaQueryWrapper.like(Exam::getExamName, examName);
        List<Exam> exams = examMapper.selectList(examLambdaQueryWrapper);
        Response<List<Exam>> listResponse = new Response<>();
        if (exams.size() == 0) {
            listResponse.setData(null);
            listResponse.setMessage("不存在");
            listResponse.setCode(201);
        } else {
            listResponse.setData(exams);
            listResponse.setMessage("查询成功");
        }
        return listResponse;
    }

    @Override
    public Response<List<ExamDto>> scoreQueryByExamClass(String examClass) {
        LambdaQueryWrapper<Exam> examLambdaQueryWrapper = new LambdaQueryWrapper<>();
        examLambdaQueryWrapper
                .eq(Exam::getExamClass, examClass)
                .orderByDesc(Exam::getExamData)
                .ne(Exam::getExamSet, "")
                .ne(Exam::getPaperClassId, "");
        List<Exam> exams = examMapper.selectList(examLambdaQueryWrapper);
        Response<List<ExamDto>> listResponse = new Response<>();
        ArrayList<ExamDto> examDtos = new ArrayList<>();
        for (Exam exam : exams) {
            ExamDto examDto = new ExamDto();
            BeanUtils.copyProperties(exam, examDto);
            examDtos.add(examDto);
        }
        if (exams.size() == 0) {
            listResponse.setData(null);
            listResponse.setMessage("不存在");
        } else {
            LambdaQueryWrapper<Model> modelLambdaQueryWrapper = new LambdaQueryWrapper<>();
            modelLambdaQueryWrapper.eq(Model::getModelClass, examClass);
            List<Model> models = modelMapper.selectList(modelLambdaQueryWrapper);
            HashMap<String, String> map = new HashMap<String, String>();
            for (Model data : models) {
                map.put(data.getPaperClassId(), data.getModelName());
            }
            for (ExamDto examDto : examDtos) {
                String paperClassId = examDto.getPaperClassId();
                if (paperClassId.length() == 0) {
                    examDto.setModelName("");
                } else {
                    String s = map.get(paperClassId);
                    examDto.setModelName(s);
                }
                LambdaQueryWrapper<Modelurl> modelurlLambdaQueryWrapper = new LambdaQueryWrapper<>();
                modelurlLambdaQueryWrapper.eq(Modelurl::getPaperClassId,examDto.getPaperClassId());
                List<Modelurl> modelurls = modelurlMapper.selectList(modelurlLambdaQueryWrapper);
                if(modelurls.size()==0){
                    examDto.setModels(null);
                }else {
                    examDto.setModels(modelurls);
                }
            }
            listResponse.setData(examDtos);
            listResponse.setMessage("查询成功");
        }
        return listResponse;
    }

    @Override
    public Response<String> getModelClass(String examId) {
        LambdaQueryWrapper<Exam> examLambdaQueryWrapper = new LambdaQueryWrapper<>();
        examLambdaQueryWrapper.eq(Exam::getExamId,examId);
        Exam exam = examMapper.selectOne(examLambdaQueryWrapper);
        Response<String> integerResponse = new Response<>();
        integerResponse.setData(exam.getPaperClassId());
        integerResponse.setMessage("查询成功");
        return integerResponse;
    }
}