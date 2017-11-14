package com.me.backup.service.impl.user;

import com.google.gson.reflect.TypeToken;
import com.me.backup.controller.user.SmsController;
import com.me.backup.pojo.UserEntity;
import com.me.backup.pojo.user.SmsModel;
import com.me.backup.service.impl.BaseServiceImpl;
import com.me.backup.util.JsonUtil;
import org.springframework.stereotype.Repository;

@Repository()
public class UserServiceImpl extends BaseServiceImpl<UserEntity> {


    public SmsModel sendSms(String phoneNumber) {
        SmsController smsController = new SmsController();
        try {
            return JsonUtil.fromJson(smsController.sendSms(phoneNumber), new TypeToken<SmsModel>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
