package com.starter.template.util.common;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.text.TextUtils;

import com.starter.template.util.constants.AppConstants;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public final class FileUtils {
    public static File saveAttachment(Context context, byte[] response, String url) throws IOException {
        File file = null;
        if (response != null) {
            String fileName = FileUtils.getFileNameWithExtension(StringUtils.decodeSpace(url));
            file = FileUtils.getFileDirectory(context, fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(response);
            fos.close();
        }
        return file;
    }

    /* Checks if external storage is available for read and write */
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    /* Checks if external storage is available to at least read */
    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }

    public static File getFileDirectory(Context context, String fileName) {
        // Get the directory for the app's private pictures directory.
        File file = null;
        if (isExternalStorageReadable() && isExternalStorageWritable()) {
            File rootDir = new File(Environment.getExternalStorageDirectory(), AppConstants.ROOT_DIRECTORY);
            if (!rootDir.exists()) {
                rootDir.mkdir();
            }
            file = new File(rootDir.getAbsolutePath(), fileName);
        } else {
            file = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), fileName);
        }
        return file;
    }

    public static String getFileNameWithExtension(String url) {
        String fileName = "";
        if (!TextUtils.isEmpty(url)) {
            String mediaUrl[] = url.split("/");
            fileName = mediaUrl[mediaUrl.length - 1];
        }
        return fileName;
    }

    public static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    public static void purgeRootDirectory(Context context) {
        if (checkWriteExternalPermission(context)) {
            File rootDir = new File(Environment.getExternalStorageDirectory(), AppConstants.ROOT_DIRECTORY);
            purgeDirectory(rootDir);
        }
    }

    private static void purgeDirectory(File dir) {
        if (dir == null || !dir.exists()) {
            return;
        }

        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                purgeDirectory(file);
            }
            file.delete();
        }
    }

    private static boolean checkWriteExternalPermission(Context context) {
        String permission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        int res = context.checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }

    private static void purgeDirectoryButKeepSubDirectories(File dir) {
        if (dir == null || !dir.exists()) {
            return;
        }

        for (File file : dir.listFiles()) {
            if (!file.isDirectory()) {
                file.delete();
            }
        }
    }
}
