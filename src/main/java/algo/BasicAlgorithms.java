package algo;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: yevgen
 * Date: 3/22/14
 * Time: 8:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class BasicAlgorithms {
    interface ISort{
        void sort(int[] arr);
    }

    static class InsertionSort implements ISort{
        public void sort(int[] arr){
            for(int i = 1; i < arr.length; i++){
                int j = i - 1;
                int lead = arr[i];
                while(j >= 0 && arr[j] > lead){
                    arr[j+1] = arr[j];
                    j--;
                }
                arr[j+1] = lead;
            }

        }
    }

    static class SelectionSort implements ISort{
        public void sort(int[] arr){
            for(int i = 0; i < arr.length - 1; i++){
                int min_idx = i;
                for(int j = i+1; j < arr.length; j++){
                    if (arr[min_idx] > arr[j])
                        min_idx = j;
                }
                swap(arr, min_idx, i);
            }
        }
        void swap(int[] arr, int i, int j){
            int t = arr[i];
            arr[i] = arr[j];
            arr[j] = t;
        }
    }

    static class QuickSort implements ISort{
        public void sort(int[] arr){
            if (arr.length == 0)
                return;
            sort(arr, 0, arr.length);
        }
        private void sort(int[] arr, int s, int e){
            if (e == s+1)
                return;
            int p = partition(arr, s, e);
            sort(arr, s, p+1);
            sort(arr, p+1, e);
        }

        int partition(int[] arr, int s, int e){
            int i = s-1;
            int j = e;
            int lead = arr[s];
            while(true){
                do{
                    i++;
                }while(arr[i] < lead);
                do{
                    j--;
                }while(arr[j] > lead);
                if (j <= i){
                    return j;
                }else{
                    swap(arr, i, j);
                }
            }
        }

        void swap(int[] arr, int i, int j){
            int t = arr[i];
            arr[i] = arr[j];
            arr[j] = t;
        }

    }

    static class BubbleSort implements ISort{
        public void sort(int[] arr){
            while(true){
                boolean found = false;
                for(int i = 0; i < arr.length -1; i++){
                    if (arr[i] > arr[i+1]){
                        swap(arr, i, i+1);
                        found = true;
                    }
                }

                if (!found)
                     break;
            }
        }
        void swap(int[] arr, int i, int j){
            int t = arr[i]; arr[i] = arr[j]; arr[j] = t;
        }
    }

    static class MergeSort implements ISort{
        public void sort(int[] arr){
            if (arr.length <= 1)
                return;
            int[] buf = new int[arr.length];
            sort(arr, 0, arr.length, buf);
        }

        private void sort(int[] arr, int s, int e, int[] buf){
            if (e == s+1)
                return;

            int m = (s + e) / 2;
            sort(arr, s, m, buf);
            sort(arr, m, e, buf);
            merge(arr, s, m, e, buf);
        }

        private void merge(int[] arr, int s, int e1, int e2, int[] buf){
            int p = s;
            int q = e1;
            int b = s;
            while(p < e1 && q < e2){
                if (arr[p] <= arr[q]){
                    buf[b++] = arr[p++];
                }else{
                    buf[b++] = arr[q++];
                }
            }
            while(p < e1)
                buf[b++] = arr[p++];
            while(q < e2)
                buf[b++] = arr[q++];

            for(int i = s; i < e2; i++)
                arr[i] = buf[i];
        }

    }

    static class Selection{
        Random rnd = new Random();

        public int smallest_k_simple(int[] arr, int k){
            if (k >= arr.length)
                return Integer.MIN_VALUE;
            Arrays.sort(arr);
            return arr[k];
        }

        //! k=0 corresponds to the smallest element
        public int smallest_k(int[] arr, int k){
            if (k >= arr.length)
                return Integer.MIN_VALUE;
            return smallest_k(arr, 0, arr.length, k);
        }

        private int smallest_k(int[] arr, int s, int e, int k){
            if (k+1 == e){//return max
                int max = arr[s];
                for(int i = s+1; i < e; i++)
                    max = Math.max(max, arr[i]);
                return max;
            }
            swap(arr, s, s + ((rnd.nextInt() & Integer.MAX_VALUE) % (e - s)));
            int lead = arr[s];
            int p = partition(arr, s, e);
            if (p >= k)
                return smallest_k(arr, s, p+1, k);
            else
                return smallest_k(arr, p + 1, e, k);
        }

        private int partition(int[] arr, int s, int e){

            int i = s - 1;
            int j = e;

            int lead = arr[s];
            while(true){
                do{
                    i++;
                }while(arr[i] < lead);
                do{
                    j--;
                }while(arr[j] > lead);
                if (j <= i){
                    return j;
                }else{
                    swap(arr, i, j);
                }
            }
        }

        private void swap(int[] arr, int i, int j){
            int t = arr[i]; arr[i] = arr[j]; arr[j] = t;
        }

        ///
        ///
        ///http://rosettacode.org/wiki/Quickselect_algorithm
        ///
        ///

        //! k=0 corresponds to the smallest element
        public int smallest_k2(int[] arr, int k){
            if (k >= arr.length)
                return Integer.MIN_VALUE;

            int left = 0;
            int right = arr.length - 1;

            while (right >= left) {
                int pivotIndex = partition2(arr, left, right, rnd.nextInt(right - left + 1) + left);
                if (pivotIndex == k) {
                    return arr[pivotIndex];
                } else if (pivotIndex < k) {
                    left = pivotIndex + 1;
                } else {
                    right = pivotIndex - 1;
                }
            }
            return Integer.MIN_VALUE;
        }

        int partition2(int[] arr, int left, int right, int pivot) {
            int pivotVal = arr[pivot];
            swap(arr, pivot, right);
            int storeIndex = left;
            for (int i = left; i < right; i++) {
                if (arr[i] < pivotVal) {
                    swap(arr, i, storeIndex);
                    storeIndex++;
                }
            }
            swap(arr, right, storeIndex);
            return storeIndex;
        }


    }

}
