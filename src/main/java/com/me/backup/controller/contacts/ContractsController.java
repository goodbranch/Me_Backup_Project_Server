package com.me.backup.controller.contacts;

import com.me.backup.controller.BaseApiController;
import com.me.backup.pojo.ContactsEntity;
import com.me.backup.service.impl.contacts.ContactsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    Object uploadContacts(@RequestParam("contactsList") List<ContactsEntity> contactsEntityList) {

        if (contactsEntityList == null || contactsEntityList.isEmpty()) {
            return toResponse(1, "本次更新0条");
        }


        return toResponse(1, "同步完成");
    }

}
