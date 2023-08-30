package com.doomole.stockproject.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Test18870 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        int[] arr2 = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = arr2[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr2);
        Map<Integer, Integer> map = new HashMap<>();
        int rank = 0;
        for(int i = 0; i < n; i++) {
            if(!map.containsKey(arr2[i])) {
                map.put(arr2[i], rank);
                rank++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            sb.append(map.get(arr[i]) + " ");
        }
        System.out.println(sb);
    }
}
