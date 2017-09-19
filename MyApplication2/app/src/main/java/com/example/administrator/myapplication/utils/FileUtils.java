package com.example.administrator.myapplication.utils;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Created by Administrator on 2017/6/10.
 */
public class FileUtils {

    public static void writeFile(String path,String jsonInfo){
        if (isSdCardExist()){
            try {
                File file = new File(path);
                //第二个参数意义是说是否以append方式添加内容
                BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
                bw.write(jsonInfo);
                bw.flush();
                Log.e("tttt","写入成功");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String readFile(String path){
        if (isSdCardExist()){
            StringBuffer sb = new StringBuffer();;
            try {
                File file = new File(path);
                BufferedReader br = new BufferedReader(new FileReader(file));
                String readline = "";
//            sb = new StringBuffer();
                while ((readline = br.readLine()) != null) {
                    System.out.println("readline:" + readline);
                    sb.append(readline);
                }
                br.close();
                Log.e("tttt","写入成功"+sb.toString());
                System.out.println("读取成功：" + sb.toString());

            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.e("tttt","写入成功22");
            return sb.toString();
        }else{
            return "";
        }

    }

    public static boolean isSdCardExist() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }
}
