package com.gcj.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alfred on 13-12-14.
 */
public class FileUtils {

    public static List<String> readFileByLines(String fileName) {
        List<String> contentList = new ArrayList<String>();
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                contentList.add(tempString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
        return contentList;
    }

    public static List<String> getUrlListFromFile(String filePath, String contentEncoding) {
        List<String> urlList = new ArrayList<String>();
        File file = new File(filePath);
        BufferedReader br = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            br = new BufferedReader(new InputStreamReader(fis, contentEncoding));
            String tempString;
            while ((tempString = br.readLine()) != null) {
                urlList.add(tempString);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
                if (fis != null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return urlList;
    }

    public static void writeFile(String filePath, String info) {
        BufferedWriter bufferedWriter = null;
        File file = new File(filePath);

        try {
            if (file.exists()) {
                bufferedWriter = new BufferedWriter(new FileWriter(file, true));
            } else {
                bufferedWriter = new BufferedWriter(new FileWriter(file));
            }
            bufferedWriter.write(info + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null)
                    bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void writeFile(String filePath, List<String> contentList) {
        BufferedWriter bufferedWriter = null;
        File file = new File(filePath);

        try {
            if (file.exists()) {
                bufferedWriter = new BufferedWriter(new FileWriter(file, true));
            } else {
                bufferedWriter = new BufferedWriter(new FileWriter(file));
            }
            for (String content : contentList) {
                bufferedWriter.write(content + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null)
                    bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        List<String> userNames = new ArrayList<String>();
        for (int i = 1; i <= 100000; i++) {
            userNames.add("gcj" + i);
        }
        writeFile("users.txt", userNames);
    }
}
