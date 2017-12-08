package com.me.backup.controller.contacts;

import com.google.gson.reflect.TypeToken;
import com.me.backup.controller.BaseApiController;
import com.me.backup.pojo.ContactsEntity;
import com.me.backup.service.impl.contacts.ContactsServiceImpl;
import com.me.backup.util.JsonUtil;
import com.me.backup.util.NumberUtil;
import com.me.backup.util.StringUtil;
import com.me.backup.util.WebServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 通讯录管理
 */

@Controller
public class ContractsController extends BaseApiController {

    @Autowired
    private ContactsServiceImpl contactsService;


    @RequestMapping(value = "/getContractList", method = RequestMethod.GET)
    public @ResponseBody
    Object getUserContractList(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        final int userId = NumberUtil.String2Int(httpServletRequest.getParameter("userId"));

        return toResponse(1, "成功", contactsService.getContactsByUserId(userId));
    }


    @RequestMapping(value = "/uploadContacts", method = RequestMethod.POST)
    public @ResponseBody
    Object uploadContacts(HttpServletRequest request, HttpServletResponse response, @RequestParam int userId, @RequestParam
            List<ContactsEntity> contactsList) {

        if (contactsList == null || contactsList.isEmpty()) {

            return toResponse(1, "数据为空");
        }

        List<ContactsEntity> userContacts = contactsService.getContactsByUserId(userId);


        ContractsCombine contractsCombine = compreConstacts(contactsList, userContacts);

        if (contractsCombine != null) {

            contactsService.save(contractsCombine.addedList);
            contactsService.update(contractsCombine.updateList);

        }

        return toResponse(1, "同步完成", contractsCombine);
    }


    /**
     * @param uploadList 上传的联系人
     * @param preList    上一次的联系人
     */
    private ContractsCombine compreConstacts(List<ContactsEntity> uploadList, List<ContactsEntity> preList) {


        if (uploadList == null || uploadList.isEmpty()) {
            return null;
        }


        ContractsCombine contractsCombine = null;

        if (preList == null || preList.isEmpty()) {
            contractsCombine = new ContractsCombine(uploadList, null);
            return contractsCombine;
        }

        List<ContactsEntity> addedList = new ArrayList<ContactsEntity>();
        List<ContactsEntity> updateList = new ArrayList<ContactsEntity>();


        for (ContactsEntity contactsEntity : uploadList) {

            boolean find = false;
            for (ContactsEntity hasContract : preList) {
                if (StringUtil.equals(contactsEntity.getPhoneNumber(), hasContract.getPhoneNumber())) {
                    find = true;
                    break;
                }

            }

            if (find) {
                updateList.add(contactsEntity);
            } else {
                addedList.add(contactsEntity);
            }

        }


        return new ContractsCombine(addedList, updateList);
    }


    private class ContractsCombine {

        public List<ContactsEntity> addedList;

        public List<ContactsEntity> updateList;

        public ContractsCombine(List<ContactsEntity> addedList, List<ContactsEntity> updateList) {
            this.addedList = addedList;
            this.updateList = updateList;
        }
    }


}
