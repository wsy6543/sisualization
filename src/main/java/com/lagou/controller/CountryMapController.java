package com.lagou.controller;

import com.lagou.common.ServerResponse;
import com.lagou.service.ICountryMapService;
import com.lagou.vo.ProvinceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/map")
public class CountryMapController {

    @Autowired
    private ICountryMapService service;

    /**
     * 统计各省的招聘人数
     */
    @RequestMapping(value = "mapData.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<ProvinceVo>>  countProvinceData(){
        ServerResponse<List<ProvinceVo>> response = service.getMapData();
        return response;
    }
}
