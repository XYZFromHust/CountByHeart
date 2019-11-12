package exp1_kTh;

import java.util.Arrays;
import java.util.Random;

public class Main {
    // 阈值
    private static final int no = 39;
    // basic 数值
    private static int BASIC = 9;
    public static void main(String[] args) {
        Random random = new Random();
        final int bound = 1000000;
        int[] array = new int[bound];
        int[] copy = new int[bound];
        System.out.print("METHOD:\tSORT\t\tSELECT\n");
        int w1=0,w2=0;
        for (int j = 0;j<10;j++){
            System.out.print((j)+"      \t");
            int k = random.nextInt(bound)+1;
            for (int i = 0;i<bound;i++){
                int t = random.nextInt(bound)+1;
                array[i] = t;
                copy[i] = array[i];
            }
            long s1 = System.nanoTime();
                Arrays.sort(copy);
                int x = copy[k-1];
            long f1 = System.nanoTime();
            long t1 = f1 - s1;
            long s2 = System.nanoTime();
                select(array,bound,k);
            long f2 = System.nanoTime();
            long t2 = f2 - s2;
            if (t1<t2){
                w1++;
            }else {
                w2++;
            }
            System.out.println(t1+"\t"+t2);
        }
        System.out.println("SORT vs SELECT: "+w1+" : "+w2);
//----------------------------------------------------------------
//        int[] time = new int[3];
//        for (int i = 0;i<1000;i++){
//            BASIC = 5;
//            long min = Long.MAX_VALUE;
//            int minIndex = 0;
//            for (int j = 0;j<3;j++){
//                int[] array = new int[100000];
//                for (int t = 0;t<100000;t++){
//                    array[t] = random.nextInt(100000)+1;
//                }
//                long s = System.nanoTime();
//                select(array,100000,random.nextInt(100000)+1);
//                long f = System.nanoTime();
//                long temp = f - s;
//                if (temp<min){
//                    min = temp;
//                    minIndex = j;
//                }
//                BASIC+=2;
//            }
//            time[minIndex]++;
//        }
//        System.out.print("BASIC:\t5\t7\t9\nTIME :\t");
//        for (int i = 0;i<3;i++){
//            System.out.print(time[i]+"\t");
//        }
//        System.out.println();
//------------------------------------------------------------------------------------
//        System.out.println("size\t\tk\t\tans\t\tget\t\tcorrect\t\ttime1\t\ttime2");
//        int count = 0;
//        // 测试次数
//        int total = 10;
//        // size的边界
//        int bound = 100000;
//        for (int i = 0; i<total;i++){
//            int size = random.nextInt(bound)+1;
////            int size = 32;
////            int size = bound;
//            int k = random.nextInt(size)+1;
//            int[] array = new int[size];
//            int[] copy = new int[size];
//            for (int j = 0 ;j<size;j++){
//                array[j] = random.nextInt(100);
//                copy[j] = array[j];
//            }
////            获得标准答案
//            long s1 = System.nanoTime();
//            Arrays.sort(copy);
//            int ans = copy[k-1];
//            long f1 = System.nanoTime();
//
//            long s2 = System.nanoTime();
//            int get = select(array,size,k);
//            long f2 = System.nanoTime();
//            System.out.println(size+"\t\t"+k+"\t\t"+ans+"\t\t"+get+"\t\t"+(ans==get)+"\t\t"+(f1-s1)+"\t\t"+(f2-s2));
//            avg += f2 - s2;
//            if ((f2-s2)-(f1-s1)>0)
//                count++;
//        }
//        System.out.println("system vs my: "+count+":"+(total - count));
//        System.out.println("BASIC at "+BASIC+" cost about "+(avg/total));
    }
    private static int select(int[] array,int n,int k){
        if (n<no){
            Arrays.sort(array,0,n);
            return array[k-1];
        }
//        用来保存筛选后的数组
        int[] p = new int[n];
        int[] r = new int[n];
        int i;
        for (i = 0;i<n/BASIC;i++){
            Arrays.sort(array,i*BASIC,(i+1)*BASIC);
            p[i] = array[BASIC*i+BASIC/2];
        }
        // 通过递归找到中间mid元素
        int m = select(p,i,i/2+i%2);
//        i the size of p which contains all bigger than m
//        j the size of q which contains all equal m ----- q 好像没有用到 就不记录了
//        l the size of r which contains all smaller than m
        i = 0;
        int j=0,l=0;
        for (int t = 0;t<n;t++){
            int x = array[t];
            if (x<m){
                p[i++] = x;
            }else if (x==m){
                j++;
            }else {
                r[l++] = x;
            }
        }
        if (i>=k){
            return select(p,i,k);
        }else if (i+j>=k){
            return m;
        }else {
            return select(r,l,k-i-j);
        }
    }
}
