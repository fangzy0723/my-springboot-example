package com.example.controller;

import org.junit.Test;

/**
 * 查找算法
 */
public class TestChaZhao {

    /**
     * 二分查找算法
     * 需要查找的数组必须是有序的
     */
    @Test
    public void testErFenChaZhao(){
        //需要查找的数
        int num = 10;
        //查找数的数组
        int[] arr = {1,3,5,6,7,9,10,13,15,16,17};
        //二分查找开始位置
        int s = 0;
        //二分查找结束位置
        int e = arr.length;
        //二分查找中间位置
        int m;
        //循环的条件是开始位置小于等于结束位置，防止找不到的情况
        while (s <= e){
            //根据开始位置和结束位置计算出中间位置
            m = (s+e)/2;
            if(arr[m]==num){//需要找的值等于中间位置
                System.out.println("找到了，下标："+m);
                return;
            }else if(arr[m]>num){//需要找的值小于中间位置的
                //再次查找开始位置不变，结束位置是中间位置-1
                e = m-1;
            }else if(arr[m]<num){//需要找的值大于中间位置的
                //结束位置不变，开始位置是中间位置+1
                s = m + 1;
            }
        }
        System.out.println("数组中不存在");
    }
}
