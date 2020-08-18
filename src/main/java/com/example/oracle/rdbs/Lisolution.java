package com.example.oracle.rdbs;

import java.util.Arrays;

public class Lisolution {
    private String[] strings;
    private int[][] overleaps;
    private int[][] parents;
    private int[][] dp;
    int[] perm;
    boolean[] seen;

    public Lisolution(String[] strings) {
        this.strings = strings;
    }

    public void getOverLeaps() {
        int length = strings.length;
        overleaps = new int[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (i == j) continue;
                else {
                    int m = Math.min(strings[i].length(), strings[j].length());
                    for (int k = m; k >= 0; k--) {
                        if (strings[i].endsWith(strings[j].substring(0, k))) {
                            this.overleaps[i][j] = k;
                            break;
                        }
                    }
                }
            }
        }
    }

    public void getDp() {
        int length = strings.length;
        dp = new int[1 << length][length];
        parents = new int[1 << length][length];
        for (int mask = 0; mask < (1 << length); mask++) {
            Arrays.fill(parents[mask], -1);
            for (int i = 0; i < length; i++) {
                if (((mask >> i) & 1) > 0) {
                    int pmask = mask ^ (1 << i);
                    if (pmask == 0) {
                        continue;
                    }
                    for (int j = 0; j < length; j++) {
                        if (((pmask >> j) & 1) > 0) {
                            int result = dp[pmask][j] + overleaps[j][i];
                            if (result > dp[pmask][j] + overleaps[j][i]) {
                                dp[mask][i] = result;
                                parents[mask][i] = j;
                            }
                        }
                    }
                }
            }
        }
    }

    public String getResult() {
        int length = strings.length;
        perm=new int[length];
        seen=new boolean[length];
        int t=0;
        int mask=(1<<length) -1;
        int p=0;
        for (int i = 0; i <length ; i++) {
            if(dp[(1<<length)-1][i]>dp[(1<<length)-1][p]){
                p=i;
            }
        }
        while (p != -1) {
            perm[t++] = p;
            seen[p] = true;
            int p2 = parents[mask][p];
            mask ^= 1 << p;
            p = p2;
        }

        for (int i = 0; i < t / 2; ++i) {
            int v = perm[i];
            perm[i] = perm[t - 1 - i];
            perm[t - 1 - i] = v;
        }

        for (int i = 0; i < length; ++i)
            if (!seen[i])
                perm[t++] = i;

        StringBuilder ans = new StringBuilder(strings[perm[0]]);
        for (int i = 1; i < length; ++i) {
            int overlap = overleaps[perm[i - 1]][perm[i]];
            ans.append(strings[perm[i]].substring(overlap));
        }

        return ans.toString();
    }

    public static void main(String[] args) {
        String[] mess= {
                "abd",
                "dfg",
                "message"
        };

        Lisolution liSolution=new Lisolution(mess);
        liSolution.getOverLeaps();
        liSolution.getDp();
        String result=liSolution.getResult();
//        System.out.println(result);
      String df=  liSolution.shortestSuperstring(mess);
        System.out.println(df);
    }


    public String shortestSuperstring(String[] A) {
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
        for(int i = 0; i < A.length; i ++)
            res += A[i];

        String[][] dp = new String[1 << n][n];
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
                    if(pre == now) continue;
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

