package com.hanekawasan.commons.lang;

import org.junit.Assert;
import org.junit.Test;

/**
 * yukms 763803382@qq.com 2019/3/26 9:33
 */
public class MathXTest {

    @Test
    public void test_getGcd() {
        Assert.assertEquals(2, MathX.getGcd(6, 4));
        Assert.assertEquals(1, MathX.getGcd(1997, 615));
    }

    @Test
    public void test_getLcm() {
        Assert.assertEquals(12, MathX.getLcm(6, 4));
        Assert.assertEquals(40, MathX.getLcm(40, 20));
        Assert.assertEquals(90, MathX.getLcm(45, 30));
        Assert.assertEquals(540, MathX.getLcm(270, 36));
    }

}