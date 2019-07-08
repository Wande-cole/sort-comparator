/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sortcomparator;

import java.util.Arrays;
import java.util.Calendar;

/**
 *
 * @author WANDE
 */
public class Sorter {

    public Sorter() {

    }

    public int[] selection(int[] numericData) {
        int n = numericData.length;
        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in unsorted array 
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {
                if (numericData[j] < numericData[min_idx]) {
                    min_idx = j;
                }
            }
            // Swap the found minimum element with the first element 
            int temp = numericData[min_idx];
            numericData[min_idx] = numericData[i];
            numericData[i] = temp;
        }
        return numericData;
    }

    public int[] bubble(int[] numericData) {
        int n = numericData.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (numericData[j] > numericData[j + 1]) {
                    // swap temp and arr[i] 
                    int temp = numericData[j];
                    numericData[j] = numericData[j + 1];
                    numericData[j + 1] = temp;
                }
            }
        }
        return numericData;
    }

    public int[] insertion(int[] numericData) {
        int n = numericData.length;
        for (int i = 1; i < n; ++i) {
            int key = numericData[i];
            int j = i - 1;
            while (j >= 0 && numericData[j] > key) {
                numericData[j + 1] = numericData[j];
                j = j - 1;
            }
            numericData[j + 1] = key;
        }
        return numericData;
    }

    public int[] heap(int[] numericData) {
        int n = numericData.length;

        // Build heap (rearrange array) 
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(numericData, n, i);
        }

        // One by one extract an element from heap 
        for (int i = n - 1; i >= 0; i--) {
            // Move current root to end 
            int temp = numericData[0];
            numericData[0] = numericData[i];
            numericData[i] = temp;

            // call max heapify on the reduced heap 
            heapify(numericData, i, 0);
        }
        return numericData;
    }

    public int[] pancake(int[] numericData) {
        int n = numericData.length;
        for (int curr_size = n; curr_size > 1; --curr_size) {
            int mi = findMax(numericData, curr_size);
            if (mi != curr_size - 1) {
                flip(numericData, mi);
                flip(numericData, curr_size - 1);
            }
        }
        return numericData;
    }

    public int[] shell(int[] numericData) {
        int n = numericData.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i += 1) {
                int temp = numericData[i];
                int j;
                for (j = i; j >= gap && numericData[j - gap] > temp; j -= gap) {
                    numericData[j] = numericData[j - gap];
                }
                numericData[j] = temp;
            }
        }
        return numericData;
    }

    public int[] cocktail(int[] numericData) {
        boolean swapped = true;
        int start = 0;
        int end = numericData.length;
        while (swapped == true) {
            swapped = false;
            for (int i = start; i < end - 1; ++i) {
                if (numericData[i] > numericData[i + 1]) {
                    int temp = numericData[i];
                    numericData[i] = numericData[i + 1];
                    numericData[i + 1] = temp;
                    swapped = true;
                }
            }
            if (swapped == false) {
                break;
            }
            swapped = false;
            end = end - 1;
            for (int i = end - 1; i >= start; i--) {
                if (numericData[i] > numericData[i + 1]) {
                    int temp = numericData[i];
                    numericData[i] = numericData[i + 1];
                    numericData[i + 1] = temp;
                    swapped = true;
                }
            }
            start = start + 1;
        }
        return numericData;
    }

    public int[] gnome(int[] numericData) {
        int n = numericData.length;
        int index = 0;
        while (index < n) {
            if (index == 0) {
                index++;
            }
            if (numericData[index] >= numericData[index - 1]) {
                index++;
            } else {
                int temp = 0;
                temp = numericData[index];
                numericData[index] = numericData[index - 1];
                numericData[index - 1] = temp;
                index--;
            }
        }
        return numericData;
    }

    public String getTime() {
        Calendar cal = Calendar.getInstance();
        int currHour = cal.get(Calendar.HOUR_OF_DAY);
        int currMin = cal.get(Calendar.MINUTE);
        int currSec = cal.get(Calendar.SECOND);
        int currMilli = cal.get(Calendar.MILLISECOND);
        String currentTime = currHour + ":" + currMin + ":" + currSec + ":" + currMilli;
        return currentTime;
    }

    public String getTimeString() {
        Calendar cal = Calendar.getInstance();
        int currHour = cal.get(Calendar.HOUR_OF_DAY);
        int currMin = cal.get(Calendar.MINUTE);
        int currSec = cal.get(Calendar.SECOND);
        int currMilli = cal.get(Calendar.MILLISECOND);
        String currentTime = currHour + "" + currMin + "" + currSec + "" + currMilli;
        return currentTime;
    }

    private void heapify(int arr[], int n, int i) {
        int largest = i; // Initialize largest as root 
        int l = 2 * i + 1; // left = 2*i + 1 
        int r = 2 * i + 2; // right = 2*i + 2 

        // If left child is larger than root 
        if (l < n && arr[l] > arr[largest]) {
            largest = l;
        }

        // If right child is larger than largest so far 
        if (r < n && arr[r] > arr[largest]) {
            largest = r;
        }

        // If largest is not root 
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree 
            heapify(arr, n, largest);
        }
    }

    private int findMax(int arr[], int n) {
        int mi, i;
        for (mi = 0, i = 0; i < n; ++i) {
            if (arr[i] > arr[mi]) {
                mi = i;
            }
        }
        return mi;
    }

    private void flip(int arr[], int i) {
        int temp, start = 0;
        while (start < i) {
            temp = arr[start];
            arr[start] = arr[i];
            arr[i] = temp;
            start++;
            i--;
        }
    }

}
