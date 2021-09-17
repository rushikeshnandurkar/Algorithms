package dynamic_programming.longest_common_substring;

public class LCS {

    private String strA;
    private String strB;

    public LCS(String strA, String strB) {
        this.strA = strA;
        this.strB = strB;
    }

    public int lcsRecursive() {
        return this.lcsRecursive(this.getStrA(), this.getStrB());
    }
    private int lcsRecursive (String a, String b) {

        int m = a.length();
        int n = b.length();

        if (m == 0 || n == 0)
            return 0;
        if (a.charAt(m-1) == b.charAt(n-1))
            return 1+lcsRecursive(a.substring(0,m-1), b.substring(0, n-1));

        return Math.max(lcsRecursive(a.substring(0, m-1), b), lcsRecursive(a, b.substring(0, n-1)));
    }

    // implementation of LCS using dynamic programming
    public int lengthOfLCS() {

        int L[][] = new int[this.getStrA().length()+1][this.getStrB().length()+1];

        for (int i = 0; i <= this.getStrA().length(); i++) {
            for (int j = 0; j <= this.getStrB().length(); j++) {

                if (i == 0 || j == 0)
                    L[i][j] = 0;
                else if (this.getStrA().charAt(i-1) == this.getStrB().charAt(j-1))
                    L[i][j] = L[i-1][j-1] +1;
                else
                    L[i][j] = Math.max(L[i-1][j], L[i][j-1]);
            }
        }
        return L[this.getStrA().length()][this.getStrB().length()];
    }

    // Getters
    public String getStrA() {
        return strA;
    }

    public String getStrB() {
        return strB;
    }
}
