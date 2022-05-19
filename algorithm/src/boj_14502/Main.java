package boj_14502;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Graph {
    int x;
    int y;

    Graph(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Main {
    static int n;
    static int m;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int answer = Integer.MIN_VALUE;

    // 벽을 세우는 메서드
    public void DFS(int L, int x, int y, int[][] board) {
        if (L == 3) {
            BFS(x, y, board);
        } else {
            int j = y;
            for (int i = x; i < n; i++) {
                for (; j < m; j++) {
                    if (board[i][j] == 0) {
                        board[i][j] = 1;
                        DFS(L + 1, i, j, board);
                        board[i][j] = 0;
                    }
                }
                j = 0;
            }
        }
    }

    // 바이러스를 퍼뜨리는 메서드
    public void BFS(int x, int y, int[][] board) {
        Queue<Graph> Q = new LinkedList<>();
        int[][] cpBoard = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                cpBoard[i][j] = board[i][j];
                if (cpBoard[i][j] == 2) {
                    Q.offer(new Graph(i, j));
                }
            }
        }

        while (!Q.isEmpty()) {
            Graph ob = Q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = ob.x + dx[i];
                int ny = ob.y + dy[i];
                if (0 <= nx && nx < n && 0 <= ny && ny < m && cpBoard[nx][ny] == 0) {
                    cpBoard[nx][ny] = 2;
                    Q.offer(new Graph(nx, ny));

                }
            }
        }

        int max = 0;
        for (int[] tmp : cpBoard) {
            for (int num : tmp) {
                if (num == 0) {
                    max++;
                }
            }
        }
        answer = Math.max(answer, max);
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt();
        m = kb.nextInt();
        int[][] board = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = kb.nextInt();
            }
        }
        T.DFS(0, 0, 0, board);
        System.out.println(answer);
    }
}