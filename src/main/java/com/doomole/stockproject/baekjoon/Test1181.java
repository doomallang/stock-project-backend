package com.doomole.stockproject.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Test1181 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int n = Integer.parseInt(br.readLine());
        String[] arr = new String[n];

        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine();
        }

        String[] resultArr = Arrays.stream(arr).distinct().toArray(String[]::new);
        Arrays.sort(resultArr, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                if(s1.length() == s2.length()) {
                    return s1.compareTo(s2);
                } else {
                    return s1.length() - s2.length();
                }
            }
        });

        for(int i = 0; i < resultArr.length; i++) {
            System.out.println(resultArr[i]);
        }
    }
}
