package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageInterceptor;
import com.lagou.common.Const;
import com.lagou.common.ResponseCode;
import com.lagou.common.ServerResponse;
import com.lagou.mapper.UserMapper;
import com.lagou.model.User;
import com.lagou.service.IUserService;
import com.lagou.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("iUserService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ServerResponse<User> login(String username, String password) {
        //1.检查用户名是否存在  (0 用户不存在 1 用户存在)
       int resultCount =  userMapper.checkUserName(username);
       if(resultCount == 0){
           return ServerResponse.createByErrorMsg("用户名不存在");
       }

        //2.使用MD5给密码进行加密
        String md5Password = MD5Util.MD5EncodeUtf8(password);

        //3.调用mapper的登录方法, 返回一个user对象  , user==null 密码错误
        User user = userMapper.selectLogin(username,md5Password);

        //4.如果user对象为null 这登录失败 : 密码错误
        if(user == null){
            return  ServerResponse.createByErrorMsg("密码错误");
        }

        //5.如果user不是null 登录成功,清空密码
        user.setPassword(StringUtils.EMPTY);
        //6.封装一个ServerResponse,返回成功信息

        return ServerResponse.createBySuccessMsgData("登录成功",user);
    }

    /**
     * 分页查询user
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ServerResponse<PageInfo> getUsers(int pageNum, int pageSize) {
        //1.给PageHelper分页助手设置 pageNum  pageSize
        PageHelper.startPage(pageNum,pageSize);
        //2.查询所有用户信息
        List<User> list =  userMapper.userList();
        //3.创建pgeInfo对象,把查询到的用户list放到pageInfo中
        PageInfo pageInfo = new PageInfo(list);
        //4.返回serverResponse,添加pgeInfo
        return ServerResponse.createBySuccessData(pageInfo);
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @Override
    public ServerResponse<String> add(User user) {
        //1.校验用户名是否存在
        ServerResponse<String> validResponse = this.checkValid(user.getUsername(), Const.USERNAME);
        if(validResponse.getStatus() != ResponseCode.SUCCESS.getCode()){
            return validResponse;
        }
        //2.校验邮箱是否已经存在
        validResponse = this.checkValid(user.getEmail(), Const.EMAIL);
        if(validResponse.getStatus() != ResponseCode.SUCCESS.getCode()){
            return validResponse;
        }

        //3.使用md5对密码进行加密
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));

        //4.添加数据入库   resultCount==1 插入成功, 如果为0,添加失败
        int resultCount = userMapper.insert(user);
        if(resultCount ==0 ){
            return ServerResponse.createByErrorMsg("添加失败");
        }

        return ServerResponse.createBySuccessMsg("添加成功");
    }


    /**
     * 校验用户名和邮箱是否存在
     * @param str   有可能是用户名,也有可能是邮箱
     * @param type   类型可以是用户名 ,也可以是邮箱
     * @return
     */
    public ServerResponse<String> checkValid(String str,String type){
        if(StringUtils.isNotBlank(type)){
            //校验用户名
            if(Const.USERNAME.equals(type)){
                //查询用户名是否存在
                int resultCount = userMapper.checkUserName(str);
                if(resultCount > 0){
                    return ServerResponse.createByErrorMsg("用户名已经存在");
                }
            }

            //校验邮箱
            if(Const.EMAIL.equals(type)){
               int resultCount  =  userMapper.checkEmail(str);
               if(resultCount > 0 ){
                   return ServerResponse.createByErrorMsg("Email已经存在");
               }
            }
        }
        return ServerResponse.createBySuccessMsg("校验成功");
    }


    /**
     * 删除用户的方法
     * @param id
     * @return
     */
    @Override
    public ServerResponse<String> deleteByPrimary(int id) {
        //1.判断该用户是否存在,获取用户信息
        ServerResponse<User> information = this.getInformation(id);
        User user = information.getData();
        if(user ==null){
            //2.如果用户不存在,返回失败的ServerResources
            return ServerResponse.createByErrorMsg("删除的用户不存在");
        }
        //3.如果用户存在,调用mapper 中的方法进行删除 ,得到返回值  count ==1 删除成功  count==0 ,删除失败
        int count = userMapper.deleteByPrimaryKey(id);
        if(count > 0){
            return ServerResponse.createBySuccessMsg("删除成功");
        }

        return ServerResponse.createByErrorMsg("删除失败");
    }

    /**
     * 根据用户的id获取用户信息
     */
    public ServerResponse<User> getInformation(int userId){
       User user =  userMapper.selectByPrimaryKey(userId);
       //根据id查到了用户
       if(user != null){
           return ServerResponse.createBySuccessData(user);
       }
       return ServerResponse.createByErrorMsg("用户不存在");
    }


}
