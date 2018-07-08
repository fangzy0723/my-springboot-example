package com.example.controller;

import org.junit.Test;

/**
 * 阶乘算法
 */
public class TestJieCheng {

    /**
     * 小数阶乘
     */
    @Test
    public void testJieCheng1(){
        //计算5的阶乘
        int num = 5;
        int val = 1;
        for (int i=1;i<=num;i++){
            val*=i;
        }
        System.out.println(num+"的阶乘值："+val);
    }
    /**
     * 大数阶乘
     * 把每个数当作数组的元素，这样适合大数字相乘，int、long存储的数字个数有限
     * 1、先把数组中的每个元素都*num
     * 2、从后往前只留各位其他位数向前进
     */
    @Test
    public void testJieCheng2(){
        //计算137*10
        int[] arr = {1,3,7};

        arr = testJieCheng2(arr,7);
        for (int i=0;i<arr.length;i++){
            System.out.print(arr[i]);
        }

        System.out.println("///////////////////////////////");

        //计算5的阶乘
        int num = 50;
        int[] arr1 = new int[100];
        arr1[arr1.length-1] = 1;
        for(int i=1;i<=num;i++){
            arr1 = testJieCheng2(arr1,i);
        }
        String aaa = "";
        for (int i=0;i<=arr1.length-1;i++){
            aaa += arr1[i];
        }
        aaa = aaa.replaceFirst("^0*", "");
        System.out.println(num+"的阶乘："+aaa);
    }

    /**
     * 1、先把数组中的每个元素都*num
     * 2、从后往前只留各位其他位数向前进
     * 得出当前数组*某一值之后的数组
     * @param arr
     * @param num
     * @return
     */
    public int[] testJieCheng2(int[] arr,int num){
        for (int i=arr.length-1;i>=0;i--){
            arr[i]*=num;
        }

        for (int i=arr.length-1;i>0;i--){
            int val = arr[i];
            arr[i] = val%10;
            arr[i-1] += val/10;
        }

        return arr;
    }
}
