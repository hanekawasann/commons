package com.hanekawasan.commons.lang;

/**
 * 做算法题时想到的
 *
 * yukms 763803382@qq.com 2019/3/26 9:30
 */
public final class MathX {

    private MathX() { }

    /**
     * 使用欧几里得算法（辗转相除法）获取两个整数数的最大公约数
     * @param max 整数max
     * @param min 整数min
     * @return 最大公约数
     */
    public static int getGcd(int max, int min) {
        int remainder = max % min;
        if (remainder == 0) {
            return min;
        }
        return getGcd(min, remainder);
    }

    /**
     * 获取两个整数数的最小公倍数
     * @param max 整数max
     * @param min 整数min
     * @return 最小公倍数
     */
    public static int getLcm(int max, int min) {
        return max * min / getGcd(max, min);
    }

}
