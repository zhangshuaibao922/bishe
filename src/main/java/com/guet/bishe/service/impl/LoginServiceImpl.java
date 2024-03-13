package com.guet.bishe.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.bishe.entity.Authority;
import com.guet.bishe.entity.LoginDto;
import com.guet.bishe.entity.Student;
import com.guet.bishe.entity.Teacher;
import com.guet.bishe.mapper.AuthorityMapper;
import com.guet.bishe.mapper.LoginMapper;
import com.guet.bishe.mapper.StudentMapper;
import com.guet.bishe.mapper.TeacherMapper;
import com.guet.bishe.service.AuthorityService;
import com.guet.bishe.service.LoginService;
import com.guet.bishe.service.StudentService;
import com.guet.bishe.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl extends ServiceImpl<LoginMapper, LoginDto> implements LoginService {

    @Autowired
    AuthorityMapper authorityMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    TeacherMapper teacherMapper;

    /**
     * 登录
     * @param loginDto
     * @return
     */
    public String login(LoginDto loginDto) {
        if(StrUtil.isBlank(loginDto.identity)||StrUtil.isBlank(loginDto.username)||StrUtil.isBlank(loginDto.password)){
            return "no";
        }
        if("student".equals(loginDto.identity)){
            LambdaQueryWrapper<Student> studentLambdaQueryWrapper = new LambdaQueryWrapper<>();
            Student student = studentMapper.selectOne(studentLambdaQueryWrapper.eq(Student::getStudentId, loginDto.username).eq(Student::getStudentPassword,loginDto.password));
            if(student!=null){
                return getAuth(student.getAuthorityId());
            }else {
                return "no";
            }

        } else if ("teacher".equals(loginDto.identity)) {
            LambdaQueryWrapper<Teacher> teacherLambdaQueryWrapper = new LambdaQueryWrapper<>();
            Teacher teacher = teacherMapper.selectOne(teacherLambdaQueryWrapper.eq(Teacher::getTeacherId, loginDto.username).eq(Teacher::getTeacherPassword, loginDto.password));
            if(teacher!=null){
                return getAuth(teacher.getAuthorityId());
            }else {
                return "no";
            }
        }
        return "no";
    }

    private String getAuth(String authorityId) {
            LambdaQueryWrapper<Authority> authorityLambdaQueryWrapper = new LambdaQueryWrapper<>();
            Authority authority = authorityMapper.selectOne(authorityLambdaQueryWrapper.eq(Authority::getAuthorityId, authorityId));
            return authority.getAuthorityRole();
    }
}
