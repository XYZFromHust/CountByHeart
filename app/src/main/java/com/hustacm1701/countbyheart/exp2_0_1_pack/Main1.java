package exp2_0_1_pack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main1 {
    private static int[][][] save;
    private static Item[] items;
    private static int M,n;
    private static boolean[] choose;
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("E:\\Java\\AlgorithmExperiment\\src\\exp2_0_1_pack\\1.mem"));
        n = scanner.nextInt();
        M = scanner.nextInt();
        save = new int[n+1][M+1][2];
        items = new Item[n+1];
        choose= new boolean[n+1];
        // 数据初始化
        for (int i =1;i<=n;i++){
            choose[i] = false;
            items[i] = new Item(scanner.nextInt(),scanner.nextInt());
            for (int j = 1; j <= M ;j++)
            {
                // 如果未计算，则计算此时的值，并做记录
                save[i][j][0] = -1;// 0 号值用来保存f(i,m) 的最大值
                save[i][j][1] = -1;// 1 号值用来保存i号物品选或者不选 0 为不选，1为选
            }
        }
        // 动态规划调用
        int dpAns =  dp(n,M);
        System.out.println(dpAns);
        for (int i = 1; i <= n;i++){
            System.out.print(choose[i]+" ");
        }
        System.out.println();
    }
    private static int dp(int n,int M){
        int ans = f(n,M);
        // 路径回溯
        for (int i = n;i>0;i--){
            choose[i] = save[i][M][1] == 1;
//            if (save[i][M][1] == -1){
//                System.exit(-1);
//            }
            M = choose[i]?M - items[i].w:M;
        }
        return ans;
    }
    private static int f(int i,int m){
        // 判断为0的情况   此时直接返回
        if (i == 0 ){
            return m<0?Integer.MIN_VALUE:0;
        }
        // 当m小于0时 代表直接不可能，则返回
        if (m<0)
            return Integer.MIN_VALUE;
        // 如果这个数值之前有计算过，那么就直接返回
        if (save[i][m][0]!=-1){
            return save[i][m][0];
        }
        // 如果没有计算过，那么就计算f(i,m)
        int a = f(i-1,m);// 不选的情况
        int b = f(i-1,m-items[i].w) + items[i].p; //选的情况
        if (a>b){
            // 最大值为a 此时不选
            save[i][m][0] = a;
            save[i][m][1] = 0;
        }else {
            // 最大值为b 此时选
            save[i][m][0] = b;
            save[i][m][1] = 1;
        }
        return save[i][m][0];
    }
}
