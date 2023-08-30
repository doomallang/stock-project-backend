package com.doomole.stockproject.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Test11651 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int n = Integer.parseInt(br.readLine());
        Integer[][] arr = new Integer[n][2];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                if (o1[1].equals(o2[1])) {
                    return o1[0] - o2[0];
                } else {
                    return o1[1] - o2[1];
                }
            }
        });

        for(int i = 0; i < n; i++) {
            System.out.println(arr[i][0] + " " + arr[i][1]);
        }
    }
}
