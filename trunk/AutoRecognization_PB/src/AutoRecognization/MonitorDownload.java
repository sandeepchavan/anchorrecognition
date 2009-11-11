/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AutoRecognization;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ddda
 */
public class MonitorDownload extends Thread implements Runnable {

   /* private String url;
    private String username;
    private String password;
    private String hour;
    private String hourstartagain;
    private static MonitorDownload instance = null;

    public static MonitorDownload Instance(String url, String username, String password, String hour, String hourstartagain) {
        if (instance == null) {
            instance = new MonitorDownload(url, username, password, hour, hourstartagain);
        }
        return instance;
    }

    private MonitorDownload(String url, String username, String password, String hour, String hourstartagain) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.hour = hour;
        this.hourstartagain = hourstartagain;
        this.start();
    }

    @Override
    public void run() {
        while (true) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
            Date d = new Date();
            Date d2 = DownloadChecking.Instance(url, username, password, hour).getStarttime();
            if (d2 != null) {
                if (sdf.format(d2).equals(sdf.format(d))) {
                    SimpleDateFormat sdf2 = new SimpleDateFormat("H");
                    String hnow = sdf2.format(new java.util.Date());
                    if (hnow.equals(hourstartagain)) {
                        DownloadChecking.Instance(url, username, password, hour).setStart(true);
                    }
                }
            }
        }
    }*/
}
