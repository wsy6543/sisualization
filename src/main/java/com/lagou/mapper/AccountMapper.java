package com.lagou.mapper;

import org.apache.ibatis.annotations.Param;

public interface AccountMapper {

    //转入钱
    public void transferIn(@Param("name") String name, @Param("money")double money);

    //转出钱
    public void transferOut(@Param("name") String name, @Param("money")double money);
}
