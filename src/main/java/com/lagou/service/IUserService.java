package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.common.ServerResponse;
import com.lagou.model.User;

public interface IUserService {
    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    public ServerResponse<User> login(String username, String password);

    ServerResponse<PageInfo> getUsers(int pageNum, int pageSize);

    ServerResponse<String> add(User user);

    ServerResponse<String> deleteByPrimary(int id);
}
