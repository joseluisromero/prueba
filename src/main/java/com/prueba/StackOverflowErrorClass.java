package com.prueba;

import java.util.ArrayList;
import java.util.List;

public class StackOverflowErrorClass {
    /*static int i = 0;
    // Method to print numbers
    public static int printNumber(int x)
    {

        i = i + 2;
        System.out.println(i);
        return i + printNumber(i + 2);
    }

    public static void main(String[] args)
    {
        // Recursive call without any
        // terminating condition
        StackOverflowErrorClass.printNumber(i);
    }
    */
    public static void recursivePrint(int num) {
        System.out.println("Number: " + num);

        if(num == 0)
            return;
        else
            recursivePrint(++num);
    }






    public static void main(String[] args) {

        errorMemory();
    }


    private static void errorMemory(){
        int[] i = new int[Integer.MAX_VALUE];
    }
}
