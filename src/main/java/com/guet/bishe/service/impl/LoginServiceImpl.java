package com.guet.bishe.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.bishe.entity.*;
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
    public Response<User> login(LoginDto loginDto) {
        Response<User> userResponse = new Response<>();
        if(StrUtil.isBlank(loginDto.identity)||StrUtil.isBlank(loginDto.username)||StrUtil.isBlank(loginDto.password)){
            userResponse.setMessage("身份/账号/密码为空");
            userResponse.setData(null);
            return userResponse;
        }
        if("student".equals(loginDto.identity)){
            LambdaQueryWrapper<Student> studentLambdaQueryWrapper = new LambdaQueryWrapper<>();
            Student student = studentMapper.selectOne(studentLambdaQueryWrapper.eq(Student::getStudentId, loginDto.username)
                    .eq(Student::getStudentPassword,loginDto.password)
                    .eq(Student::getStatus,"1"));
            if(student!=null){
                User user = new User();
                user.setName(student.getStudentName());
                user.setUsername(student.getStudentId());
                user.setOldPassword(student.getStudentPassword());
                user.setAuthorityRole(getAuth(student.getAuthorityId()));
                user.setDescription(student.getDescription());
                user.setIdentity(loginDto.identity);
                userResponse.setMessage("学生登录成功");
                userResponse.setData(user);
                return userResponse;
            }else {
                userResponse.setMessage("用户不存在");
                userResponse.setData(null);
                return userResponse;
            }

        } else if ("teacher".equals(loginDto.identity)) {
            LambdaQueryWrapper<Teacher> teacherLambdaQueryWrapper = new LambdaQueryWrapper<>();
            Teacher teacher = teacherMapper.selectOne(teacherLambdaQueryWrapper.eq(Teacher::getTeacherId, loginDto.username)
                    .eq(Teacher::getTeacherPassword, loginDto.password)
                    .eq(Teacher::getStatus,"1"));
            if(teacher!=null){
                User user = new User();
                user.setName(teacher.getTeacherName());
                user.setUsername(teacher.getTeacherId());
                user.setOldPassword(teacher.getTeacherPassword());
                user.setAuthorityRole(getAuth(teacher.getAuthorityId()));
                user.setDescription(teacher.getDescription());
                user.setIdentity(loginDto.identity);
                userResponse.setMessage("教师登录成功");
                userResponse.setData(user);
                return userResponse;
            }else {
                userResponse.setMessage("用户不存在");
                userResponse.setData(null);
                return userResponse;
            }
        }
        userResponse.setMessage("身份不存在");
        userResponse.setData(null);
        return userResponse;
    }

    /**
     * 修改密码
     *
     * @param user 实例对象
     * @return 实例对象
     */
    public String updateByUser(User user) {
        if("student".equals(user.identity)){
            LambdaQueryWrapper<Student> studentLambdaQueryWrapper = new LambdaQueryWrapper<>();
            Student student = studentMapper.selectOne(studentLambdaQueryWrapper.eq(Student::getStudentId, user.getUsername()).eq(Student::getStudentPassword,user.getOldPassword()));
            if(student!=null){
                student.setStudentPassword(user.getNewPassword());
                studentMapper.update(student,studentLambdaQueryWrapper.eq(Student::getStudentId,user.getUsername()));
                return "success";
            }else {
                return "fail";
            }
        } else if ("teacher".equals(user.identity)) {
            LambdaQueryWrapper<Teacher> teacherLambdaQueryWrapper = new LambdaQueryWrapper<>();
            Teacher teacher = teacherMapper.selectOne(teacherLambdaQueryWrapper.eq(Teacher::getTeacherId, user.getUsername()).eq(Teacher::getTeacherPassword, user.getOldPassword()));
            if(teacher!=null){
                teacher.setTeacherPassword(user.getNewPassword());
                teacherMapper.update(teacher,teacherLambdaQueryWrapper.eq(Teacher::getTeacherId,user.getUsername()));
                return "success";
            }else {
                return "fail";
            }
        }else {
            return "fail";
        }
    }

    private String getAuth(String authorityId) {
            LambdaQueryWrapper<Authority> authorityLambdaQueryWrapper = new LambdaQueryWrapper<>();
            Authority authority = authorityMapper.selectOne(authorityLambdaQueryWrapper.eq(Authority::getAuthorityId, authorityId));
            return authority.getAuthorityRole();
    }
}
