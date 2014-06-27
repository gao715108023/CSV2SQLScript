package com.gcj.utils;

/**
 * Created by alfred on 13-12-14.
 */
public class StringUtils {

    public static void deleteLastComma(StringBuilder sb) {
        String str = sb.toString();
        int length = str.length();
        if (length > 0) {
            sb = new StringBuilder();
            sb.append(str.substring(0, length - 1));
        }
    }

    public static String convertStr(String content, String columnNameType) {
        String result;
        String[] array1 = columnNameType.split(",");
        String[] array = new String[array1.length];
        String[] array2 = content.split(",");
        if (array1.length > array2.length) {
            System.arraycopy(array2, 0, array, 0, array2.length);
        } else {
            System.arraycopy(array2, 0, array, 0, array1.length);
        }
//        for (int i = 0; i < array1.length; i++) {
//            array[i] = array2[i];
//        }

        int[] type = new int[array1.length];
        for (int i = 0; i < array1.length; i++) {
            type[i] = Integer.parseInt(array1[i]);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null && array[i].contains("'")) {
                String[] strS = array[i].split("'");
                StringBuilder sb2 = new StringBuilder();
                for (String str : strS) {
                    sb2.append(str).append("\\'");
                }
                if (array[i].endsWith("'")) {
                    array[i] = sb2.toString();
                } else {
                    String temp = sb2.toString();
                    array[i] = temp.substring(0, sb2.length() - 2);
                }
            }
            if (type[i] == 0)
                sb.append(array[i]).append(",");
            if (type[i] == 1)
                sb.append("'").append(array[i]).append("',");
        }
        String temp = sb.toString();
        int lenght = temp.length();
        result = temp.substring(0, lenght - 1);
        return result;
    }

    public static void getIndexList(String str, String stt) {
        for (int i = 0; i < str.length(); i++) {
            int a = str.indexOf(stt, i);
            if (a < 0) {
                break;
            }
            System.out.println(a);
            i = a;
        }
    }

    public static void main(String[] args) {
        String str = "ReportModule.dll&MODULE='602'&PARAM= loadStat 6046 6046";
        String[] array = str.split("'");
        StringBuilder sb = new StringBuilder();
        for (String anArray : array) {
            sb.append(anArray).append("\\'");
        }
        String temp = sb.toString();
        String result = temp.substring(0, temp.length() - 2);
        System.out.println(result);
        //String stt = "'";
        //getIndexList(str, stt);
    }
}
