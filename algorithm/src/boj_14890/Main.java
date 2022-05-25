package boj_14890;

import java.util.Scanner;

class Main {
    static int n;
    static int l;
    static int[][] board;

    // d가 0 이면 열, 1 이면 행
    public boolean solution(int x, int y, int d) {
        int[] cpBoard = new int[n];
        int[] ch = new int[n];
        if (d == 0) {
            for (int i = 0; i < n; i++) {
                cpBoard[i] = board[i][y];
            }
        } else {
            for (int i = 0; i < n; i++) {
                cpBoard[i] = board[x][i];
            }
        }

        // 지나갈 수 있는 길 인지를 체크
        for (int i = 0; i < n - 1; i++) {
            if (cpBoard[i] == cpBoard[i + 1]) {
                continue;
            }
            if (Math.abs(cpBoard[i] - cpBoard[i + 1]) > 1) {
                return false;
            }

            // 내려가는 경우
            if (cpBoard[i] > cpBoard[i + 1]) {
                for (int j = i + 1; j <= i + l; j++) {
                    if (j >= n || cpBoard[j] != cpBoard[i] - 1 || ch[j] == 1) {
                        return false;
                    }
                    ch[j] = 1;
                }
            } else if (cpBoard[i] < cpBoard[i + 1]) { // 올라가는 경우
                for (int j = i; j > i - l; j--) {
                    if (j < 0 || cpBoard[j] != cpBoard[i] || ch[j] == 1) {
                        return false;
                    }
                    ch[j] = 1;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt();
        l = kb.nextInt();
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = kb.nextInt();
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            if (T.solution(0, i, 0)) {
                answer++;
            }
            if (T.solution(i, 0, 1)) {
                answer++;
            }
        }
        System.out.println(answer);
    }
}