/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AutoRecognization;

import java.sql.Date;


/**
 *
 * @author ddda
 */
public class DownloadChecking extends Thread implements Runnable {

    private String url;
    private String username;
    private String password;

    public DownloadChecking(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public void run() {
        while (true) {
            try {
                DownloadChecking.sleep(1000);
                Date d = new Date(new java.util.Date().getTime());
                Boolean ret = DataHandler.Instance(url, username, password).checkNewProducts(Date.valueOf("2009-11-06"));
                if (ret) {
                    MultiRun.Instance().setListFolderAuto(DataHandler.Instance(url, username, password).getNewProducts(Date.valueOf("2009-11-06")));
                }
            } catch (InterruptedException ex) {

            }
        }
    }
}
