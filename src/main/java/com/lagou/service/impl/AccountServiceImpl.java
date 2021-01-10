package com.lagou.service.impl;

import com.lagou.mapper.AccountMapper;
import com.lagou.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional  //事务控制
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountMapper mapper;

    @Override
    public int transfer(String inName, String outName, double money) {
        try{
            //调用转入
            mapper.transferIn(inName,money);
            //调用转出
            mapper.transferOut(outName,money);
            return 0;
        }catch (Exception e){
            System.out.println(e);
            return 1;
        }
    }
}
