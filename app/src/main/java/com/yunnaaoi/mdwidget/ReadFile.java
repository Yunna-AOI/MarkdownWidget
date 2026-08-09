package com.yunnaaoi.mdwidget;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReadFile {

    // Нужно добавить чтение meta информации файла, и проверка ее. а так же если есть изменение нужно добавить возможность проверять хешсумму файлв.

    public static String readFromFile(Context context, Uri path) {
        String ret = " ";
        try {
            ContentResolver contentResolver= context.getContentResolver();
            InputStream inputStream = contentResolver.openInputStream(path);

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String recieveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((recieveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append("\n").append(recieveString);
                }
                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            Log.e("YunnaAOI", e.toString());
        }

        return ret;
    }
}
