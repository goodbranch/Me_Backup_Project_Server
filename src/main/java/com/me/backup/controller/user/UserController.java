package com.me.backup.controller.user;

import com.me.backup.controller.BaseApiController;
import com.me.backup.pojo.UserEntity;
import com.me.backup.pojo.user.SmsModel;
import com.me.backup.service.impl.user.UserServiceImpl;
import com.me.backup.util.NumberUtil;
import com.me.backup.util.ProjectUtil;
import com.me.backup.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

        final String name = request.getParameter("username");
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
            return toResponse(2, "账号已存在，请直接登录或找回密码", null);
        }

        userEntity = new UserEntity();
        userEntity.setUsername(name);
        userEntity.setPhoneNumber(phoneNumber);
        userEntity.setPassword(password);
        if (!StringUtil.isEmpty(email)) {
            userEntity.setEmail(email);
        }
        if (!StringUtil.isEmpty(gender)) {
            userEntity.setGender(NumberUtil.String2Int(gender));
        }

        userEntity.setToken(token);
        if (!StringUtil.isEmpty(birthday)) {
            userEntity.setBirthday(new Timestamp(NumberUtil.String2Long(birthday)));
        }
        if (!StringUtil.isEmpty(idCard)) {
            userEntity.setIdcard(idCard);
        }

        boolean success = userService.save(userEntity);

        if (!success) {
            return toResponse(0, "注册失败", null);
        }
        return toResponse(1, "注册成功", userEntity);
    }


    @RequestMapping(value = "/sendSms", method = RequestMethod.GET)
    public @ResponseBody
    Object sendSms(@RequestParam("phoneNumber") String phoneNumber) {

        if (!ProjectUtil.isMobilePhoneNum(phoneNumber)) {
            return toResponse(0, "请输入正确的手机号码");
        }

        SmsModel smsModel = userService.sendSms(phoneNumber);

        if (smsModel == null || smsModel.code != 200) {
            return toResponse(0, "验证码发送失败，请重试");
        }

        return toResponse(1, "验证码已发送，请查收");
    }


    @RequestMapping(value = "/userinfo", method = RequestMethod.GET)
    public @ResponseBody
    Object userInfo(HttpServletRequest request, HttpServletResponse response) {
        final String name = request.getParameter("username");
        final String phoneNumber = request.getParameter("phoneNumber");
        final String password = request.getParameter("password");

        UserEntity userEntity = findUser(name, phoneNumber, password);

        if (userEntity == null) {
            return toResponse(0, "用户信息查询失败", null);
        }

        return toResponse(1, "success", userEntity);
    }


}
