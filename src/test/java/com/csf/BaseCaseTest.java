package com.csf;

import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml", "classpath:spring/spring-domain.xml",
        "classpath:spring/spring-repository.xml", "classpath:spring/spring-resources.xml",
        "classpath:spring/spring-web.xml", "classpath:spring/spring-service.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public abstract class BaseCaseTest {

    protected final Logger log = Logger.getLogger(BaseCaseTest.class);


}
