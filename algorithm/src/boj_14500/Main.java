package boj_14500;

import java.util.Scanner;

class Main {
    static int n;
    static int m;
    static int[][] board;
    static int[][] ch;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int answer = Integer.MIN_VALUE;

    public void DFS(int L, int x, int y, int sum) {
        if (L == 4) {
            answer = Math.max(answer, sum);
        } else {
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (0 <= nx && nx < n && 0 <= ny && ny < m && ch[nx][ny] == 0) {
                    ch[nx][ny] = 1;
                    DFS(L + 1, nx, ny, sum + board[x][y]);
                    ch[nx][ny] = 0;
                }
            }
        }
    }

    public void solution(int x, int y) {
        if (x + 1 < n && y + 2 < m) {
            answer = Math.max(answer, board[x][y] + board[x][y + 1] + board[x + 1][y + 1] + board[x][y + 2]);
        }
        if (0 <= x - 1 && y + 2 < m) {
            answer = Math.max(answer, board[x][y] + board[x][y + 1] + board[x - 1][y + 1] + board[x][y + 2]);
        }
        if (x + 2 < n && y + 1 < m) {
            answer = Math.max(answer, board[x][y] + board[x + 1][y] + board[x + 2][y] + board[x + 1][y + 1]);
        }
        if (x + 2 < n && 0 <= y - 1) {
            answer = Math.max(answer, board[x][y] + board[x + 1][y] + board[x + 2][y] + board[x + 1][y - 1]);
        }
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt();
        m = kb.nextInt();
        board = new int[n][m];
        ch = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = kb.nextInt();
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ch[i][j] = 1;
                T.DFS(0, i, j, 0);
                T.solution(i, j);
                ch[i][j] = 0;
            }
        }
        System.out.println(answer);
    }
}