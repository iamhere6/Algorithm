package dynamic;

/**
 * 动态规划应用场景 --- 背包问题 之 01背包问题
 *       物品         重量        价格
 *       吉他           1         1500
 *       音响           4         3000
 *       电脑           5         2000
 *  目的：
 *      （1）背包容量4磅
 *      （2）装入背包的总价值最大，并且重量不超出
 *      （3）装入的物品不重复
 *  思路：
 *      （1）01背包 ：每个物品最多放一个
 *      （2）无限背包：每个物品可以无限放入 （可以转化为无限背包）
 */
public class KnapsackProblem {

    public static void main(String[] args) {

        int[] weight = {1,4,3}; // 物品的重量
        int[] price = {1500,3000,2000}; // 物品的价值
        int num = price.length; // 物品数量
        int pSize = 4; // 背包容量

        // 创建二维数组
        int[][] path = new int[num+1][pSize+1]; // 记录商品放入的情况
        int[][] maxPrice = new int[num+1][pSize+1]; // 前i个物品中能够装入容量为j的背包中的最大价值
        // 初始化第一行、第一列
        for (int i = 0; i < maxPrice[0].length; i ++){
            maxPrice[0][i] = 0;
        }
        for (int i = 0; i < maxPrice.length; i ++){
            maxPrice[i][0] = 0;
        }

        // 动态规划处理
        for (int i = 1; i<maxPrice.length;i++){ // 抛开第一行
            for (int j = 1;j<maxPrice[0].length;j++){ // 抛开第一列
                if (weight[i-1] > j){ // 当前物品重量 > 背包容量
                    maxPrice[i][j] = maxPrice[i-1][j];
                }else{
                    // maxPrice[i][j] = Math.max(maxPrice[i-1][j],price[i-1]+maxPrice[i-1][j-weight[i-1]]);
                    if (maxPrice[i-1][j] < price[i-1] + maxPrice[i-1][j-weight[i-1]]){
                            maxPrice[i][j] = price[i-1]+maxPrice[i-1][j-weight[i-1]];
                            path[i][j] = 1;
                    }else{
                        maxPrice[i][j] = maxPrice[i-1][j];
                    }
                }
            }
        }

        // 遍历输出maxValue
        for (int i = 0; i < maxPrice.length;i++){
            for (int j = 0;j<maxPrice[i].length;j++){
                System.out.print(maxPrice[i][j]+" ");
            }
            System.out.println();
        }

        System.out.println("===========================");

        // 遍历输出path
        for (int[] ints : path) {
            for (int j = 0; j < ints.length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
        System.out.println("===========================");

        int i = path.length - 1;
        int j = path[0].length - 1;
        while(i>0 && j>0){
            if (path[i][j] == 1){
                System.out.printf("第%d个商品放入到背包\n",i);
                j -= weight[i-1];
            }
            i--;
        }


    }


}
