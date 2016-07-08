package com.csf.repository;

import com.csf.BaseCaseTest;
import com.johnny.repository.MobileUserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by johnson.yang on 2016/5/12.
 */
public class MobileUserRepositoryTest extends BaseCaseTest {

    @Autowired
    private MobileUserRepository mobileUserRepository;

    @Test
    public void testUpate(){
        mobileUserRepository.fetchAndDelele("1bebfb2976c7a399");
    }

}
