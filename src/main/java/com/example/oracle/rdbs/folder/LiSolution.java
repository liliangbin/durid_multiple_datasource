package com.example.oracle.rdbs.folder;

public class LiSolution {

    private String[] A;
    private String[][] dp;
    private int[][] overlaps;
    String res = "";

    public LiSolution(String[] a) {
        A = a;
    }

    public void fillString() {
        int n = A.length;
        for (int i = 0; i < A.length; i++)
            res += A[i];

        dp = new String[1 << n][n];
        // 把dp数组中所有都填满为直接拼接的字符串。
        for (int i = 0; i < 1 << n; i++)
            for (int j = 0; j < n; j++)
                dp[i][j] = res;
    }

    public void getDp() {
        int n = A.length;
        for (int i = 0; i < (1 << n) - 1; i++) {
            for (int pre = 0; pre < n; pre++) {

                /* 当前的步伐，能够预测下一步的操作需要耗费多少。当前的dp+overlaps
                * 0 | 0 0 0  ----> 1 | 2 | 4 ---> 分别在相应的dp位置添加上对应的字符串。dp[1][0] dp[2][1] dp[4][2]
                *
                * 1 | 0 0 1  ----> 3 | 5 ---> 预测对应的dp位置 dp[3][1]=dp[1][0]+overlaps[0][1]  dp[5][2] =dp[1][0]+overlaps[0][2]
                * 2 | 0 1 0  ----> ***** 6 | 3 遇到相同的位置，最优的留下来。
                * 4 | 1 0 0
                *
                * 3 | 0 1 1      |
                * 5 | 1 0 1      |
                * 6 | 1 1 0      |
                *                >>>  根据前一步的结果，预测到的最优的三种选择的三种方式 再从中选取最小的
                * 7 | 1 1 1
                *
                * */
                for (int now = 0; now < n; now++) {
                    int nowstatus = i | (1 << now);
                    if (i == 0) {
                        dp[nowstatus][now] = A[now];  // dp 数组初始化
                        continue;
                    }
                    if (pre == now) continue;
                    String temp = dp[i][pre] + A[now].substring(overlaps[pre][now]);
                    if (temp.length() < dp[nowstatus][now].length())
                        dp[nowstatus][now] = temp;
                }
            }
        }
    }

    public void getOverlaps() {
        int n = A.length;
        overlaps = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                int m = Math.min(A[i].length(), A[j].length());
                for (int k = m; k >= 0; k--) {
                    if (A[i].endsWith(A[j].substring(0, k))) {
                        overlaps[i][j] = k;
                        break;
                    }
                }
            }
        }

    }

    public String getRes() {
        int n = A.length;
        for (int i = 0; i < n; i++)
            if (res.length() > dp[(1 << n) - 1][i].length()) res = dp[(1 << n) - 1][i];
        return res;
    }

    public static void main(String[] args) {
        String[] emss = {
                "catg", "ctaagt", "gcta", "ttca", "atgcatc"
        };

        LiSolution liSolution = new LiSolution(emss);

        liSolution.getOverlaps();
        liSolution.fillString();
        liSolution.getDp();
        String mess = liSolution.getRes();
        System.out.println(mess);
    }
}
