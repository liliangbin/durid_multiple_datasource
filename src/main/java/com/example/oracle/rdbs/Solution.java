package com.example.oracle.rdbs;

class Solution {


    public static void main(String[] args) {
        String [] mess= {"abc","bcd","mess"};
        System.out.println(shortestSuperstring(mess));
    }
    public static String shortestSuperstring(String[] A) {
        int n = A.length;
        int[][] overlaps = new int[n][n];
        for(int i = 0; i < n; i ++) {
            for(int j = 0; j < n; j ++) {
                if(i == j) continue;
                int m = Math.min(A[i].length(), A[j].length());
                for(int k = m; k >= 0; k --) {
                    if(A[i].endsWith(A[j].substring(0, k))) {
                        overlaps[i][j] = k;
                        break;
                    }
                }
            }
        }
        String res = "";
        String[][] dp = new String[1 << n][n];
        for(int i = 0; i < A.length; i ++)
            res += A[i];
        for(int i = 0; i < 1 << n; i ++)
            for(int j = 0; j < n; j ++)
                dp[i][j] = res;

        for(int i = 0; i < (1 << n) - 1; i ++) {
            for(int pre = 0; pre < n; pre ++) {
                for(int now = 0; now < n; now ++) {
                    int nowstatus = i | (1 << now);
                    if(i == 0) {
                        dp[nowstatus][now] = A[now];
                        continue;
                    }
                    //if(dp[i][pre] == "") continue;
                    if(pre == now) continue;
                    if(dp[i][pre] == res) continue;
                    String temp = dp[i][pre] + A[now].substring(overlaps[pre][now]);
                    if(temp.length() < dp[nowstatus][now].length())
                        dp[nowstatus][now] = temp;
                }
            }
        }
        for(int i = 0; i < n; i ++)
            if(res.length() > dp[(1 << n) - 1][i].length()) res = dp[(1 << n) - 1][i];
        return res;
    }
}