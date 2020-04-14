package kmp;

/**
 *  概念：BM算法：
 *      从模式串的尾部开始匹配，且拥有在最坏情况下O(N)的时间复杂度。
 *      KMP的匹配是从模式串的开头开始匹配的，而Boyer-Moore算法
 *      在实践中，比KMP算法的实际效能高。
 *
 * 目的：查找模式串是否在文本串中，如果存在，返回模式串在文本串中的位置。
 *   源字符串：HERE IS A SIMPLE EXAMPLE
 *   模式串：EXAMPLE
 */
public class Bm {
        //注意此处right[]的构造
        public static void getRight(String pat, int[] right) {
            for (int i = 0; i < 256; i++) {
                right[i] = -1;
            }
            for (int j = 0; j < pat.length(); j++) {
                right[pat.charAt(j)] = j;
            }
        }

        public static int Search(String txt, String pat, int[] right) {
            int M = txt.length();
            int N = pat.length();
            int skip;
            for (int i = 0; i < M - N; i += skip) {
                skip = 0;
                for (int j = N - 1; j >= 0; j--) {
                    if (pat.charAt(j) != txt.charAt(i + j)) {
                        skip = j - right[txt.charAt(i + j)];
                        if (skip < 1)
                            skip = 1;
                        break;
                    }
                }
                if (skip == 0)
                    return i;
            }
            return -1;
        }

        public static void main(String[] args) {
            String txt = "THIS IS A BIG TIGER";
            String pat = "IG";
            int[] right = new int[256];
            getRight(pat, right);
            System.out.println(Search(txt, pat, right));
        }

    }

