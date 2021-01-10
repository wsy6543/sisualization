package com.lagou.mapper;

import com.lagou.vo.ConverterVo;

import java.util.List;

public interface EduMapper {
    /**
     * 统计不同学历下的岗位
     * @return
     */
    List<ConverterVo> getEdu();
}
