package com.foresee.utils;

import java.util.Random;

/**
 * 1) 获取id: 1111111
 * 2) 使用自定义进制转为：gpm6
 * 3) 转为字符串，并在后面加'O'字符：gpm6o
 * 4）在后面随机产生若干个随机数字字符：gpm6o7
 * 转为自定义进制后就不会出现o这个字符，然后在后面加个'o'，这样就能确定唯一性。最后在后面产生一些随机字符进行补全。
 */
public class InviteodeUtil {
    /**
     * 自定义进制(0, 1没有加入,容易与o,l混淆)
     */
    private static final char[] r = new char[]{
            'D', 'E', 'I', '2', 'O',
            'K', 'H', 'R', '3', 'V',
            'L', 'T', 'Z', 'A', 'Q',
            'P', 'J', 'S', 'C', '4',
            '8', 'F', 'W', '9', 'N',
            '7', 'M', '5', 'G', 'U',
            'B', 'X', 'Y'
    };
    private static final char b = '6';
    private static final int binLen = r.length;
    private static final int s = 6;

    public static String toSerialCode(long id) {
        char[] buf = new char[32];
        int charPos = 32;
        while ((id / binLen) > 0) {
            int ind = (int) (id % binLen);
            buf[--charPos] = r[ind];
            id /= binLen;
        }
        buf[--charPos] = r[(int) (id % binLen)];
        String str = new String(buf, charPos, (32 - charPos));
        // 不够长度的自动随机补全
        if (str.length() < s) {
            StringBuilder sb = new StringBuilder();
            sb.append(b);
            Random rnd = new Random();
            for (int i = 1; i < s - str.length(); i++) {
                sb.append(r[rnd.nextInt(binLen)]);
            }
            str += sb.toString();
        }
        return str;
    }

    public static long codeToId(String code) {
        char chs[] = code.toUpperCase().toCharArray();
        long res = 0L;
        for (int i = 0; i < chs.length; i++) {
            int ind = 0;
            for (int j = 0; j < binLen; j++) {
                if (chs[i] == r[j]) {
                    ind = j;
                    break;
                }
            }
            if (chs[i] == b) {
                break;
            }
            if (i > 0) {
                res = res * binLen + ind;
            } else {
                res = ind;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String s = toSerialCode(522);
        System.out.println(s);
        System.out.println(codeToId(s));
    }
}