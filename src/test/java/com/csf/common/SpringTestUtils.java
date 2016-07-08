package com.csf.common;

import com.csf.BaseCaseTest;
import com.csf.entity.ReflectionTestEntity;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.ReflectionUtils;


/**
 * Created by johnson.yang on 2016/5/12.
 */
public class SpringTestUtils extends BaseCaseTest {

    @Test
    public void reflectionTestUtils() {
        ReflectionTestEntity entity = new ReflectionTestEntity();
        ReflectionTestUtils.setField(entity, "name", "aa");
        ReflectionTestUtils.setField(entity, "age", "aaa");
        System.out.println(entity);
    }

}
