package dynamic_programming.ways_to_cover_a_distance.Solution;

public class Solution {

    public static int countWaysRecursive(int n) {

        if (n < 0)
            return 0;
        if (n == 0)
            return 1;

        return countWaysRecursive(n-1) +
                countWaysRecursive(n-2) +
                countWaysRecursive(n-3);
    }

    public static int countWaysDP(int n) {

        int[] count = new int[n+1];

        // initialize first 3 indices to 1, 1, 2
        // this program will fail if n is less than 4
        count[0] = 1;
        count[1] = 1;
        count[2] = 2;

        for (int i = 3; i <= n; i++) {
            count[i] = count[i-1] + count[i-2] + count[i-3];
        }
        return count[n];
    }

    public static void main(String[] args) {

        int n = 4;
        System.out.println("Recursive Result: ");
        System.out.println(countWaysRecursive(n));
        System.out.println("Dynamic programming result: ");
        System.out.println(countWaysDP(n));
    }
}
