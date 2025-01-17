package Dynamic_Programming.OneDimensionalDP;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;

/**
 * Author : AMIT KUMAR GUPTA
 * e-mail : amitguptapc@gmail.com
 * Date : 11/09/19
 * Time : 6:15 PM
 * Problem Code : NumbersInPi
 * Platform : AlgoExpert
 */

public class NumbersInPi {
    // minimum no of spaces
    private static long MOD = 1000000007;

    // begin of solution
    private static String s;
    private static int n;
    private static ArrayList<String> list;
    private static int[] dp;

    private static int minSpaces(int pos) {
        if (pos == n)
            return -1;
        if (dp[pos] != -10)
            return dp[pos];
        String pre = "";
        int ans = Integer.MAX_VALUE;
        for (int i = pos; i < n; i++) {
            pre += s.charAt(i);
            if (list.contains(pre)) {
                int m = minSpaces(i + 1);
                if (m != -2) {
                    ans = Math.min(ans, 1 + m);
                }
            }
        }
        return dp[pos] = ans == Integer.MAX_VALUE ? -2 : ans; // -2 indicates its not possible to split given string
    }

    public static void main(String[] args) throws IOException {
        AmitScan sc = new AmitScan();
        AmitPrint pr = new AmitPrint();
        s = sc.s(); // input of the string
        n = s.length();
        int m = sc.si();
        dp = new int[n + 1];
        Arrays.fill(dp, -10);
        list = new ArrayList<>();
        for (int i = 0; i < m; i++)
            list.add(sc.s());
        pr.pl(minSpaces(0));
        pr.close();
    }
    // end of solution

    static class AmitScan {
        private byte[] buf = new byte[1024]; // Buffer of Bytes
        private int index;
        private InputStream in;
        private int total;

        AmitScan() {
            in = System.in;
        }

        private int scan() throws IOException // Scan method used to scan buf
        {
            if (total < 0)
                throw new InputMismatchException();
            if (index >= total) {
                index = 0;
                total = in.read(buf);
                if (total <= 0)
                    return -1;
            }
            return buf[index++];
        }

        String s() throws IOException {
            StringBuilder sb = new StringBuilder();
            int n = scan();
            while (isWhiteSpace(n))
                n = scan();
            while (!isWhiteSpace(n)) {
                sb.append((char) n);
                n = scan();
            }
            return sb.toString();
        }

        int si() throws IOException {
            int integer = 0;
            int n = scan();
            while (isWhiteSpace(n)) // Removing starting whitespaces
                n = scan();
            int neg = 1;
            if (n == '-') // If Negative Sign encounters
            {
                neg = -1;
                n = scan();
            }
            while (!isWhiteSpace(n)) {
                if (n >= '0' && n <= '9') {
                    integer *= 10;
                    integer += n - '0';
                    n = scan();
                } else
                    throw new InputMismatchException();
            }
            return neg * integer;
        }

        long sl() throws IOException {
            long integer = 0;
            int n = scan();
            while (isWhiteSpace(n))
                n = scan();
            int neg = 1;
            if (n == '-') {
                neg = -1;
                n = scan();
            }
            while (!isWhiteSpace(n)) {
                if (n >= '0' && n <= '9') {
                    integer *= 10;
                    integer += n - '0';
                    n = scan();
                } else
                    throw new InputMismatchException();
            }
            return neg * integer;
        }

        private boolean isWhiteSpace(int n) {
            return n == ' ' || n == '\n' || n == '\r' || n == '\t' || n == -1;
        }
    }

    static class AmitPrint {
        private final BufferedWriter bw;

        AmitPrint() {
            this.bw = new BufferedWriter(new OutputStreamWriter(System.out));
        }

        private void p(Object object) throws IOException {
            bw.append("").append(String.valueOf(object));
        }

        void pl(Object object) throws IOException {
            p(object);
            bw.append("\n");
        }

        void close() throws IOException {
            bw.close();
        }

        void f() throws IOException {
            bw.flush();
        }
    }
}
/* sample test case

3141592653589793238462643383279
9
314
49
9001
15926535897
14
9323
8462643383279
4
793

 */