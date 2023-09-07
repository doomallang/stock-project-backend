package com.doomole.stockproject.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Test1620 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashMap<String, Integer> pokemonMap = new HashMap<>();
        String[] pokemonArr = new String[N+1];

        for(int i = 1; i <= N; i++) {
            String pokemon = br.readLine();
            pokemonMap.put(pokemon, i);
            pokemonArr[i] = pokemon;
        }

        for(int i = 0; i < M; i++) {
            String answer = br.readLine();
            if(isNum(answer)) {
                System.out.println(pokemonArr[Integer.parseInt(answer)]);
            } else {
                System.out.println(pokemonMap.get(answer));
            }
        }
    }

    public static boolean isNum(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
