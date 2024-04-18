package com.guet.bishe.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guet.bishe.entity.Model;
import com.guet.bishe.entity.Response;
import com.guet.bishe.entity.TestModelDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * 模版;(model)表服务接口
 * @author : cardo
 * @date : 2024-4-18
 */
public interface ModelService extends IService<Model> {
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    Model queryById(Integer id);

    /** 
     * 新增数据
     *
     * @param model 实例对象
     * @return 实例对象
     */
    boolean insert(Model model);
    /** 
     * 更新数据
     *
     * @param model 实例对象
     * @return 实例对象
     */
    boolean update(Model model);
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    Response<List<Model>> queryAllById(String id);

    Response<Boolean> insertTestModelDto(TestModelDto testModelDto);
}