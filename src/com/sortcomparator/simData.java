/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sortcomparator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author WANDE
 */
class simData {

    int[] generate(int dataSize) {
        int[] myArr = new int[dataSize];
        for (int i = 0; i < dataSize; i++) {
            Random r = new Random();
            myArr[i] = r.nextInt(999) + 1;
        }

        return myArr;
    }

    boolean writeFile(String filename, int[] dataArray) {
        filename += ".txt";
        boolean success = false;
        FileWriter myDataFile = null;
        try {
            myDataFile = new FileWriter(new File(filename));
            BufferedWriter mydataWrite = new BufferedWriter(myDataFile);
            for (int j = 0; j < dataArray.length; j++) {
                mydataWrite.write(dataArray[j] + ",");
            }
            success = true;
            mydataWrite.close();
        } catch (IOException ex) {
            success = false;
        }
        return success;
    }
    
    void writeFile(String filename, String content) {
        filename += ".txt";
        FileWriter myDataFile = null;
        try {
            myDataFile = new FileWriter(new File(filename));
            BufferedWriter mydataWrite = new BufferedWriter(myDataFile);
            mydataWrite.write(content);
            mydataWrite.close();
        } catch (IOException ex) {
            
        }
    }

}
