package boj_14888;

import java.util.ArrayList;
import java.util.Scanner;

class Main {
    static int n;
    static long[] arr;
    static int[] ch;
    static ArrayList<Integer> sign;
    static long max = -1000000000;
    static long min = 1000000000;

    public void DFS(int L, long sum) {
        if (L == n) {
            max = Math.max(max, sum);
            min = Math.min(min, sum);
        } else {
            for (int i = 0; i < n - 1; i++) {
                if (ch[i] == 0) {
                    ch[i] = 1;
                    switch (sign.get(i)) {
                        case 0:
                            DFS(L + 1, sum + arr[L]);
                            break;
                        case 1:
                            DFS(L + 1, sum - arr[L]);
                            break;
                        case 2:
                            DFS(L + 1, sum * arr[L]);
                            break;
                        case 3:
                            if (sum < 0) {
                                DFS(L + 1, Math.abs(sum) / arr[L] * -1);
                            } else {
                                DFS(L + 1, sum / arr[L]);
                            }
                    }
                    ch[i] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt();
        arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = kb.nextInt();
        }

        sign = new ArrayList<>();
        ch = new int[n - 1];
        for (int i = 0; i < 4; i++) {
            int num = kb.nextInt();
            for (int j = 0; j < num; j++) {
                sign.add(i);
            }
        }
        T.DFS(1, arr[0]);
        System.out.println(max);
        System.out.println(min);
    }
}