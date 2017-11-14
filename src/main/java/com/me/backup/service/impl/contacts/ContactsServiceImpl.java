package com.me.backup.service.impl.contacts;

import com.me.backup.dao.impl.contacts.ContactsDaoImpl;
import com.me.backup.pojo.ContactsEntity;
import com.me.backup.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public class ContactsServiceImpl extends BaseServiceImpl<ContactsEntity> {

    @Autowired
    private ContactsDaoImpl contactsDao;

    public List<ContactsEntity> getContactsByUserId(Serializable userId) {
        String hql = "from ContactsEntity where userId=?";

        return contactsDao.findByHql(hql, userId);
    }
}
