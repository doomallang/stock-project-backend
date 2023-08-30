package com.doomole.stockproject.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Test14425 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Set<String> set = new HashSet<>();
        for(int i = 0; i < n; i++) {
            set.add(br.readLine());
        }

        int count = 0;
        for(int i = 0; i < m; i++) {
            String s = br.readLine();
            if(set.contains(s)) {
                System.out.println(s);
                count++;
            }
        }
        System.out.println(count);
    }
}
