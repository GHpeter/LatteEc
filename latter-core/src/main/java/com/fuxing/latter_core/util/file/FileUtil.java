package com.fuxing.latter_core.util.file;

import android.content.Intent;
import android.media.MediaScannerConnection;
import android.os.Build;
import android.os.Environment;
import android.webkit.MimeTypeMap;

import com.fuxing.latter_core.app.Latte;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ：Create by lichunfu
 * @Date : 2020-03-08
 * Description:
 **/
public class FileUtil {

    //格式化的模板
    private static final String TIME_FORMAT = "_yyyyMMdd_HHmmss";
    private static final String SDCARD_DIR = Environment.getExternalStorageDirectory().getPath();

    //默认本地上传图片目录
    private static final String UPLOAD_PHOTO_DIR =
            Environment.getExternalStorageDirectory().getPath() + "/a_upload_photos/";

    //网页缓存地址
    private static final String WEB_CACHE_DIR = Environment.getExternalStorageDirectory().getPath() + "/app_web_cache/";


    //系统相机目录

    private static final String CAMERA_PHOTO_DIR =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getPath();

    /**
     * 获取文件的MIME
     *
     * @param filePath：文件路径
     * @return
     */
    public static String getMimeType(String filePath) {
        String extension = getExtension(filePath);

        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
    }

    /**
     * 获取文件后缀名称
     *
     * @param filePath
     * @return
     */
    public static String getExtension(String filePath) {
        String suffix = "";
        File file = new File(filePath);
        String name = file.getName();
        final int idx = name.lastIndexOf(".");
        if (idx > 0) {
            suffix = name.substring(idx + 1);
        }
        return suffix;
    }

    public static File writeToDisk(InputStream is,
                                   String dir, String prefix,
                                   String extension) {
        File file = FileUtil.createFileByTime(dir, prefix, extension);
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;

        try {
            bis = new BufferedInputStream(is);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);

            byte data[] = new byte[1024 * 4];
            int count;
            while ((count = bis.read(data)) != -1) {
                bos.write(data, 0, count);
            }
            bos.flush();
            fos.flush();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {

                if (bos != null) {
                    bos.close();
                }
                if (fos != null) {
                    fos.close();
                }
                if (bis != null) {
                    bis.close();
                }
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;

    }

    /**
     *
     * @param is
     * @param dir
     * @param name 完整的文件名
     * @return
     */
    public static File writeToDisk(InputStream is,
                                   String dir, String name) {
        File file = FileUtil.createFile(dir, name);
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;

        try {
            bis = new BufferedInputStream(is);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);

            byte data[] = new byte[1024 * 4];
            int count;
            while ((count = bis.read(data)) != -1) {
                bos.write(data, 0, count);
            }
            bos.flush();
            fos.flush();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {

                if (bos != null) {
                    bos.close();
                }
                if (fos != null) {
                    fos.close();
                }
                if (bis != null) {
                    bis.close();
                }
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;

    }

    public static File createFileByTime(String sdcardDirName, String timeFormatHeader, String extension) {
        String fileName = getFileNameByTime(timeFormatHeader, extension);
        return createFile(sdcardDirName, fileName);


    }

    /**
     * @param timeFormatHeader：格式化的头（除去时间部分）
     * @param extension:后缀名
     * @return 返回时间格式化后的文件名
     */

    public static String getFileNameByTime(String timeFormatHeader, String extension) {
        return getTimeFormatName(timeFormatHeader + "." + extension);

    }

    @SuppressWarnings("ResultofMethodCallignored")
    public static File createFile(String sdcardName, String fileName) {
        return new File(createDir(sdcardName), fileName);
    }

    public static String getTimeFormatName(String timeFormatHeader) {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("'" + timeFormatHeader + "'" + TIME_FORMAT);
        return format.format(date);
    }

    @SuppressWarnings("ResultofMethodCallignored")
    public static File createDir(String sdcardDirName) {
        //拼接sd卡完整的dir
        String dir = SDCARD_DIR + "/" + sdcardDirName + "/";
        File fileDir = new File(dir);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        return fileDir;

    }

    public  static  String getRawFile(int id){
        InputStream stream=Latte.getApplication().getResources().openRawResource(id);
        BufferedInputStream bis=new BufferedInputStream(stream);
        InputStreamReader reader=new InputStreamReader(bis);
        BufferedReader bReader=new BufferedReader(reader);
        StringBuilder stringBuilder=new StringBuilder();
        String str;
        try {
            while ((str=bReader.readLine())!=null){
                stringBuilder.append(str);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                bReader.close();
                reader.close();
                bis.close();
                stream.close();

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  stringBuilder.toString();
    }


}

