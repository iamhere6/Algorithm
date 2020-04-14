package kmp;

import java.util.Arrays;

/**
 *  应用场景：字符串匹配问题
 *  目的：KMP 是一个解决模式串在文本串是否出现过，如果出现过，最早出现的位置的经典算法
 *  核心：KMP 方法算法就利用之前判断过的信息，通过一个 next 数组，保存模式串中前后最长公共子序列的长度，
 *      每次回溯时，通过 next 数组找到，前面匹配过的位置，省去了大量的计算时间
 *  权威网址：https://www.cnblogs.com/zzuuoo666/p/9028287.html
 *  样例：str = "BBC ABCDAB ABCDABCDABDE"   str1 = "ABCDABD"
 *      判断 str 中是否含有 str1 ,如果存在，就返回第一次出现的位置，如果没有，则返回-1
 *      要求：使用KMP算法完成，而不是使用简单的暴力匹配算法
 *      正确结果：
 *          next=[0, 0, 0, 0, 1, 2, 0]
 *          index=15
 *
 */
public class Kmp {

    public static void main(String[] args) {
        String S = "BBC ABCDAB ABCDABCDABDE"; // 主串
        String P = "ABCDABD"; // 模式串

        int[] next = kmpNext("ABCDABD");
        System.out.println("next=" + Arrays.toString(next));

        int index = kmpSearch(S,P,next);
        System.out.println("index=" + index);
    }

    /**
     * 获取模式串的部分匹配值表
     *              匹配串 = "ABCDABD"
     *          最大长度表 = [0, 0, 0, 0, 1, 2, 0]
     *               next = [-1, 0, 0, 0, 0, 1, 2]
     *              匹配串 = "ABAB"
     *          最大长度表 = [0, 0, 1, 2]
     * @param dest  String dest = "ABCDABD"; // 模式串
     * @return  next=[0, 0, 0, 0, 1, 2, 0]
     */
    private static int[] kmpNext(String dest){
        // 创建一个数组保存最大长度表
        int[] next = new int[dest.length()];
        next[0] = 0; // 模式串的长度 = 1 ， 匹配值 = 0
        for (int i = 1,j=0; i < dest.length() ; i++){
            // 当 dest.charAt(i) != dest.charAt(j) , 我们需要从 next[j-1] 获取新的 j
            // 直到我们发现有 dest.charAt(i) == dest.charAT(j) 成立才退出
            // 这个思路是kmp的核心点
            while(j > 0 && dest.charAt(i) != dest.charAt(j)){
                j = next[j-1];
            }

            // 当dest.charAt(i) == dest.charAt(j)  , 最大长度值值就 +1
            if (dest.charAt(i) == dest.charAt(j)){
                j++;
            }
            next[i] = j;
        }
        return next;
    }



    /**
     *  Kmp搜索算法
     * @param str  源字符串
     * @param str1  模式串
     * @param next  部分匹配表（模式串对应）
     * @return      -1 未匹配到   否则返回第一个匹配的位置
     */
    private static int kmpSearch(String str, String str1, int[] next) {
        // 遍历
        for (int i = 0,j = 0; i < str.length(); i++){
            while(j>0 && str.charAt(i) != str1.charAt(j)){
                j = next[j-1];
            }
            if (str.charAt(i) == str1.charAt(j)){
                j++;
            }
            if (j == str1.length()){
                return i - j + 1;
            }
        }
        return -1;
    }
}
