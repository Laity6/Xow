package com.ol.xow.base;

import android.graphics.Bitmap;
import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 文件操作工具类
 */
public class FileUtils {

    /**
     * 删除文件
     *
     * @param filePath
     * @return
     */
    public static boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists())
            return file.delete();
        return true;
    }

    public static String createFilePath(String folder, String fileName) {
        File dir = new File(folder);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return new File(dir, fileName).getAbsolutePath();
    }

    /**
     * 创建图片文件 默认扩展名 .jpg
     *
     * @param filePath
     * @param bitmap
     * @return
     */
    public static String createImage(String filePath, Bitmap bitmap) {
        String fileName=System.currentTimeMillis()+"";
        return createImage(filePath, fileName, ".jpg", bitmap);
    }
    /**
     * 创建图片文件 默认扩展名 .jpg
     *
     * @param filePath
     * @param fileName
     * @param bitmap
     * @return
     */
    public static String createImage(String filePath, String fileName, Bitmap bitmap) {
        return createImage(filePath, fileName, ".jpg", bitmap);
    }


    /**
     * 创建图片
     *
     * @param filePath
     * @param fileName
     * @param suffix
     * @param bitmap
     * @return
     */
    public static String createImage(String filePath, String fileName, String suffix, Bitmap bitmap) {
        FileOutputStream fos = null;
        File file = null;
        fileName = fileName + suffix;
        String savePath = filePath + "/" + fileName;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
                HxLog.d("Create the file:" + file.getPath());
            }
            file = new File(savePath);
            if (!file.exists()) {
                file.createNewFile();
                HxLog.d("Create the file:" + file.getPath());
            }
            fos = new FileOutputStream(savePath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.close();
        } catch (Exception e) {
            savePath = null;
            HxLog.e("Error on write createImage File:" + e);
        } finally {
            fos = null;
        }
        return savePath;
    }

    /**
     * 写入文件到txt
     *
     * @param strcontent
     * @param filePath
     * @param fileName
     */
    public static void writeTxtToFile(String strcontent, String filePath, String fileName) {
        //生成文件夹之后，再生成文件，不然会出错
        makeFilePath(filePath, fileName);

        String strFilePath = filePath + fileName;
        // 每次写入时，都换行写
        String strContent = strcontent + "\r\n";
        try {
            File file = new File(strFilePath);
            if (!file.exists()) {
                HxLog.d("Create the file:" + strFilePath);
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            RandomAccessFile raf = new RandomAccessFile(file, "rwd");
            raf.seek(file.length());
            raf.write(strContent.getBytes());
            raf.close();
        } catch (Exception e) {
            HxLog.e("Error on write File:" + e);
        }
    }

    /**
     * 创建文件
     *
     * @param filePath
     * @param fileName
     * @return
     */
    public static File makeFilePath(String filePath, String fileName) {
        File file = null;
        makeRootDirectory(filePath);
        try {
            file = new File(filePath + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * 生成文件夹
     *
     * @param filePath
     */
    public static void makeRootDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }
        } catch (Exception e) {
            HxLog.i("error:", e + "");
        }
    }


    /**
     * RandomAccessFile 获取文件的MD5值
     *
     * @param file 文件路径
     * @return md5
     */
    public static String getFileMd53(File file) {
        MessageDigest messageDigest;
        RandomAccessFile randomAccessFile = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");

            if (file == null) {
                return "";
            }
            if (!file.exists()) {
                return "";
            }
            randomAccessFile = new RandomAccessFile(file, "r");
            byte[] bytes = new byte[1024 * 1024 * 2];
            int len = 0;
            while ((len = randomAccessFile.read(bytes)) != -1) {
                messageDigest.update(bytes, 0, len);
            }
            BigInteger bigInt = new BigInteger(1, messageDigest.digest());
            String md5 = bigInt.toString(16);
            while (md5.length() < 32) {
                md5 = "0" + md5;
            }
            return md5;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                    randomAccessFile = null;
                }
            } catch (IOException e) {
                // 
                e.printStackTrace();
            }
        }
        return "";
    }

    public static String getFileMD5(File file) {
        if (!file.isFile()) {
            return null;
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        byte buffer[] = new byte[1024 * 1024 * 2];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024 * 1024 * 2)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        String md5 = bigInt.toString(16);
        while (md5.length() < 32) {
            md5 = "0" + md5;
        }
        return md5;
    }

    /**
     * @param size 文件大小(以Byte为单位)
     * @return String 格式化的常见文件大小(保留一位小数)
     */
    public static String formatFileSize(long size) {
        if (size < 1024 * 1024) {
            int f = (int) Math.ceil(size / 1024f);
            return String.format("%dK", f);
        } else {
            float f = size / (1024 * 1024f);
            return String.format("%.1fM", f);
        }
    }

    public static void copyFile(String pathFrom, String pathTo) {
        try {
            if (pathFrom.equalsIgnoreCase(pathTo)) {
                return;
            }

            FileChannel outputChannel = null;
            FileChannel inputChannel = null;
            try {
                inputChannel = new FileInputStream(new File(pathFrom)).getChannel();
                outputChannel = new FileOutputStream(new File(pathTo)).getChannel();
                inputChannel.transferTo(0, inputChannel.size(), outputChannel);
                inputChannel.close();
            } finally {
                if (inputChannel != null) inputChannel.close();
                if (outputChannel != null) outputChannel.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 是否是网络图片
     *
     * @param path
     * @return
     */
    public static boolean isHttp(String path) {
        if (!TextUtils.isEmpty(path)) {
            if (path.startsWith("http")
                    || path.startsWith("https")) {
                return true;
            }
        }
        return false;
    }


    public static boolean isSDCard(String path) {
        if (!TextUtils.isEmpty(path)) {
            File file = new File(path);
            return file.exists();
        }
        return false;
    }

    /**
     * 读取文件的大小
     */
    public static long getFileSize(File f) {
        long l = 0;
        try {
            if (f.exists()) {
                FileInputStream inputStream = new FileInputStream(f);
                l = inputStream.available();
            } 
        } catch (Exception e) {

        }
        return l;
    }

    /**
     * 调用此方法计算指定文件或指定文件夹的大小
     *
     * @param filePath 文件路径
     * @return
     */
    public static long getFileOrFolderSize(String filePath) {
        File file = new File(filePath);
        long blockSize = 0;
        try {
            if (file.isDirectory()) {
                blockSize = getFileSizes(file);
            } else {
                blockSize = getFileSize(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
            HxLog.e("获取文件大小失败!");
        }
        return blockSize;
    }

    /**
     * 获取指定文件夹
     *
     * @param f
     * @return
     * @throws Exception
     */
    private static long getFileSizes(File f) throws Exception {
        long size = 0;
        File flist[] = f.listFiles();
        for (int i = 0; i < flist.length; i++) {
            if (flist[i].isDirectory()) {
                size = size + getFileSizes(flist[i]);
            } else {
                size = size + getFileSize(flist[i]);
            }
        }
        return size;
    }

    /**
     * 删除文件夹或者文件
     *
     * @param filePath
     * @return
     */
    public static void deleteFileOrFolder(String filePath) {
        File file = new File(filePath);
        try {
            if(file.isDirectory()){
                deleteFiles(file);
            }else{
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
            HxLog.e("删除文件或文件夹失败!");
        }
    }

    /**
     * 删除文件夹下的文件
     *
     * @param f
     * @throws Exception
     */
    private static void deleteFiles(File f) throws Exception {
        File files[] = f.listFiles();
        for (int i = 0; i < files.length; i++) {
            if(!files[i].isDirectory()){
                files[i].delete();
            }
        }
    }

    public static String createImageType(String path) {
        try {
            if (!TextUtils.isEmpty(path)) {
                File file = new File(path);
                String fileName = file.getName();
                int last = fileName.lastIndexOf(".") + 1;
                String temp = fileName.substring(last, fileName.length());
                return "image/" + temp;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "image/jpeg";
        }
        return "image/jpeg";
    }

    public static String createVideoType(String path) {
        try {
            if (!TextUtils.isEmpty(path)) {
                File file = new File(path);
                String fileName = file.getName();
                int last = fileName.lastIndexOf(".") + 1;
                String temp = fileName.substring(last, fileName.length());
                return "video/" + temp;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "video/mp4";
        }
        return "video/mp4";
    }

    /**
     * 获取文件后缀
     * @return
     */
    public static String getFileSuffix(String path){
        if (!TextUtils.isEmpty(path)) {
            int last = path.lastIndexOf(".");
            String suffix = path.substring(last, path.length());
            return suffix;
        }
        return "";
    }

    public static String getFileName(String path){
        if (!TextUtils.isEmpty(path)) {
            int last = path.lastIndexOf(File.separator);
            String fileName = path.substring(last + 1, path.length());
            return fileName;
        }
        return "";
    }
}
