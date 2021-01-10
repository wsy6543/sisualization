package com.lagou.mapper;

import com.lagou.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    /**
     * 根据用户名查找用户是否存在
     * @param username
     * @return
     */
    int checkUserName(String username);

    /**
     * 用户登录
     * @param username
     * @param md5Password
     * @return
     */
    User selectLogin(@Param("username") String username, @Param("password") String md5Password);

    List<User> userList();

    int checkEmail(String str);

    int insert(User user);

    int deleteByPrimaryKey(int id);

    User selectByPrimaryKey(int userId);
}
