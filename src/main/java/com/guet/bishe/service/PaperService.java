package com.guet.bishe.service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guet.bishe.entity.Paper;
import com.guet.bishe.entity.Response;
import com.guet.bishe.entity.StudentDto;

import java.util.List;


/**
 * 答题卡;(paper)表服务接口
 * @author : cardo
 * @date : 2024-4-17
 */
public interface PaperService extends IService<Paper>{
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    Paper queryById(Integer id);

    /**
     * 新增数据
     *
     * @param paper 实例对象
     * @return 实例对象
     */
    boolean insert(Paper paper);
    /** 
     * 更新数据
     *
     * @param paper 实例对象
     * @return 实例对象
     */
    boolean update(Paper paper);
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    void insertByExamId(String lessonId,String examId, String objectName);

    Response<List<StudentDto>> queryByIdExam(String lessonId, String examId);
}