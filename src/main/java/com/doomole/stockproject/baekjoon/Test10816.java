package com.doomole.stockproject.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Test10816 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        HashMap<Integer, Integer> cardMap = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if(cardMap.get(num) == null) {
                cardMap.put(num, 1);
            } else {
                int count = cardMap.get(num);
                cardMap.put(num, count + 1);
            }
        }

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < M; i++) {
            int num = Integer.parseInt(st.nextToken());
            if(cardMap.get(num) == null) {
                sb.append(0 + " ");
            }else {
                sb.append(cardMap.get(num) + " ");
            }
        }

        System.out.println(sb);
    }
}
