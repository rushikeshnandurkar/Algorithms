package dynamic_programming.longest_common_substring;

public class LIS {

    public static int findLengthOfLIS(int[] arr) {

        int n = arr.length;
        int[] lis = new int[n];
        int max;
        max = 0;

        for (int i = 0; i < n; i++) {
            lis[i] = 1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && lis[i] < lis[j]+1) {
                    lis[i] = lis[j] + 1;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (lis[i] > max) {
                max = lis[i];
            }
        }

        return max;
    }
}
