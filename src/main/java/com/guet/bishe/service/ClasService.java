package com.guet.bishe.service;

import com.guet.bishe.entity.Clas;


/**
 * 班级;(clas)表服务接口
 * @author : cardo
 * @date : 2024-3-4
 */
public interface ClasService{

    /**
     * 通过ID查询单条数据
     *
     * @param classId 主键
     * @return 实例对象
     */
    Clas queryByClassId(String classId);

    /**
     * 新增数据
     *
     * @param clas 实例对象
     * @return 实例对象
     */
    boolean insert(Clas clas);
    /**
     * 更新数据
     *
     * @param clas 实例对象
     * @return 实例对象
     */
    boolean update(Clas clas);
    /**
     * 通过主键删除数据
     *
     * @param classId 主键
     * @return 是否成功
     */
    boolean deleteByClassId(String classId);
}