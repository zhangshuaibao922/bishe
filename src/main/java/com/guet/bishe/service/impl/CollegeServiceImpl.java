package com.guet.bishe.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.bishe.entity.College;
import com.guet.bishe.entity.Response;
import com.guet.bishe.entity.Teacher;
import com.guet.bishe.mapper.CollegeMapper;
import com.guet.bishe.mapper.TeacherMapper;
import com.guet.bishe.service.CollegeService;
import com.guet.bishe.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.util.List;

/**
 * 学院;(college)表服务实现类
 *
 * @author : cardo
 * @date : 2024-2-27
 */
@Service
public class CollegeServiceImpl extends ServiceImpl<CollegeMapper, College> implements CollegeService {
    @Autowired
    private CollegeMapper collegeMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param collegeId 主键
     * @return 实例对象
     */
    public College queryByCollegeId(String collegeId) {
        LambdaQueryWrapper<College> collegeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        return collegeMapper.selectOne(collegeLambdaQueryWrapper.eq(College::getCollegeId, collegeId));
    }

    /**
     * 分页查询
     *
     * @param collegeId 筛选条件
     * @param current   当前页码
     * @param size      每页大小
     * @return
     */
    public Page<College> paginQuery(String collegeId, long current, long size) {
        //1. 构建动态查询条件
        LambdaQueryWrapper<College> teacherLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(collegeId)) {
            teacherLambdaQueryWrapper.eq(College::getCollegeId, collegeId);
        }
        //2. 执行分页查询
        Page<College> paging = new Page<>(current, size, true);
        IPage<College> selectResult = collegeMapper.selectPage(paging, teacherLambdaQueryWrapper);
        paging.setPages(selectResult.getPages());
        paging.setTotal(selectResult.getTotal());
        paging.setRecords(selectResult.getRecords());
        //3. 返回结果
        return paging;
    }

    /**
     * 新增数据
     *
     * @param college 实例对象
     * @return 实例对象
     */
    public boolean insert(College college) {
        int insert = collegeMapper.insert(college);
        return insert > 0;
    }

    /**
     * 更新数据
     *
     * @param college 实例对象
     * @return 实例对象
     */
    public boolean update(College college) {
        //1. 根据条件动态更新
        //2. 设置主键，并更新
        int update = collegeMapper.updateById(college);
        return update > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteByCollegeId(Integer id) {
        int delete = collegeMapper.deleteById(id);
        return delete > 0;
    }

    @Override
    public Response<List<College>> queryAll() {
        Response<List<College>> listResponse = new Response<>();
        List<College> colleges = collegeMapper.selectList(new LambdaQueryWrapper<College>());
        if (colleges.size() == 0) {
            listResponse.setMessage("出现故障");
        }else {
            listResponse.setData(colleges);
        }
        return listResponse;
    }

}