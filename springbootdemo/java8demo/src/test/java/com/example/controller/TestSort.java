package com.example.controller;

import org.junit.Test;

/**
 * 插入排序算法
 */
public class TestSort {
    /**
     * 插入排序算法思路1,三层for循环的：
     * 1、从数组的第二位开始，i位置的值先和前一个比较大小，要是大于前一个的话继续下一次循环，
     *    要是小于前一个数字的话，开始第二层for循环。第一层for循环的深度是1~（arr.length-1）
     * 2、第二层for循环的深度是0~（i-1），拿i位置的值跟前面的每一个值（j位置）开始比较大小，
     *    要是i位置的值大于j位置的值，继续下一次第二层for循环，开始位置移动
     * 3、要是i位置的值小于j位置的值，把从i-1位置到j位置的值都向后移动一位
     *    先把i位置的值拿出来放到一个变量中临时存储，把i-1位置的值放到i位置，然后把i-2位置的值放到i-1位置，
     *    直到j的位置的值放到j后一位，然后把临时存储到变量中的原来i位置的值放到j位置
     */
    @Test
    public void estChaRuSort1(){
        int num = 0;
        //定义一个需要排序的数组
        int[] arr = {2,3,5,1,9,8,7,6,4};
        //开始第一层for循环
        for (int i=1;i<arr.length;i++){
            if(arr[i]<arr[i-1]) {
                //满足小于前面位置的数字，进入第二层for循环
                for (int j=0;j<=i-1;j++){
                    if (arr[j]>arr[i]){
                        //i位置的值小于当前循环位置的值，需要把i位置的值插入到j位置
                        //从j~（i-1）位置的值都需要往后移动,移动的顺序是从后往前
                        num = arr[i];
                        for (int k=i-1;k>=j;k--){
                            arr[k+1] = arr[k];
                        }
                        arr[j] = num;
                        break;
                    }
                }
            }
        }
        System.out.print("排序之后的数组：");
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]);
        }
    }
    /**
     * 插入排序算法思路2,两层for循环的：
     * 1、从数组的第二位开始，i位置的值先和前一个比较大小，要是大于前一个的话继续下一次循环，
     *    要是小于前一个数字的话，开始第二层for循环。
     * 2、第二层for循环的深度是i-1~0，倒叙循环，从i位置开始跟前一个位置的值比较大小
     *    如果后一个位置的值小于前一个位置的值时将两个位置的值替换，
     *    如果后一个位置的值大于前一个位置的值就可以推出第二层for循环了，因为第一层for循环到i位置时，i前面的已经是排序之后的顺序了
     */
    @Test
    public void estChaRuSort2(){
        int num = 0;
        //定义一个需要排序的数组
        int[] arr = {2,3,5,1,9,8,7,6,4,5};
        //开始第一层for循环
        for (int i=1;i<arr.length;i++){
            if(arr[i]<arr[i-1]) {
                //满足小于前面位置的数字，进入第二层for循环
                //i位置跟前一个位置比较，如果i位置小于其i-1位置的值，i位置的值跟i-1位置的的值进行交换
                for (int j=i-1;j>=0;j--){
                    if(arr[j]>arr[j+1]){
                        //将j位置的值跟j+1位置的值进行交换
                        num = arr[j+1];
                        arr[j+1] = arr[j];
                        arr[j] = num;
                    }else{
                        break;
                    }
                }
            }
        }
        System.out.print("排序之后的数组：");
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]);
        }
    }
}
