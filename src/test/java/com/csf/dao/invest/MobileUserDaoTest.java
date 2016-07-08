package com.csf.dao.invest;

import com.csf.BaseCaseTest;
import com.johnny.dao.invest.MobileUserDao;
import com.johnny.entity.invest.MobileUser;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * Created by johnson.yang on 2016/5/12.
 */
public class MobileUserDaoTest extends BaseCaseTest {

    @Autowired
    @Qualifier("mobileUserDao")
    private MobileUserDao mobileUserDao;

    @Test
    public void testFetchByPkey() {
        log.info("begin to testFetchByPkey");
        List<MobileUser> users = mobileUserDao.findByPkey("1bebfb2976c7a399");
        users.forEach(System.out::println);
        log.info("end to testFetchByPkey");
    }

}
