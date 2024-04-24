package com.guet.bishe.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guet.bishe.entity.Response;
import com.guet.bishe.entity.Setting;
import com.guet.bishe.entity.SettingDesDto;
import com.guet.bishe.entity.SettingDto;

import java.util.List;


/**
 * 考试设置;(seting)表服务接口
 * @author : cardo
 * @date : 2024-4-17
 */
public interface SettingService extends IService<Setting> {

    /**
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    Setting queryById(Integer id);

    /**
     * 新增数据
     *
     * @param setting 实例对象
     * @return 实例对象
     */
    boolean insert(Setting setting);
    /**
     * 更新数据
     *
     * @param setting 实例对象
     * @return 实例对象
     */
    boolean update(Setting setting);
    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    Response<List<SettingDto>> queryByTeacherIdAndExamSet(String teacherId, String examId);
    Response<List<SettingDesDto>> queryDes(String examSet, String teacherId);

    Response<Boolean> editDes(List<SettingDesDto> settingDesDtos);

    Response<SettingDesDto> queryDesById(String examSet, String answerId);
}