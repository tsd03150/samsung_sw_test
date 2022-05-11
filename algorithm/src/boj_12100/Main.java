package boj_12100;

import java.util.Scanner;

class Main {
    static int n;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int answer = Integer.MIN_VALUE;

    public void DFS(int L, int end, int[][] board) {
        // 5번의 이동이 끝난 경우
        if (L == end) {
            for (int[] tmp : board) {
                for (int num : tmp) {
                    answer = Math.max(answer, num);
                }
            }
            return;
        }

        // 0:위, 1:아래, 2:왼쪽, 3:오른쪽 이동
        for (int i = 0; i < 4; i++) {
            // 한 번의 이동에서 합쳐졌는 지를 검사하는 배열
            int[][] ch = new int[n][n];
            // board 복사
            int[][] tmpBoard = new int[n][n];
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    tmpBoard[j][k] = board[j][k];
                }
            }
            // 위, 왼쪽 이동의 경우
            if (i == 0 || i == 2) {
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < n; k++) {
                        if (tmpBoard[j][k] > 0) {
                            move(tmpBoard, ch, i, j, k);
                        }
                    }
                }
            }
            // 아래, 오른쪽 이동의 경우
            if (i == 1 || i == 3) {
                for (int j = n - 1; j >= 0; j--) {
                    for (int k = n - 1; k >= 0; k--) {
                        if (tmpBoard[j][k] > 0) {
                            move(tmpBoard, ch, i, j, k);
                        }
                    }
                }
            }
            // 5번 이동할 때 까지 계속 재귀 호출
            DFS(L + 1, end, tmpBoard);
        }
    }

    // i=이동방향, j=x축, k=y축
    public void move(int[][] board, int[][] ch, int i, int j, int k) {
        // 현재 위치
        int nowX = j;
        int nowY = k;
        // 해당 방향으로 이동
        int moveX = nowX + dx[i];
        int moveY = nowY + dy[i];

        // 이동한 블록이 유효한 범위인지 체크
        if (0 <= moveX && moveX < n && 0 <= moveY && moveY < n) {
            boolean flag = false;
            while (!flag) {
                if (0 > moveX || moveX >= n || 0 > moveY || moveY >= n) {
                    break;
                }
                // 이동하는 위치가 0인 경우
                if (board[moveX][moveY] == 0) {
                    board[moveX][moveY] = board[nowX][nowY];
                    board[nowX][nowY] = 0;
                    nowX = moveX;
                    nowY = moveY;
                    moveX = nowX + dx[i];
                    moveY = nowY + dy[i];
                } else if (board[moveX][moveY] == board[nowX][nowY]) { // 현재 위치의 값이 이동하는 위치에 값과 같은 경우 즉 더하는 경우
                    if (ch[moveX][moveY] == 0) {
                        ch[moveX][moveY] = 1;
                        board[nowX][nowY] = 0;
                        board[moveX][moveY] *= 2;
                    } else {
                        flag = true;
                    }
                } else { // 현재 위치의 값이 이동하는 위치에 값과 다른 경우
                    flag = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt();
        int[][] board = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = kb.nextInt();
            }
        }
        T.DFS(0, 5, board);
        System.out.println(answer);
    }
}