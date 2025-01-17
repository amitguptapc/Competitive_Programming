package src.Mathematics;

import java.util.Scanner;

public class NKLadder {
    private static int k;
    private static long[] b, c;
    private static long MOD = 1000000000;

    private static long[][] multiply(long[][] A, long[][] B) {
        long[][] C = new long[k][k];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                for (int l = 0; l < k; l++) {
                    C[i][j] = (C[i][j] + (A[i][l] * B[l][j]) % MOD) % MOD;
                }
            }
        }
        return C;
    }

    private static long[][] power(long[][] T, int n) {
        // base case
        if (n == 1)
            return T;
        // if n is even
        if ((n & 1) == 1)
            return multiply(T, power(T, n - 1));
        else {
            long[][] X = power(T, n / 2);
            return multiply(X, X);
        }
    }

    private static long calculate(int n) {
        //if value is less than k no need to calculate
        if (n == 0)
            return 0;
        if (n <= k)
            return b[n - 1];
        // finding F1 vector which is b
        // calculating the transformation matrix T
        long[][] T = new long[k][k];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                if (i < k - 1) {
                    if (j == i + 1)
                        T[i][j] = 1;
                    else T[i][j] = 0;
                    continue;
                }
                // filling the last row
                T[i][j] = c[k - j - 1];
            }
        }
        // calculate T^n-1
        T = power(T, n - 1);
        // calculate Fn
        long res = 0;
        for (int i = 0; i < k; i++)
            res = (res + (T[0][i] * b[i]) % MOD) % MOD;
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("No of rugs in ladder :");
        int n = sc.nextInt();// no of rugs in ladder
        System.out.println("Max no of steps :");
        k = sc.nextInt();// max no of steps he can take
        b = new long[k];
        c = new long[k];
        b[0] = 1;
        for (int i = 1; i < k; i++)
            b[i] = 2 * b[i - 1];
        for (int i = 0; i < k; i++)
            c[i] = 1;
        System.out.println("No of ways to climb : " + calculate(n));
    }
}