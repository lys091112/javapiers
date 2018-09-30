package com.xianyue.basictype;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Xianyue
 */
public class BitUtil {

    public static boolean isBitFit(int param, int ordinal) {
        return (param & (1 << ordinal - 1)) > 0;
    }

    /**
     * 将int转化为字节数组
     */
    public static void writeRawVarInt32(int value, OutputStream stream) throws IOException {
        while (true) {
            if ((value & ~0x7F) == 0) {
                stream.write((byte) value);
                return;
            } else {
                stream.write(((byte) ((value & 0x7F) | 0x80)));
                value >>>= 7;
            }
        }
    }

    public static byte[] writeVarInt32(int value) {
        byte[] buf = new byte[5];
        int pos = 0;
        if (value >>> 7 == 0) {
            buf[pos] = (byte) value;
        } else if (value >>> 14 == 0) {
            buf[pos++] = (byte) ((value & 0x7F) | 0x80);
            buf[pos] = (byte) (value >>> 7);
        } else if (value >>> 21 == 0) {
            buf[pos++] = (byte) ((value & 0x7F) | 0x80);
            buf[pos++] = (byte) (value >>> 7 | 0x80);
            buf[pos] = (byte) (value >>> 14);
        } else if (value >>> 28 == 0) {
            buf[pos++] = (byte) ((value & 0x7F) | 0x80);
            buf[pos++] = (byte) (value >>> 7 | 0x80);
            buf[pos++] = (byte) (value >>> 14 | 0x80);
            buf[pos] = (byte) (value >>> 21);
        } else {
            buf[pos++] = (byte) (value >>> 7 | 0x80);
            buf[pos++] = (byte) (value >>> 14 | 0x80);
            buf[pos++] = (byte) (value >>> 21 | 0x80);
            buf[pos] = (byte) (value >>> 28);
            buf[pos++] = (byte) ((value & 0x7F) | 0x80);
        }
        return buf;
    }

    public static void main(String[] args) {
        boolean ret = isBitFit(7, 2);
        System.out.println("result is " + ret);

        for (byte b : writeVarInt32(256)) {
            System.out.println(b);
        }
    }


}
