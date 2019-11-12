package exp2_Chain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static class Mat{
        int m,n;
        public Mat(int m, int n) {
            this.m = m;
            this.n = n;
        }
    }
    private static Mat[] mats;
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner =  new Scanner(new File("E:\\Java\\AlgorithmExperiment\\src\\exp2_Chain\\1.mem"));
        int n = scanner.nextInt();
        mats = new Mat[n];
        for (int i = 0;i<n;i++){
            mats[i] = new Mat(scanner.nextInt(), scanner.nextInt());
        }
//        定义记录板
        int[][] save = new int[n][n];
//        初始化对角线数据
        for (int i = 0;i<n;i++){
            save[i][i] = 0;
        }
//         迭代
        for (int b = 1;b<= n-1;b++){
            for (int i = 0;i<n-b;i++){
                int min = Integer.MAX_VALUE;
                for (int j = 0;j<b;j++){
                    int t = save[i][i+j] + save[i+j+1][i+b] + Cost(i,i+b,i+j);
                    min = Math.min(min,t);
                }
                save[i][i+b] = min;
            }
        }
//        中间结果
        System.out.println("中间结果：");
        for (int i = 0; i <n ;i++){
            for (int j = 0 ; j < n;j++){
                System.out.print(save[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println("最终结果："+save[0][n-1]);

    }
//  given i <= k < j return the cost of this two matrix
    public static int Cost(int i,int j,int k){
        return mats[i].m * mats[k].n * mats[j].n;
    }
}
