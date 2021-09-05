package app.forrozapp.meuforroapp.android;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

@SuppressLint({"NewApi"})
public class ClassDownloads {
    public static Connection.Response file;
    public static Connection.Response res;

    /* access modifiers changed from: package-private */
    public boolean existe_file(String dir, String nome) {
        if (new File(Environment.getExternalStorageDirectory() + "/PlugMusica/" + dir + "/" + nome).exists()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean download_jsoup(String fileurl, String dir, String nome) {
        try {
            File direct = new File(Environment.getExternalStorageDirectory() + "/PlugMusica" + dir);
            if (!direct.exists()) {
                direct.mkdirs();
            }
            if (new File(Environment.getExternalStorageDirectory() + "/PlugMusica" + dir + "/" + nome).exists()) {
                return false;
            }
            file = Jsoup.connect(fileurl).ignoreContentType(true).userAgent("Mozilla/5.0").maxBodySize(20971520).timeout(700000).execute();
            String PATH = Environment.getExternalStorageDirectory() + "/PlugMusica" + dir + "/" + nome;
            try {
                int len = file.bodyAsBytes().length;
                FileOutputStream out = new FileOutputStream(new File(PATH));
                out.write(file.bodyAsBytes(), 0, len);
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            return true;
        } catch (IOException e3) {
            e3.printStackTrace();
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean download_invisible_NAOEMUSO__(String fileurl, String dir, String nome) {
        File direct = new File(Environment.getExternalStorageDirectory() + "/PlugMusica" + dir);
        if (!direct.exists()) {
            direct.mkdirs();
        }
        if (new File(Environment.getExternalStorageDirectory() + "/PlugMusica" + dir + "/" + nome).exists()) {
            return false;
        }
        try {
            Connection.Response resultImageResponse = Jsoup.connect(fileurl.replace(" ", "%20")).maxBodySize(5999999).timeout(999999).ignoreContentType(true).execute();
            FileOutputStream out = new FileOutputStream(new File("/sdcard/PlugMusica" + dir + "/" + nome));
            out.write(resultImageResponse.bodyAsBytes());
            out.close();
            return true;
        } catch (Exception e) {
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean download_invisible2__(String fileurl, String dir, String nome) {
        File direct = new File(Environment.getExternalStorageDirectory() + "/PlugMusica" + dir);
        if (!direct.exists()) {
            direct.mkdirs();
        }
        if (new File(Environment.getExternalStorageDirectory() + "/PlugMusica" + dir + "/" + nome).exists()) {
            return false;
        }
        try {
            URL url = new URL(fileurl.replace(" ", "%20"));
            url.openConnection().connect();
            InputStream input = new BufferedInputStream(url.openStream(), 8192);
            OutputStream output = new FileOutputStream("/sdcard/PlugMusica" + dir + "/" + nome);
            byte[] data = new byte[1024];
            while (true) {
                int count = input.read(data);
                if (count == -1) {
                    break;
                }
                output.write(data, 0, count);
            }
            output.flush();
            output.close();
            input.close();
        } catch (Exception e) {
        }
        return true;
    }

    public void downloadFile(String fileurl, String dir, String nome, Context a) {
        String dir2 = dir + "";
        File direct = new File(Environment.getExternalStorageDirectory() + "/PlugMusica" + dir2);
        if (!direct.exists()) {
            direct.mkdirs();
        }
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(fileurl.replace(" ", "%20")));
        request.setAllowedNetworkTypes(3).setAllowedOverRoaming(false).setTitle("Baixando " + nome + "-" + dir2).setDescription("/sdcard/PlugMusica/" + dir2).setDestinationInExternalPublicDir("/PlugMusica" + dir2, nome);
        ((DownloadManager) a.getSystemService("download")).enqueue(request);
    }
}
