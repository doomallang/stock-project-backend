package com.doomole.stockproject.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test13241 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        long gcd = getGCD(A, B);
        System.out.println(A * B / gcd);
    }

    public static long getGCD(long num1, long num2) {
        if(num1 % num2 == 0) {
            return num2;
        }
        return getGCD(num2, num1%num2);
    }
}
