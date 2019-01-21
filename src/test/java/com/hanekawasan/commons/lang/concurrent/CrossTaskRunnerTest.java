package com.hanekawasan.commons.lang.concurrent;

import java.util.concurrent.ExecutionException;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author yukms 2019/1/15
 */
public class CrossTaskRunnerTest {

    @Test
    public void test_run_order() throws ExecutionException, InterruptedException {
        for (int i = 0; i < 100; i++) {
            StringBuilder buffer = new StringBuilder();
            CrossTaskRunner.run(controller -> {
                buffer.append("MAIN");
                controller.notifyInsertAndAwaitMain();
            }, () -> {
                buffer.append("INSERT");
            });
            Assert.assertEquals("MAININSERT", buffer.toString());
        }
    }

    @Test
    public void test_run_insert() throws ExecutionException, InterruptedException {
        for (int i = 0; i < 100; i++) {
            StringBuilder buffer = new StringBuilder();
            CrossTaskRunner.run(controller -> {
                buffer.append("MAIN");
                controller.notifyInsertAndAwaitMain();
                buffer.append("MAIN");
            }, () -> {
                buffer.append("INSERT");
            });
            Assert.assertEquals("MAININSERTMAIN", buffer.toString());
        }
    }

}