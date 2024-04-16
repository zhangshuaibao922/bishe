package com.guet.bishe.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.guet.bishe.entity.Instruct;


 /**
 * 授课;(instruct)表服务接口
 * @author : cardo
 * @date : 2024-4-16
 */
public interface InstructService extends IService<Instruct> {
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    Instruct queryById(Integer id);

    /** 
     * 新增数据
     *
     * @param instruct 实例对象
     * @return 实例对象
     */
    boolean insert(Instruct instruct);
    /** 
     * 更新数据
     *
     * @param instruct 实例对象
     * @return 实例对象
     */
    boolean update(Instruct instruct);
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);
}