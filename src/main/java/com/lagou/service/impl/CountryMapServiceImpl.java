package com.lagou.service.impl;

import com.lagou.common.CityProvinceConverter;
import com.lagou.common.ServerResponse;
import com.lagou.mapper.CountryMapMapper;
import com.lagou.service.ICountryMapService;
import com.lagou.vo.CityVo;
import com.lagou.vo.ProvinceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryMapServiceImpl  implements ICountryMapService {

    @Autowired
    private CountryMapMapper mapper;

    @Override
    public ServerResponse<List<ProvinceVo>> getMapData() {
        //1.查询各个省份有哪些城市  List<ProvinceVo>   没有城市招聘数量0
        List<ProvinceVo>  provinceVoList = mapper.queryProvinceCity();

        //2.查询每个城市招聘数量 List<CityVo>  有城市招聘数量
        List<CityVo>  cityList = mapper.queryCityNum();

        //3.转换类,传递 List<ProvinceVo>    List<CityVo>  --->  List<ProvinceVo> 结果中
        List<ProvinceVo> provinceVos = CityProvinceConverter.converter(cityList,provinceVoList);

        //4.返回ServerResponse ,把List<ProvinceVo>放到
        return ServerResponse.createBySuccessData(provinceVos);
    }
}
