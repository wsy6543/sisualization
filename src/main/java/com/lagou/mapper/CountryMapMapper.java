package com.lagou.mapper;

import com.lagou.vo.CityVo;
import com.lagou.vo.ProvinceVo;

import java.util.List;

public interface CountryMapMapper {
    /**
     * 通过省份的城市
     * @return
     */
    List<ProvinceVo> queryProvinceCity();

    /**
     * 通过各个城市的招聘数量
     * @return
     */
    List<CityVo> queryCityNum();
}
