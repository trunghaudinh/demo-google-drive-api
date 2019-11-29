package com.example.demogg;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.content.ContentValues.TAG;

public class RealmBackupRestore {
//    public void backup() {
//        try {
//            // create a backup file
//            File exportRealmFile;
//            exportRealmFile = new File(EXPORT_REALM_PATH, EXPORT_REALM_FILE_NAME);
//
//            // if backup file already exists, delete it
//            exportRealmFile.delete();
//
//            // copy current realm to backup file
//            realm.writeCopyTo(exportRealmFile);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        realm.close();
//    }
//    public void restore() {
//        String restoreFilePath = EXPORT_REALM_PATH + "/" + EXPORT_REALM_FILE_NAME;
//
//        copyBundledRealmFile(restoreFilePath, IMPORT_REALM_FILE_NAME);
//        Log.d(TAG, "Data restore is done");
//    }
//    private String copyBundledRealmFile(String oldFilePath, String outFileName) {
//        try {
//            File file = new File(activity.getApplicationContext().getFilesDir(), outFileName);
//
//            FileOutputStream outputStream = new FileOutputStream(file);
//
//            FileInputStream inputStream = new FileInputStream(new File(oldFilePath));
//
//            byte[] buf = new byte[1024];
//            int bytesRead;
//            while ((bytesRead = inputStream.read(buf)) > 0) {
//                outputStream.write(buf, 0, bytesRead);
//            }
//            outputStream.close();
//            return file.getAbsolutePath();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
