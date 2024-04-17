package com.guet.bishe.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guet.bishe.entity.Answer;
import org.apache.ibatis.annotations.Mapper;


 /**
 * 试卷;(answer)表数据库访问层
 * @author : cardo
 * @date : 2024-4-17
 */
@Mapper
public interface AnswerMapper  extends BaseMapper<Answer>{
}