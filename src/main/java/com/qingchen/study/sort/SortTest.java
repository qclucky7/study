package com.qingchen.study.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @ClassName SortTest
 * @description:
 * @author: WangChen
 * @create: 2020-05-09 19:48
 **/
public class SortTest {

    @Test
    public void myTest(){

        int[] array = {10,1,9,8,4,5,2};

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i -1 ; j++) {
                if (array[j] > array[j + 1]){
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }

        System.out.println(Arrays.toString(array));


        quickSort(array, 0, array.length -1);
        System.out.println(Arrays.toString(array));
    }

    public static void quickSort(int[] arr,int low,int high){
        int i,j,temp,t;
        if(low>high){
            return;
        }
        i=low;
        j=high;
        //temp就是基准位
        temp = arr[low];

        while (i<j) {
            //先看右边，依次往左递减
            while (temp<=arr[j]&&i<j) {
                j--;
            }
            //再看左边，依次往右递增
            while (temp>=arr[i]&&i<j) {
                i++;
            }
            //如果满足条件则交换
            if (i<j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }

        }
        //最后将基准为与i和j相等位置的数字交换
        arr[low] = arr[i];
        arr[i] = temp;
        //递归调用左半数组
        quickSort(arr, low, j-1);
        //递归调用右半数组
        quickSort(arr, j+1, high);
    }
}
