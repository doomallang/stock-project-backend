package com.doomole.stockproject.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Test7785 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int n = Integer.parseInt(br.readLine());
        HashMap<String, Object> memberMap = new HashMap<>();

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            String status = st.nextToken();

            if(memberMap.containsKey(name)) {
                memberMap.remove(name);
            } else {
                memberMap.put(name, status);
            }
        }

        ArrayList<String> memberList = new ArrayList<String>(memberMap.keySet());

        memberList.stream().sorted(Collections.reverseOrder()).forEach(System.out::println);
    }
}
