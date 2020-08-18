package com.example.oracle.rdbs;

import java.sql.Timestamp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sftrings {
    public static void main(String[] args) {
        String[] lis = {"abc", "abcd", "bcdef", "defg"};
        System.out.println(1 << 4);
        // INFO  2020/8/6 11:23 Administrator    dfs 状态dp

        String authenticationValue = "MAC id=\"7F938B205F876FC359400476F943111FE2528D1096F3CC589D3925448D05BA6914ECB973F9C60D9314938EABA411F2D8\",nonce=\"1596692554611:PN15KE45\",mac=\"ik+rS6f9JeFojj7Pcf2/uSkrj2HVhTASN0F79gkPCaM=\"";

        String mess="SELECT column_1, SUM(column_7) column_7\n" +
                "            FROM data";
        String pattern = "(?i) ";
        Pattern p = Pattern.compile("((?i)MAC id)=\"(.*)\",((?i)nonce)=\"(.*)\",((?i)mac)=\"(.*)\"");
        Matcher m = p.matcher(authenticationValue);
        m.find();
        for (int i = 0; i < m.groupCount(); i++) {
            System.out.println(m.group(i + 1));
        }


        String message = "select `COLUMN_0`, `COLUMN_1`, `COLUMN_2`, `COLUMN_3`, `COLUMN_4`, `COLUMN_5`, `COLUMN_6`, `COLUMN_7` from DATA";
        String mes = message.replace('`', ' ');
        System.out.println(message);
        System.out.println(mes);
        System.out.println(mess);
    }
}
