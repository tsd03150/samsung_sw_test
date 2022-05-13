package boj_13458;

import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = kb.nextInt();
        }
        int b = kb.nextInt();
        int c = kb.nextInt();

        long sum = 0;
        for (int i = 0; i < n; i++) {
            int num = arr[i] - b;
            int tmp  = 1;
            if (num > 0) {
                if (num % c == 0) {
                    tmp += num / c;
                } else {
                    tmp += num / c + 1;
                }
            }
            sum += tmp;
        }
        System.out.println(sum);
    }
}