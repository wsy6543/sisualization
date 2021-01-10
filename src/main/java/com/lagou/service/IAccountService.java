package com.lagou.service;

public interface IAccountService {

    //转账
    public int transfer(String inName, String outName , double money);
}
