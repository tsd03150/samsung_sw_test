package boj_14501;

import java.util.ArrayList;
import java.util.Scanner;

class Advice {
    int t;
    int p;

    Advice(int t, int p) {
        this.t = t;
        this.p = p;
    }
}

class Main {

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        ArrayList<Advice> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int a = kb.nextInt();
            int b = kb.nextInt();
            arr.add(new Advice(a, b));
        }

        int[] dp = new int[n + 2];
        for (int i = 1; i <= n; i++) {
            Advice ob = arr.get(i - 1);
            dp[i] = Math.max(dp[i], dp[i - 1]);

            if (i + ob.t <= n + 1) {
                dp[i + ob.t] = Math.max(dp[i + ob.t], dp[i] + ob.p);
            }
        }
        System.out.println(Math.max(dp[n], dp[n + 1]));
    }
}