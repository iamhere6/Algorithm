package kmp;

/**
 * 应用场景：字符串匹配问题
 * 方法：暴力匹配
 * 样例：str = "BBC ABCDAB ABCDABCDABDE"   str1 = "ABCDABD"
 *      判断 str 中是否含有 str1 ,如果存在，就返回第一次出现的位置，如果没有，则返回-1
 * 思路：
 *      假设现在文本串  S 匹配到 i 位置，匹配串 P 匹配到 j 位置
 *      如果当前字符匹配成功，即【 S[i] == P[j] 】 ，则 i++ , j++ ， 继续匹配下一个字符
 *      如果当前字符匹配不成功，即【 s[i] != P[j] 】 ，则 i = i - (j-1) , j = 0 ，i 回溯 ，j 置 0
 * 缺点：
 *      暴力匹配方法针对字符串匹配问题解决时，会产生大量的回溯，每次只移动一位，若是不匹配，移动到下一位接着判断，浪费大量时间
 *      那有没有一种算法，让i 不往回退，只需要移动j 即可呢？
 * 代替方法：Kmp
 */
public class ViolenceMatch {

    public static void main(String[] args) {
        String S = "BBC ABCDAB ABCDABCDABDE";
        String P = "ABCDABD";
        int index = violenceMatch(S,P);
        System.out.println(index);
    }

    /**
     * 暴力匹配
     */
    private static int violenceMatch(String s,String p){
        char[] str1 = s.toCharArray();
        char[] str2 = p.toCharArray();
        int i = 0;
        int j = 0;
        while(i < str1.length && j < str2.length){
            if (str1[i] == str2[j]){
                i++;
                j++;
            }else{
                i = i - (j-1);
                j = 0;
            }
        }
        // 判断是否匹配成功
        if (j == str2.length){
            return i - j ;
        }else{
            return -1;
        }
    }
}
