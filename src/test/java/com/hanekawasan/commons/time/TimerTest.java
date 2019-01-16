package com.hanekawasan.commons.time;

import java.time.temporal.ChronoUnit;

import org.junit.Test;

/**
 * @author yukms 2019/1/16
 */
public class TimerTest {

    @Test
    public void test_getDuration() {
        Timer timer = Timer.start("test");
        timer.end();
        timer.getDuration();
    }

    @Test(expected = NullPointerException.class)
    public void test_getDuration_exception() {
        Timer.start("test").getDuration();
    }

    @Test
    public void test_getIntervals() {
        Timer timer = Timer.start("test");
        timer.end();
        timer.getIntervals();
    }

    @Test(expected = NullPointerException.class)
    public void test_getIntervals_exception() {
        Timer.start("test").getIntervals();
    }

    @Test
    public void test_println() {
        Timer timer = Timer.start("test");
        timer.end();
        timer.print();
        timer.print(ChronoUnit.SECONDS);
    }

    @Test
    public void test_printlnCompare() throws InterruptedException {
        Timer test1 = Timer.start("test1");
        Thread.sleep(1);
        test1.end();
        Timer test2 = Timer.start("test2");
        Thread.sleep(10);
        test2.end();
        test1.printlnCompare(test1);
        test1.printlnCompare(test2);
        test2.printlnCompare(test1);
    }

    @Test(expected = NullPointerException.class)
    public void test_printlnCompare_exception() throws InterruptedException {
        Timer test1 = Timer.start("test1");
        Timer test2 = Timer.start("test2");
        test2.end();
        test1.printlnCompare(test1);
    }

    @Test
    public void test_reStart() throws InterruptedException {
        Timer timer = Timer.start("test");
        timer.end();
        timer.print();
        timer.reStart();
        Thread.sleep(1);
        timer.end();
        timer.print();
    }

    @Test(expected = NullPointerException.class)
    public void test_reStart_exception() throws InterruptedException {
        Timer timer = Timer.start("test");
        timer.end();
        timer.print();
        timer.reStart();
        Thread.sleep(1);
        timer.print();
    }
}