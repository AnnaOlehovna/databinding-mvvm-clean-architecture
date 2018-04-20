package anna.poddubnaya.presentation.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;


import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;


public class ImageChooser {

    public static final int CAMERA_REQUEST_CODE = 123;
    public static final int GALERIA_REQUEST_CODE = 987;

    public static void startCamera(Activity activity) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //проверяет наличие такого Intent в телефоне - в данном случае, есть ли камера
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            File photo = getCameraFile(activity);
            Uri uri = FileProvider.getUriForFile(activity, "anna.poddubnaya.presentation.utils.MyFileProvider", photo);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            activity.startActivityForResult(intent, CAMERA_REQUEST_CODE);

        }

    }

    public static void startGallery(Activity activity) {
        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions
                .request(Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (granted) {
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        if (intent.resolveActivity(activity.getPackageManager()) != null) {
                            activity.startActivityForResult(intent, GALERIA_REQUEST_CODE);
                        }
                    } else {
                        // Oups permission denied
                    }
                });


    }

    private static File getCameraFile(Activity activity) {
        File root = activity.getExternalFilesDir(null);
        if (root == null) {
            root = activity.getFilesDir();
        }
        File myDir = new File(root.getAbsoluteFile() + "/myDir");
        if (!myDir.exists())
            myDir.mkdir();
        return new File(myDir.getAbsoluteFile() + "/" + "123.jpg");
    }


    public static File getImageFromResult(Activity activity, int requestCode,
                                          int resultCode, Intent data) {
        switch (requestCode) {
            case CAMERA_REQUEST_CODE: {
                File file = getCameraFile(activity);
                if (file.exists()) {
                    return file;
                } else {
                    return null;
                }
            }
            case GALERIA_REQUEST_CODE: {
                Uri uri = data.getData();
                Cursor cursor = activity.getApplicationContext().getContentResolver()
                        .query(uri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);
                if (cursor == null) {
                    return null;
                }

                cursor.moveToFirst();
                int index = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                String file = cursor.getString(index);
                return new File(file);
            }
        }


        return null;
    }
}
