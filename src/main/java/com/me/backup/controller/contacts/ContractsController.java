package com.me.backup.controller.contacts;

import com.me.backup.controller.BaseApiController;
import com.me.backup.pojo.ContactsEntity;
import com.me.backup.service.impl.contacts.ContactsServiceImpl;
import com.me.backup.util.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 通讯录管理
 */

@Controller
public class ContractsController extends BaseApiController {

    @Autowired
    private ContactsServiceImpl contactsService;

    @RequestMapping(value = "/uploadContacts", method = RequestMethod.POST)
    public @ResponseBody
    Object uploadContacts(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        String contactsEntityListStr = request.getParameter("contactsList");

//        if (contactsEntityList == null || contactsEntityList.isEmpty()) {
//            return toResponse(1, "本次更新0条");
//        }

        List<ContactsEntity> userContacts = contactsService.getContactsByUserId(NumberUtil.String2Int(userId));


        return toResponse(1, "同步完成", userContacts);
    }

}
