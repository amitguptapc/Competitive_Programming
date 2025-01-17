package Dynamic_Programming.MultiDimensionalDP;

import java.util.Scanner;

public class MagicalString1 {
    private static final long mod = 1000000007;
    private static char[] s;
    private static int[] f;
    private static int n;

    private static boolean isValid(int i, int j) {
        int l = j - i + 1;
        for (int x = i; x <= j; x++)
            if (f[s[x] - 'a'] < l)
                return false;
        return true;
    }

    private static long solve() {
        long[] dp = new long[n + 1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = i; j >= 1; j--) {
                if (isValid(j - 1, i - 1))
                    dp[i] = (dp[i] % mod + dp[j - 1] % mod) % mod;
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        s = sc.next().toCharArray();
        f = new int[26];
        for (int i = 0; i < 26; i++)
            f[i] = sc.nextInt();
        System.out.println(solve());
    }
}
