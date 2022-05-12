package boj_3190;

import java.util.*;

class Main {
    static int n;
    static int[][] board;
    static Map<Integer, String> moveInfo;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int d = 1;
    static int time;

    public void BFS() {
        Queue<Integer> Q = new LinkedList<>();
        Q.offer(0);
        int x = 0;
        int y = 0;

        while (true) {
            // 뱀이 한 번 이동
            int nx = x + dx[d];
            int ny = y + dy[d];
            time++;

            // 벽에 부딪치는 경우
            if (0 > nx || nx >= n || 0 > ny || ny >= n) {
                return;
            }
            // 자신의 몸에 부딪치는 경우
            if (Q.contains(nx * n + ny)) {
                return;
            }

            // 이동한 곳에 사과가 있는 경우
            if (board[nx][ny] == 1) {
                board[nx][ny] = 0;
                Q.offer(nx * n + ny);
            } else {
                Q.offer(nx * n + ny);
                // Q는 뱀의 몸이라고 생각하면 된다
                // 즉 board에 뱀의 몸이 차지하는 공간
                // 한 칸 이동하면 이동한 칸이 뱀의 몸이 차지하고
                // 이동 전에 칸은 뱀의 몸이 이제 아니어서 poll()을 한 것이다
                Q.poll();
            }

            // 방향이 바뀌는 경우
            // 오른쪽인 경우
            if (moveInfo.get(time) != null) {
                String str = moveInfo.get(time);
                if (str.equals("D")) {
                    d += 1;
                    if (d == 4) {
                        d = 0;
                    }
                }
                if (str.equals("L")) {
                    d -= 1;
                    if (d == -1) {
                        d = 3;
                    }
                }
            }
            x = nx;
            y = ny;
        }
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt();
        int k = kb.nextInt();

        board = new int[n][n];
        for (int i = 0; i < k; i++) {
            int a = kb.nextInt() - 1;
            int b = kb.nextInt() - 1;
            board[a][b] = 1;
        }

        int l = kb.nextInt();
        moveInfo = new HashMap<>();
        for (int i = 0; i < l; i++) {
            int x = kb.nextInt();
            String c = kb.next();
            moveInfo.put(x, c);
        }

        T.BFS();
        System.out.println(time);
    }
}