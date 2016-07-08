package com.johnny.repository;

import com.johnny.dao.invest.MobileUserDao;
import com.johnny.entity.invest.MobileUser;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by johnson.yang on 2016/5/12.
 */
public class MobileUserRepository {

    private MobileUserDao mobileUserDao;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = TransactionDefinition.TIMEOUT_DEFAULT,
            transactionManager = "txManager")
    public int fetchAndDelele(String pkey) {
        List<MobileUser> userList = mobileUserDao.findByPkey(pkey);
        for (MobileUser user : userList) {
            user.setName("aa11");
            mobileUserDao.updateName(user);
            mobileUserDao.updateNonField(user);
        }
        return 0;
    }

    public void setMobileUserDao(MobileUserDao mobileUserDao) {
        this.mobileUserDao = mobileUserDao;
    }
}
