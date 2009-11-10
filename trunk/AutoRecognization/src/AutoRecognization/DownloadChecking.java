/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AutoRecognization;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author ddda
 */
public class DownloadChecking extends Thread implements Runnable {

    private String url;
    private String username;
    private String password;
    private String hour;
    private java.util.Date starttime = null;
    private static DownloadChecking instance = null;
    private Boolean start = true;

    public Boolean isStart() {
        return start;
    }

    public void setStart(Boolean start) {
        this.start = start;
    }

    public static DownloadChecking Instance(String url, String username, String password, String hour) {
        if (instance == null) {
            instance = new DownloadChecking(url, username, password, hour);
        }
        return instance;
    }

    private DownloadChecking(String url, String username, String password, String hour) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.hour = hour;
    }

    public java.util.Date getStarttime() {
        return starttime;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (isStart()) {
                    DownloadChecking.sleep(1000);
                    Date d = new Date(new java.util.Date().getTime());
                    starttime = new java.util.Date();
                    MultiRunAutomatic.Instance().setStatus("Start at...: " + starttime.toString());
                    SimpleDateFormat sdf = new SimpleDateFormat("H");
                    String strdate = sdf.format(d);
                    Calendar cld = Calendar.getInstance();
                    cld.setTime(d);
                    cld.add(Calendar.DAY_OF_MONTH, -1);
                    Date d2 = new Date(cld.getTime().getTime());
                    if (strdate.equals(hour) && isStart()) {
                        Boolean ret = DataHandler.Instance(url, username, password).checkNewProducts(d);
                        Boolean ret2 = DataHandler.Instance(url, username, password).checkNewProducts(d2);
                        if (ret || ret2) {
                            MultiRunAutomatic.Instance().setListFolderAuto(DataHandler.Instance(url, username, password).getNewProducts(Date.valueOf("2009-11-06")));
                            MultiRunAutomatic.Instance().setStatus("Stop at...: " + new java.util.Date().toString());
                            setStart(false);
                        }
                    }
                }
            } catch (InterruptedException ex) {
            }
        }
    }
}
