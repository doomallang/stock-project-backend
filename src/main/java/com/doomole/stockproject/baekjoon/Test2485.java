package com.doomole.stockproject.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Test2485 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);
        int gcd = arr[1] - arr[0];
        for(int i = 1; i < N - 1; i++) {
            int distance = arr[i+1] - arr[i];
            gcd = getGCD(distance, gcd);
        }
        System.out.println(((arr[arr.length-1] - arr[0]) / gcd) + 1 - N);
    }

    public static int getGCD(int num1, int num2) {
        if(num1 % num2 == 0) {
            return num2;
        }
        return getGCD(num2, num1%num2);
    }
}
