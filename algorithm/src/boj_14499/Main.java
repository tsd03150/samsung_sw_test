package boj_14499;

import java.util.Scanner;

class Main {
    static int n;
    static int m;
    static int k;
    static int[][] board;
    static int[] dice;
    static int[] arr;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static StringBuilder answer = new StringBuilder();

    public void move(int x, int y) {
        for (int i = 0; i < k; i++) {
            int d = arr[i] - 1;
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (0 > nx || nx >= n || 0 > ny || ny >= m) {
                continue;
            }

            roll(d);
            if (board[nx][ny] == 0) {
                board[nx][ny] = dice[3];
            } else {
                dice[3] = board[nx][ny];
                board[nx][ny] = 0;
            }
            x = nx;
            y = ny;
            answer.append(dice[6] + "\n");
        }
    }

    public void roll(int d) {
        int tmp = dice[3];
        switch (d) {
            case 0:
                dice[3] = dice[4];
                dice[4] = dice[6];
                dice[6] = dice[2];
                dice[2] = tmp;
                break;
            case 1:
                dice[3] = dice[2];
                dice[2] = dice[6];
                dice[6] = dice[4];
                dice[4] = tmp;
                break;
            case 2:
                dice[3] = dice[1];
                dice[1] = dice[6];
                dice[6] = dice[5];
                dice[5] = tmp;
                break;
            case 3:
                dice[3] = dice[5];
                dice[5] = dice[6];
                dice[6] = dice[1];
                dice[1] = tmp;
                break;
        }
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt();
        m = kb.nextInt();
        int x = kb.nextInt();
        int y = kb.nextInt();
        k = kb.nextInt();
        dice = new int[7];

        board = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = kb.nextInt();
            }
        }
        arr = new int[k];
        for (int i = 0; i < k; i++) {
            arr[i] = kb.nextInt();
        }
        T.move(x, y);
        System.out.println(answer);
    }
}