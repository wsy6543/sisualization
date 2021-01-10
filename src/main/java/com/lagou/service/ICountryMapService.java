package com.lagou.service;

import com.lagou.common.ServerResponse;
import com.lagou.vo.ProvinceVo;

import java.util.List;

public interface ICountryMapService {
    /**
     * 统计各个省份的招聘人数
     * @return
     */
    ServerResponse<List<ProvinceVo>> getMapData();
}
