package boj_14891;

import java.util.*;

class Main {
    static int[][] board;

    public void solution(int num, int d) {
        left(num - 1, -d);
        right(num + 1, -d);
        rotate(num, d);
    }

    public void left(int num, int d) {
        if (num < 0) {
            return;
        }
        if (board[num][2] != board[num + 1][6]) {
            left(num - 1, -d);
            rotate(num, d);
        }
    }

    public void right(int num, int d) {
        if (num > 3) {
            return;
        }
        if (board[num][6] != board[num - 1][2]) {
            right(num + 1, -d);
            rotate(num, d);
        }
    }

    public void rotate(int num, int d) {
        if (d == -1) {
            int tmp = board[num][0];
            for (int i = 1; i < 8; i++) {
                board[num][i - 1] = board[num][i];
            }
            board[num][7] = tmp;
        } else {
            int tmp = board[num][7];
            for (int i = 6; i >= 0; i--) {
                board[num][i + 1] = board[num][i];
            }
            board[num][0] = tmp;
        }
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        board = new int[4][8];
        for (int i = 0; i < 4; i++) {
            String str = kb.next();
            for (int j = 0; j < 8; j++) {
                board[i][j] = str.charAt(j) - '0';
            }
        }

        int k = kb.nextInt();
        for (int i = 0; i < k; i++) {
            int num = kb.nextInt() - 1;
            int d = kb.nextInt();
            T.solution(num, d);
        }

        int answer = 0;
        for (int i = 0; i < 4; i++) {
            if (board[i][0] == 1) {
                answer += Math.pow(2, i);
            }
        }
        System.out.println(answer);
    }
}