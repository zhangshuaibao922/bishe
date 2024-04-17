package com.guet.bishe.service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guet.bishe.entity.Seting;


 /**
 * 考试设置;(seting)表服务接口
 * @author : cardo
 * @date : 2024-4-17
 */
public interface SetingService extends IService<Seting> {
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    Seting queryById(Integer id);

    /**
     * 新增数据
     *
     * @param seting 实例对象
     * @return 实例对象
     */
    boolean insert(Seting seting);
    /** 
     * 更新数据
     *
     * @param seting 实例对象
     * @return 实例对象
     */
    boolean update(Seting seting);
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);
}