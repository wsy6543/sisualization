package com.lagou.controller;

import com.lagou.common.ResponseCode;
import com.lagou.common.ServerResponse;
import com.lagou.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private IAccountService service;

    @RequestMapping("transfer.do")
    @ResponseBody
    public ServerResponse<String> accountTransfer(String inName, String outName,double money){
        int status = service.transfer(inName, outName, money);
        //如果执行转账成功
        if(ResponseCode.SUCCESS.getCode() == status){
            return ServerResponse.createBySuccessMsg("转账成功");
        }else{
            return  ServerResponse.createByErrorMsg("转账失败");
        }
    }
}
