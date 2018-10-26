package com.xianyue.basictype;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Xianyue
 */
public class BitUtilTest {

    @Test
    public void isFitBitTest() {
        boolean ret = BitUtil.isBitFit(7, 1);
        Assert.assertTrue(ret);
        ret = BitUtil.isBitFit(7, 2);
        Assert.assertTrue(ret);
        ret = BitUtil.isBitFit(7, 3);
        Assert.assertTrue(ret);
        ret = BitUtil.isBitFit(7, 4);
        Assert.assertFalse(ret);
        ret = BitUtil.isBitFit(7, 0);
        Assert.assertFalse(ret);
    }

}
