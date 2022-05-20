package boj_14503;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Graph {
    int x;
    int y;
    int d;

    Graph(int x, int y, int d) {
        this.x = x;
        this.y = y;
        this.d = d;
    }
}

class Main {
    static int n;
    static int m;
    static int[][] board;
    static int[][] dp;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};

    public void BFS(int x, int y, int d) {
        Queue<Graph> Q = new LinkedList<>();
        Q.offer(new Graph(x, y, d));

        while (!Q.isEmpty()) {
            Graph ob = Q.poll();
            if (board[ob.x][ob.y] == 0) {
                dp[ob.x][ob.y] = 1;
            }
            boolean flag = false;
            int nd = ob.d;
            for (int i = 0; i < 4; i++) {
                int nx = ob.x + dx[nd];
                int ny = ob.y + dy[nd];
                nd--;
                if (nd == -1) {
                    nd = 3;
                }

                if (0 <= nx && nx < n && 0 <= ny && ny < m && board[nx][ny] == 0 && dp[nx][ny] == 0) {
                    flag = true;
                    Q.offer(new Graph(nx, ny, nd));
                    break;
                }
            }

            if (!flag) {
                int ndd = ob.d - 1;
                if (ndd == -1) {
                    ndd = 3;
                }
                int ndx = ob.x + dx[ndd];
                int ndy = ob.y + dy[ndd];
                if (0 <= ndx && ndx < n && 0 <= ndy && ndy < m && board[ndx][ndy] != 1) {
                    Q.offer(new Graph(ndx, ndy, ob.d));
                }
            }
        }
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt();
        m = kb.nextInt();
        board = new int[n][m];
        dp = new int[n][m];

        int x = kb.nextInt();
        int y = kb.nextInt();
        int d = kb.nextInt();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = kb.nextInt();
            }
        }
        T.BFS(x, y, d);
        int answer = 0;
        for (int[] tmp : dp) {
            for (int num : tmp) {
                if (num == 1) {
                    answer++;
                }
            }
        }
        System.out.println(answer);
    }
}