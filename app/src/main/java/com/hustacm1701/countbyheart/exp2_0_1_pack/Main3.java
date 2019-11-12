package exp2_0_1_pack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main3 {
    private static ArrayList<ArrayList<Integer>>[] lists;
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("E:\\Java\\AlgorithmExperiment\\src\\exp2_0_1_pack\\3.mem"));
        int n = scanner.nextInt();
        int target = scanner.nextInt();
        int[] nums = new int[n];
        lists = new ArrayList[target+1];
        // 输入数组 和初始化数据
        for (int i = 0;i<n;i++){
            nums[i] = scanner.nextInt();
        }
        for (int i = 0;i<=target;i++){
            lists[i] = new ArrayList<>();
        }
        int ans = dp(nums,target);
        System.out.println("共："+ans+"种情况");
        for (ArrayList<Integer> list:lists[target]){
            System.out.println(list);
        }
    }
    private static int dp(int[] nums,int target){
        if (nums == null)
            return 0;
        int[] save = new int[target+1];
        // 当为0的情况时  都不选 就可以了
        save[0] = 1;
        lists[0].add(new ArrayList<>());
        for (int i = 1;i<=target;i++){
            for (int num:nums){
                if (i>=num){
                    // 如果target比当前的数字大，说明当前数字可能构成target
                    save[i] += save[i-num];
                    // 记录组合情况
                    for (ArrayList<Integer> list:lists[i-num]){
                        ArrayList<Integer> temp = new ArrayList<>(list);
                        temp.add(num);
                        lists[i].add(temp);
                    }
                }
            }
            for (int j = 0;j<=target;j++){
                System.out.print(save[j]+" ");
            }
            System.out.println();
        }
        return save[target];
    }
}
