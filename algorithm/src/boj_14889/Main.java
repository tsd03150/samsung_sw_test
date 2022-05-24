package boj_14889;

import java.util.ArrayList;
import java.util.Scanner;

class Main {
    static int n;
    static int[][] board;
    static int[] ch;
    static int min = Integer.MAX_VALUE;

    public void DFS(int L, int s) {
        if (L == n / 2) {
            ArrayList<Integer> team1 = new ArrayList<>();
            ArrayList<Integer> team2 = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                if (ch[i] == 1) {
                    team1.add(i);
                } else {
                    team2.add(i);
                }
            }

            int sum1 = 0;
            int sum2 = 0;
            for (int i = 0; i < team1.size(); i++) {
                for (int j = i + 1; j < team1.size(); j++) {
                    sum1 += board[team1.get(i)][team1.get(j)] + board[team1.get(j)][team1.get(i)];
                    sum2 += board[team2.get(i)][team2.get(j)] + board[team2.get(j)][team2.get(i)];
                }
            }
            min = Math.min(min, Math.abs(sum1 - sum2));

        } else {
            for (int i = s; i < n; i++) {
                ch[i] = 1;
                DFS(L + 1, i + 1);
                ch[i] = 0;
            }
        }
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt();
        board = new int[n][n];
        ch = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = kb.nextInt();
            }
        }
        T.DFS(0, 0);
        System.out.println(min);
    }
}