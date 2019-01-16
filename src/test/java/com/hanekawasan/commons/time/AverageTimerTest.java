package com.hanekawasan.commons.time;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author yukms 2019/1/16
 */
public class AverageTimerTest {
    @Test
    public void time() throws Exception {
        StringBuilder builder = new StringBuilder();
        AverageTimer.time("test", 10, i -> {
            try {
                builder.append(i);
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Assert.assertEquals("12345678910", builder.toString());
    }

}