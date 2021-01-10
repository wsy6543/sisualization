package com.lagou.service;

import com.lagou.common.ServerResponse;

import java.util.Map;

public interface IEduService {
    /**
     * 统计不同学历下的岗位数量
     * @return
     */
    ServerResponse<Map<String, Object>> getEduData();
}
