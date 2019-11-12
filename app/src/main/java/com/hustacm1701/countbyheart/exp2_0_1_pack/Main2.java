package exp2_0_1_pack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main2 {
    private static int[][][] save;
    private static Item[] items;
    private static int M,n;
    private static boolean[] choose;
    private static class Item{
        int p;
        int w;
        public Item(int p, int w) {
            this.p = p;
            this.w = w;
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("E:\\Java\\AlgorithmExperiment\\src\\exp2_0_1_pack\\2.mem"));
        n = scanner.nextInt();
        M = scanner.nextInt();
        save = new int[n+1][M+1][2];
        items = new Item[n+1];
        choose= new boolean[n+1];
        // 输入数据 O(n)
        for (int  i =1;i<=n;i++){
            items[i] = new Item(scanner.nextInt(),scanner.nextInt());
        }
        // 数据初始化 O(nM)
        for (int i = 0;i<=n;i++){
            choose[i] = false;
            for (int j = 0; j <= M ;j++)
            {
                // 如果未计算，则计算此时的值，并做记录
                save[i][j][0] = 0;// 0 号值用来保存f(i,m) 的最大值
                save[i][j][1] = -1;// 1 号值用来保存i号物品选或者不选 0 为不选，1为选
            }
        }
        // 数据赋值 O(nM)
        for (int i = 1;i<=n;i++){
            for (int j = 1;j<=M;j++){
                if (j<items[i].w){
                    // 如果背包容量 j小于物品的重量  则不将该物品考虑在内
                    save[i][j][0] = save[i-1][j][0];
                    save[i][j][1] = 0;
                }else {
                    // 否则按照公式计算最大情况，并做记录
                    int a = save[i-1][j][0];
                    int b = save[i-1][j-items[i].w][0] + items[i].p;
                    if (a>b){ // 不选的情况
                        save[i][j][0] = a;
                        save[i][j][1] = 0;
                    }else { // 选的情况
                        save[i][j][0] = b;
                        save[i][j][1] = 1;
                    }
                }
            }
        }
        // 输出打印保存表
        for (int i = 1;i<=n;i++){
            for (int j=1;j<=M;j++){
                System.out.print(save[i][j][0]+"("+save[i][j][1]+")"+"\t");
            }
            System.out.println();
        }

        int ans = save[n][M][0];
        // 回溯求选择元素 O(n)
        for (int i = n;i>0;i--){
            // 对choose[i]赋值
            choose[i] = save[i][M][1] == 1;
//            if (save[i][M][1] == -1 && M!=0){
//                System.exit(-1);
//            }
            // 如果元素被选择了，则容量减去物品的重量，否则保持不变
            M = choose[i]?M - items[i].w:M;
        }

        //输出结果
        System.out.println("max price:"+ans);
        System.out.print("Items:\t");
        for (int i = 1; i <= n;i++){
            System.out.print("Item"+i+"\t");
        }
        System.out.print("\nChoose:\t");
        for (int i = 1; i <= n;i++){
            System.out.print(choose[i]+"\t");
        }
        System.out.println();
    }
}
