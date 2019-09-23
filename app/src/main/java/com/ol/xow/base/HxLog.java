package com.ol.xow.base;

import android.text.TextUtils;
import android.util.Log;

public class HxLog {

    public static final int VERBOSE = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int WARN = 4;
    public static final int ERROR = 5;
    public static final int NOTHING = 6;
    public static final int LEVEL = VERBOSE;
    public static final String SEPARATOR = ",";

    private static boolean isDebuggable() {
        return true;
    }

    public static void v(String message) {
        if (!isDebuggable()) {
            return;
        }
        if (LEVEL <= VERBOSE) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            String tag = getDefaultTag(stackTraceElement);
            Log.v(tag, message);
        }
    }

    public static void v(String tag, String message) {
        if (!isDebuggable()) {
            return;
        }

        if (LEVEL <= VERBOSE) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            if (TextUtils.isEmpty(tag)) {
                tag = getDefaultTag(stackTraceElement);
            }
            Log.v(tag, message);
        }
    }

    public static void d(String message) {
        if (!isDebuggable()) {
            return;
        }

        if (LEVEL <= DEBUG) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            String tag = getDefaultTag(stackTraceElement);
            Log.d(tag, message);
        }
    }

    public static void d(String tag, String message) {
        if (!isDebuggable()) {
            return;
        }

        if (LEVEL <= DEBUG) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            if (TextUtils.isEmpty(tag)) {
                tag = getDefaultTag(stackTraceElement);
            }
            Log.d(tag, message);
        }
    }

    public static void i(String message) {
        if (!isDebuggable()) {
            return;
        }

        if (LEVEL <= INFO) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            String tag = getDefaultTag(stackTraceElement);
            Log.i(tag, message);
        }
    }

    public static void i(String tag, String message) {
        if (!isDebuggable()) {
            return;
        }

        if (LEVEL <= INFO) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            if (TextUtils.isEmpty(tag)) {
                tag = getDefaultTag(stackTraceElement);
            }
            Log.i(tag, message);
        }
    }

    public static void w(String message) {
        if (!isDebuggable()) {
            return;
        }

        if (LEVEL <= WARN) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            String tag = getDefaultTag(stackTraceElement);
            Log.w(tag, message);
        }
    }

    public static void w(String tag, String message) {
        if (!isDebuggable()) {
            return;
        }

        if (LEVEL <= WARN) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            if (TextUtils.isEmpty(tag)) {
                tag = getDefaultTag(stackTraceElement);
            }
            Log.w(tag, message);
        }
    }

    public static void e(String message) {
        if (!isDebuggable()) {
            return;
        }

        if (LEVEL <= ERROR) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            String tag = getDefaultTag(stackTraceElement);
            if (message != null) {
                Log.e(tag, message);
            }
        }
    }

    public static void e(String tag, String message) {
        if (!isDebuggable()) {
            return;
        }

        if (LEVEL <= ERROR) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            if (TextUtils.isEmpty(tag)) {
                tag = getDefaultTag(stackTraceElement);
            }
            Log.e(tag, message);
        }
    }

    /**
     * 获取默认的TAG名称.
     * 比如在MainActivity.java中调用了日志输出.
     * 则TAG为MainActivity
     */
    private static String getDefaultTag(StackTraceElement stackTraceElement) {
        String fileName = stackTraceElement.getFileName();
        String stringArray[] = fileName.split("\\.");
        String tag = stringArray[0];
        return tag;
    }

    /**
     * 输出日志所包含的信息
     */
    private static String getLogInfo(StackTraceElement stackTraceElement) {
        StringBuilder logInfoStringBuilder = new StringBuilder();
        // 获取线程名
        String threadName = Thread.currentThread().getName();
        // 获取线程ID
        long threadID = Thread.currentThread().getId();
        // 获取文件名.即xxx.java
        String fileName = stackTraceElement.getFileName();
        // 获取类名.即包名+类名
        String className = stackTraceElement.getClassName();
        // 获取方法名称
        String methodName = stackTraceElement.getMethodName();
        // 获取输出行数
        int lineNumber = stackTraceElement.getLineNumber();

        logInfoStringBuilder.append("[ ");
        logInfoStringBuilder.append("threadID=").append(threadID).append(SEPARATOR);
        logInfoStringBuilder.append("threadName=").append(threadName).append(SEPARATOR);
        logInfoStringBuilder.append("fileName=").append(fileName).append(SEPARATOR);
        logInfoStringBuilder.append("className=").append(className).append(SEPARATOR);
        logInfoStringBuilder.append("methodName=").append(methodName).append(SEPARATOR);
        logInfoStringBuilder.append("lineNumber=").append(lineNumber);
        logInfoStringBuilder.append(" ] ");
        return logInfoStringBuilder.toString();
    }

    /**
     * 输出日志所包含的简化信息
     */
    private static String getLogSampleInfo(StackTraceElement stackTraceElement) {
        // 获取方法名称
        String methodName = stackTraceElement.getMethodName();
        return methodName;
    }
}
