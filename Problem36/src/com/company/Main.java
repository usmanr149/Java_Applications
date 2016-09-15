package com.company;

import java.math.BigInteger;

public class Main {

    public static void main(String args[]) {

        int limit = 100000, sum = 0;

        for(int j = 1; j<limit; j+=2){
            BigInteger k = BigInteger.valueOf(j);
            if(CheckPalindrome(k)){
                BigInteger bina = binary(j);
                 if(CheckPalindrome(bina) == true) {
                     sum += j;
                 }
                }
        }
        System.out.println("sum = " + sum);
    }

    public static boolean CheckPalindrome(BigInteger i){
        BigInteger j = BigInteger.valueOf(0), remainder;
        BigInteger hold = i;

        // compare bi1 with bi2
        while(!( i.equals(BigInteger.valueOf(0) ) ) ){
            // perform remainder operation on bi1 using bi2
            remainder = i.remainder(BigInteger.valueOf(10));
            // multiply bi1 with bi2 and assign result to bi3
            j = j.multiply(BigInteger.valueOf(10));
            // perform add operation on bi1 using bi2
            j = j.add(remainder);
            // divide bi1 with bi2
            i = i.divide(BigInteger.valueOf(10));
        }

        if(j == hold) return true;
        else return false;
    }

    public static BigInteger binary(int i){
        BigInteger bina = BigInteger.valueOf(0);
        BigInteger bina2 = BigInteger.valueOf(0);
        BigInteger ten = BigInteger.valueOf(10);
        BigInteger two = BigInteger.valueOf(2);

        int n;

        double M = 0.30102999566;
        while(i > 0){
             // 4, 2
            n = (int) (Math.log10(i)/M);
            bina = ten.pow(n);  // 10000, 10100
            bina2 = bina2.add(bina);
            i = (int)(i - Math.pow(2, n)); // 7, 2
        }
        System.out.println("binary = " + bina);
        return bina;
    }

}