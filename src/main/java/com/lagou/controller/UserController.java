package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.common.Const;
import com.lagou.common.ResponseCode;
import com.lagou.common.ServerResponse;
import com.lagou.model.User;
import com.lagou.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
@Transactional
public class UserController {

    @Autowired
    private IUserService iUserService;

    /**
     * 1.用户登录
     */
    @RequestMapping(value = "login.do" , method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username, String password , HttpSession session){
        //1.调用serivce的登录方法 ,会返回一个ServerResponse<User>
        ServerResponse<User> response = iUserService.login(username,password);
        //2.判断是否登录成功,如果登录成功,把当前用户user放到session中
        if(response.getStatus() == ResponseCode.SUCCESS.getCode()){
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        //3.返回ServerResponse
        return  response;
    }


    /**
     * 2.分页查找
     */
    @RequestMapping(value = "list.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<PageInfo>  list(@RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                          @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
       ServerResponse<PageInfo> users =  iUserService.getUsers(pageNum,pageSize);
       return users;
    }

    /**
     * 3.添加用户
     */
    @RequestMapping(value = "add.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> add(User user){
        return iUserService.add(user);
    }

    /**
     * 4.删除用户
     */
    @RequestMapping(value = "deleteUser.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> delete(int id){
        return iUserService.deleteByPrimary(id);
    }

    /**
     * 5.用户登出
     */
    @RequestMapping(value = "logout.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> logout(HttpSession session){
        try{
            session.removeAttribute(Const.CURRENT_USER);
            return ServerResponse.createBySuccessMsg("注销成功");
        }catch (Exception e){
            return ServerResponse.createByErrorMsg("注销失败");
        }
    }
}
