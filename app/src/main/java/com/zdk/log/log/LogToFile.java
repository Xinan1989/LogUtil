package com.zdk.log.log;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.zdk.log.CustomApplication;
import com.zdk.log.utils.SystemUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Des:将Log日志写入文件中
 * autour: wangxa
 * date: 5/1/2019 16:21
 */
public class LogToFile {
    private static String logPath = null;//log日志存放路径
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd　HH:mm:ss", Locale.CHINA);
    private static Date date = new Date();

    private static String getFilePath() {
        String file_dir = "";
        // SD卡是否存在
        boolean isSDCardExist = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
        boolean isRootDirExist = Environment.getExternalStorageDirectory().exists();
        if (isSDCardExist && isRootDirExist) {
            file_dir = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "LogUtilDemo"+File.separator + "log/";

        } else {
            file_dir = CustomApplication.getInstance().getFilesDir().getAbsolutePath() + "/log/";
        }
        return file_dir;
    }

    private static final char VERBOSE = 'v';
    private static final char DEBUG = 'd';
    private static final char INFO = 'i';
    private static final char WARN = 'w';
    private static final char ERROR = 'e';

    public static void v(String tag, String msg) {
        writeToFile(VERBOSE, tag, msg);
    }

    public static void d(String tag, String msg) {
        writeToFile(DEBUG, tag, msg);
    }

    public static void i(String tag, String msg) {
        writeToFile(INFO, tag, msg);
    }

    public static void w(String tag, String msg) {
        writeToFile(WARN, tag, msg);
    }

    public static void e(String tag, String msg) {
        writeToFile(ERROR, tag, msg);
    }

    /**
     * 将log信息写入文件中
     *
     * @param type
     * @param tag
     * @param msg
     */
    private static void writeToFile(char type, String tag, String msg) {
        if (null == logPath) {
            logPath = getFilePath();
        }

        String fileName = logPath + "/log_" + dateFormat.format(new Date()) + "_"+SystemUtil.getDeviceBrand()+"-"+SystemUtil.getSystemModel()+"_"+SystemUtil.getSystemVersion()+".log";
        StringBuilder sb = new StringBuilder();
        File file = new File(logPath);
        // 如果父路径不存在
        if (!file.exists()) {
            file.mkdirs();//创建父路径
        }
        sb.append("["+timeFormat.format(date)+"]  ");
        sb.append(tag+"  ");
        sb.append(msg+"\n");
        FileOutputStream fos = null;
        BufferedWriter bw = null;
        try {
            fos = new FileOutputStream(fileName, true);
            bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();//关闭缓冲流
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
