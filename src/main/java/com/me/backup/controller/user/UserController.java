package com.me.backup.controller.user;

import com.me.backup.controller.BaseApiController;
import com.me.backup.pojo.UserEntity;
import com.me.backup.service.impl.user.UserServiceImpl;
import com.me.backup.util.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class UserController extends BaseApiController {


    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody
    Object login(HttpServletRequest request, HttpServletResponse response) {

        final String name = request.getParameter("name");
        final String phoneNumber = request.getParameter("phoneNumber");
        final String password = request.getParameter("password");

        UserEntity userEntity = findUser(name, phoneNumber, password);

        if (userEntity != null) {
            return toResponse(1, "登录成功", userEntity);
        }
        return toResponse(0, "账号或者密码错误", null);
    }


    private UserEntity findUser(String name, String phoneNumber, String password) {
        List<UserEntity> userEntitys = userService.findByHql(
                "from UserEntity where (username=? or phoneNumber=?) and password=?", name, phoneNumber, password);

        if (userEntitys == null || userEntitys.isEmpty()) {
            return null;

        }

        return userEntitys.get(0);
    }


    private UserEntity findUser(String name, String phoneNumber) {
        List<UserEntity> userEntitys = userService.findByHql(
                "from UserEntity where (username=? or phoneNumber=?)", name, phoneNumber);

        if (userEntitys == null || userEntitys.isEmpty()) {
            return null;

        }

        return userEntitys.get(0);
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public @ResponseBody
    Object register(HttpServletRequest request, HttpServletResponse response) {
        final String name = request.getParameter("username");
        final String phoneNumber = request.getParameter("phoneNumber");
        final String password = request.getParameter("password");
        final String email = request.getParameter("email");
        final String gender = request.getParameter("gender");
        final String token = request.getParameter("token");
        final String birthday = request.getParameter("birthday");
        final String idCard = request.getParameter("idCard");


        UserEntity userEntity = findUser(name, phoneNumber);

        if (userEntity != null) {
            return toResponse(0, "账号已存在，请直接登录或找回密码", null);
        }
        userEntity = new UserEntity();
        userEntity.setUsername(name);
        userEntity.setPhoneNumber(phoneNumber);
        userEntity.setPassword(password);
        userEntity.setEmail(email);
        userEntity.setGender(NumberUtil.String2Int(gender));
        userEntity.setToken(token);
        userEntity.setBirthday(new Timestamp(NumberUtil.String2Long(birthday)));
        userEntity.setIdcard(idCard);
        userService.save(userEntity);

        UserEntity result = findUser(name, phoneNumber, password);

        if (result == null) {
            return toResponse(0, "注册失败", null);

        }
        return toResponse(1, "注册成功", result);
    }


    @RequestMapping(value = "/userinfo", method = RequestMethod.GET)
    public @ResponseBody
    Object userInfo(HttpServletRequest request, HttpServletResponse response) {
        final String name = request.getParameter("name");
        final String phoneNumber = request.getParameter("phonenumber");
        final String password = request.getParameter("password");

        UserEntity userEntity = findUser(name, phoneNumber, password);

        if (userEntity == null) {
            return toResponse(0, "用户信息查询失败", null);
        }

        return toResponse(1, "success", userEntity);
    }


}
