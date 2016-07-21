package com.csf.common;

import com.google.common.base.Joiner;
import com.google.common.collect.Sets;
import org.apache.commons.collections4.CollectionUtils;

/**
 * Created by johnny01.yang on 2016/6/21.
 */
public class ExcelDemoTest {

    public static void abc(String abc, ExcelHandler handler) {
        handler.doHandler(abc);
    }

    public static void main(String[] args) {
//        abc("aaa", new ExcelHandler() {
//            @Override
//            public void doHandler(String file) {
//                System.out.println(file);
//            }
//        });
        System.out.println(Joiner.on(",").skipNulls().join(CollectionUtils.subtract(Sets.newHashSet("a", "b"), Sets.newHashSet("a"))));
    }

}
