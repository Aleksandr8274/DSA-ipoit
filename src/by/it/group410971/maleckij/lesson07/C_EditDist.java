package by.it.group410971.maleckij.lesson07;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class C_EditDist {
    String getDistanceEdinting(String one, String two) {
        int m = one.length();
        int n = two.length();
        int[][] dp = new int[m + 1][n + 1];
        char[][] operations = new char[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    dp[i][j] = j;
                    operations[i][j] = '+';
                } else if (j == 0) {
                    dp[i][j] = i;
                    operations[i][j] = '-';
                } else if (one.charAt(i - 1) == two.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                    operations[i][j] = '#';
                } else {
                    int insert = dp[i][j - 1];
                    int delete = dp[i - 1][j];
                    int replace = dp[i - 1][j - 1];

                    if (insert <= delete && insert <= replace) {
                        dp[i][j] = 1 + insert;
                        operations[i][j] = '+';
                    } else if (delete <= insert && delete <= replace) {
                        dp[i][j] = 1 + delete;
                        operations[i][j] = '-';
                    } else {
                        dp[i][j] = 1 + replace;
                        operations[i][j] = '~';
                    }
                }
            }
        }

        StringBuilder result = new StringBuilder();
        int i = m, j = n;
        while (i > 0 || j > 0) {
            char op = operations[i][j];
            switch (op) {
                case '#':
                    result.insert(0, "#,");
                    i--;
                    j--;
                    break;
                case '+':
                    result.insert(0, "+" + two.charAt(j - 1) + ",");
                    j--;
                    break;
                case '-':
                    result.insert(0, "-" + one.charAt(i - 1) + ",");
                    i--;
                    break;
                case '~':
                    result.insert(0, "~" + two.charAt(j - 1) + ",");
                    i--;
                    j--;
                    break;
            }
        }
        return result.toString();
    }

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = C_EditDist.class.getResourceAsStream("dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
    }
}