package com.lv.demo.code;

/**
 * xxxxxx xxoxox
 * xxoxxx xxxoxx
 * xoxoxx xxxxxx
 * xxoxxx xxxxxx
 * <p>
 * 上边有两幅图，分别由x o 两个字符组成。图一中o完成了一个包围，被包围的字符以及o本身面积为5；图二没有完成包围，只有的o的面积是3。
 * 现在给定一个这样的数组，计算o以及被o包围的面积。
 * <p>
 * 思路：
 * 1.计算包围的面积=》计算没有被包围的面积
 * 2.没有被包围=》从边界沿x可达
 * 3.遍历边界，记录全部可达的x并更新为d（递归）
 * 4.输出非d的char的数量
 * <p>
 * 递归的思想其实就是"递""归"两个字的含义。"递"代表了向下走；"归"代表了什么时候程序停止，向回走。
 * 所以关键点在于：
 * 1.明确终止条件
 * 2.明确递归时的处理逻辑
 *
 * @desc:
 * @author: huichaolv@tencent.com
 * @create: 2021/6/28
 */
public class 被包围的面积 {

    public static void main(String[] args) {
        char[][] chars = new char[][]{
                {'x', 'x', 'x', 'x', 'x', 'x'},
                {'x', 'x', 'o', 'o', 'x', 'x'},
                {'x', 'o', 'x', 'o', 'x', 'x'},
                {'x', 'x', 'o', 'x', 'x', 'x'},
        };
        for (int i = 0; i < chars.length; i++) {
            mark(chars, i, 0);
            mark(chars, i, chars[0].length - 1);
        }
        for (int j = 0; j < chars[0].length; j++) {
            mark(chars, 0, j);
            mark(chars, chars.length - 1, j);
        }
        int count = 0;
        for (char[] aChar : chars) {
            for (char c : aChar) {
                if (c != 'd') {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    public static void mark(char[][] chars, int i, int j) {
        //终止条件：碰到o；碰到d；越界；
        if (i < 0 || i >= chars.length || j < 0 || j >= chars[0].length) {
            return;
        }
        if (chars[i][j] == 'o' || chars[i][j] == 'd') {
            return;
        }
        //处理
        chars[i][j] = 'd';
        mark(chars, i - 1, j);
        mark(chars, i + 1, j);
        mark(chars, i, j - 1);
        mark(chars, i, j + 1);
    }
}
