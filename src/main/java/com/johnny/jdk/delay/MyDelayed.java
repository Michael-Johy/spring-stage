package com.johnny.jdk.delay;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p>
 * Author: yang_tao
 * Date  : 2017-07-25 15:21
 */
public class MyDelayed implements Delayed {

    private String msg;
    private String time;


    @Override
    public long getDelay(TimeUnit unit) {
        return 0;
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }
}
