package dynamic_programming.edit_distance;

import java.util.Collections;
import java.util.List;

public class EditDistance {

    public static int findEditDistance(String a, String b, boolean byRecursion) {

        if (byRecursion)
            return EditDistance.findEditDistanceUsingRecursion(a, b);

        return EditDistance.findEditDistanceUsingDP(a, b);
    }

    private static int findEditDistanceUsingDP(String a, String b) {
        int m = a.length();
        int n = b.length();
        int[][] dp = new int[m+1][n+1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {

                if (i == 0)
                    dp[i][j] = j;   // when a (str1) is empty
                else if (j == 0)
                    dp[i][j] = i;   // when b (str2) is empty
                else if (a.charAt(i-1) == b.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1];    // Ignore if the characters are same
                else
                    dp[i][j] = 1 + Collections.min(List.of(dp[i][j-1],  // Insert
                                                            dp[i-1][j], // Remove
                                                            dp[i-1][j-1])); // Replace
            }
        }

        return dp[m][n];
    }

    private static int findEditDistanceUsingRecursion(String a, String b) {

        int m = a.length();
        int n = b.length();

        if (m == 0)
            return b.length();

        if (n == 0)
            return a.length();


        if (a.charAt(m-1) == b.charAt(n-1))
            return EditDistance.findEditDistanceUsingRecursion(a.substring(0, m-1), b.substring(0, n-1));

        return 1 + Collections.min(List.of(EditDistance.findEditDistanceUsingRecursion(a, b.substring(0, n-1)),
                EditDistance.findEditDistanceUsingRecursion(a.substring(0, m-1), b),
                EditDistance.findEditDistanceUsingRecursion(a.substring(0, m-1), b.substring(0, n-1))));
    }

}
