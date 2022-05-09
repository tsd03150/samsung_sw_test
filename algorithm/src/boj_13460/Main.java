package boj_13460;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Bead {
    int rx;
    int ry;
    int bx;
    int by;
    int cnt;

    Bead(int rx, int ry, int bx, int by, int cnt) {
        this.rx = rx;
        this.ry = ry;
        this.bx = bx;
        this.by = by;
        this.cnt = cnt;
    }
}

class Main {
    static int n;
    static int m;
    static int[][] board;
    static int[][][][] ch;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int answer = Integer.MAX_VALUE;

    public void BFS(int rx, int ry, int bx, int by, int cnt) {
        Queue<Bead> Q = new LinkedList<>();
        Q.offer(new Bead(rx, ry, bx, by, cnt));
        ch[rx][ry][bx][by] = 1;

        while (!Q.isEmpty()) {
            Bead ob = Q.poll();
            if (ob.cnt >= 10) {
                return;
            }
            for (int i = 0; i < 4; i++) {
                int nRx = ob.rx;
                int nRy = ob.ry;
                int nBx = ob.bx;
                int nBy = ob.by;

                // 빨간 구슬 이동
                while (board[nRx + dx[i]][nRy + dy[i]] != 0) {
                    nRx += dx[i];
                    nRy += dy[i];
                    if (board[nRx][nRy] == 44) {
                        break;
                    }
                }

                // 파란 구슬 이동
                while (board[nBx + dx[i]][nBy + dy[i]] != 0) {
                    nBx += dx[i];
                    nBy += dy[i];
                    if (board[nBx][nBy] == 44) {
                        break;
                    }
                }

                // 파랑 구슬이 구멍에 들어갔을 때
                if (board[nBx][nBy] == 44) {
                    continue;
                }

                // 빨간 구슬이 구멍에 들어갔을 때
                if (board[nRx][nRy] == 44) {
                    answer = Math.min(answer, ob.cnt + 1);
                    return;
                }

                // 빨강 파랑이 서로 만났을 때
                if (nRx == nBx && nRy == nBy && board[nRx][nRy] != 44) {
                    int red_move = Math.abs(nRx - ob.rx) + Math.abs(nRy - ob.ry);
                    int blue_move = Math.abs(nBx - ob.bx) + Math.abs(nBy - ob.by);

                    // 파란 공이 더 빨리 도착하는 경우
                    if (red_move > blue_move) {
                        nRx -= dx[i];
                        nRy -= dy[i];
                    } else { // 빨간 공이 더 빨리 도착하는 경우
                        nBx -= dx[i];
                        nBy -= dy[i];
                    }
                }

                // 이동한 위치 조건 검사 후 다시 Q에 넣기
                if (ch[nRx][nRy][nBx][nBy] == 0) {
                    ch[nRx][nRy][nBx][nBy] = 1;
                    Q.offer(new Bead(nRx, nRy, nBx, nBy, ob.cnt + 1));
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
        ch = new int[n][m][n][m];

        int rx = 0;
        int ry = 0;
        int bx = 0;
        int by = 0;
        for (int i = 0; i < n; i++) {
            String str = kb.next();
            for (int j = 0; j < m; j++) {
                // R=47, B=31, O=44, #=0, .=11
                board[i][j] = str.charAt(j) - '0' + 13;
                if (board[i][j] == 47) {
                    rx = i;
                    ry = j;
                } else if (board[i][j] == 31) {
                    bx = i;
                    by = j;
                }
            }
        }
        T.BFS(rx, ry, bx, by, 0);
        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }
}