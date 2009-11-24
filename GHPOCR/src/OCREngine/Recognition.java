/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OCREngine;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author ddda
 */
public class Recognition {

    private ArrayList<Rectangle> reclist = new ArrayList<Rectangle>();
    private ArrayList<String> templist = new ArrayList<String>();
    private ArrayList<String> resultlist = new ArrayList<String>();
    private Image img;
    private int slideX = 0;
    private int slideY = 0;
    private Languages language = Languages.ENGLISH;
    private final String LANG_OPTION = "-l";
    private final String EOL = System.getProperty("line.separator");
    private final String PATHOCRCORE = "C:/OCRCore/OCRApp/";

    public void setImg(Image img) {
        this.img = img;
    }

    public boolean setReclist(ArrayList<Rectangle> reclist) {
        this.resultlist.clear();
        this.reclist = reclist;
        if (!splitImages(false)) {
            return false;
        }
        return true;
    }

    /**
     * @return the language
     */
    public Languages getLanguage() {
        return language;
    }

    /**
     * @param language the language to set
     */
    public void setLanguage(Languages language) {
        this.language = language;
    }

    public enum Languages {

        ENGLISH, GERMAN, FRENCH
    }

    /**
     * Hàm này phải được gọi trước tiên để load hình cần nhận dạng.</br>
     * Hàm trả về TRUE nếu load hình thành công</br>
     * Hàm trả về False nếu load hình thất bại</br>
     * @param imgPath Đường dẫn hình
     * @return boolean
     */
    public boolean LoadImage(String imgPath) {
        this.reclist.clear();
        this.templist.clear();
        if (!writeFile(PATHOCRCORE + "path.txt", imgPath)) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param imgPath Đường dẫn hình
     * @param recl Danh sách các rectangle cần cắt
     * @return boolean true nếu thành công
     */
    public boolean LoadImage(String imgPath, ArrayList<Rectangle> recl) {
        this.resultlist.clear();
        this.reclist = recl;
        int dotPos = imgPath.lastIndexOf(".");
        String extension = imgPath.substring(dotPos);
        if (extension.equals(".tif")) {
            img = readImage(imgPath, "tif");
        } else if (extension.equals(".bmp")) {
            img = readImage(imgPath, "bmp");
        } else { // For other image
            img = new ImageIcon(imgPath).getImage();
        }
        if (!splitImages(false)) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param imgtemp Đối tượng Image
     * @param recl Danh sách các rectangle cần cắt
     * @return boolean true nếu thành công
     */
    public boolean LoadImage(Image imgtemp, ArrayList<Rectangle> recl) {
        this.resultlist.clear();
        this.reclist = recl;
        img = imgtemp;
        if (!splitImages(false)) {
            return false;
        }
        return true;
    }

    public boolean LoadImageICR(Image imgtemp, ArrayList<Rectangle> recl) {
        this.resultlist.clear();
        this.reclist = recl;
        img = imgtemp;
        if (!splitImages(true)) {
            return false;
        }
        return true;
    }

    private boolean writeFile(String path, String value) {
        try {
            File f = new File(path);
            if (!f.exists()) {
                f.createNewFile();
            }
            FileWriter fis = new FileWriter(path);
            File imgf = new File(value);
            String tempstr = "";
            switch (language) {
                case ENGLISH:
                    tempstr = "0" + imgf.getAbsolutePath();
                    break;
                case GERMAN:
                    tempstr = "1" + imgf.getAbsolutePath();
                    break;
                case FRENCH:
                    tempstr = "2" + imgf.getAbsolutePath();
                    break;
                default:
                    tempstr = "0" + imgf.getAbsolutePath();
                    break;
            }
            fis.write(tempstr);
            fis.close();
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    private Image readImage(String path, String ext) {
        try {
            ImageReader reader;
            ImageInputStream iis;
            if (ext.equals("tif")) {
                reader = ImageIO.getImageReadersByFormatName("tiff").next();
            } else {
                reader = ImageIO.getImageReadersByFormatName("bmp").next();
            }
            iis = ImageIO.createImageInputStream(new File(path));
            reader.setInput(iis, false);
            Image imgs = new ImageIcon(reader.read(0)).getImage();
            return imgs;
        } catch (Exception ex) {
            return null;
        }
    }

    private boolean splitImages(boolean handwritting) {
        try {
            JFrame tmpjframe = new JFrame();
            for (Rectangle rec : reclist) {
                BufferedImage bi = new BufferedImage(rec.width, rec.height, BufferedImage.TYPE_INT_RGB);
                Graphics2D g2d = bi.createGraphics();
                g2d.setBackground(Color.WHITE);
                g2d.setColor(Color.BLACK);
                g2d.drawImage(img, 0, 0, rec.width, rec.height, rec.x - slideX, rec.y - slideY, rec.x + rec.width - slideX, rec.y + rec.height - slideY, Color.WHITE, tmpjframe);
                try {
                    File f;
                    if (handwritting == false) {
                        f = new File(PATHOCRCORE + "recog" + reclist.indexOf(rec) + ".tif");
                        ImageIO.write(bi, "TIFF", f);
                    } else {
                        f = new File(PATHOCRCORE + "recog" + reclist.indexOf(rec) + ".pnm");
                        ImageIO.write(bi, "pnm", f);
                    }
                    templist.add(f.getAbsolutePath());
                } catch (IOException ex) {
                    return false;
                }
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Hàm này được gọi ngay sau hàm LoadImage </br>
     * Khi hàm này được gọi tiến trình nhận dạng bắt đầu </br>
     * Hàm trả về true nếu nhận dạng thành công. </br>
     * Dữ liệu nhận dạng sẽ được lấy ra bởi hàm <b>getResultRecognition</b>
     * @return boolean
     */
    public boolean callOCREngine() {
        try {
            if (this.reclist.size() == 0) {
                Process p = Runtime.getRuntime().exec(PATHOCRCORE + "Test.exe");
                p.waitFor();
                p.destroy();
                return true;
            }
            for (String str : templist) {
                if (!writeFile(PATHOCRCORE + "path.txt", str)) {
                    return false;
                }
                Process p = Runtime.getRuntime().exec(PATHOCRCORE + "Test.exe");
                p.waitFor();
                p.destroy();
                resultlist.add(ReadClipboard());
                File f = new File(str);
                f.delete();
            }
            return true;
        } catch (Exception ex) {
            return false;
        } finally {
            this.templist.clear();
        }
    }

    public boolean callICREngine() {
        try {
            for (String str : templist) {
                if (!writeFile(PATHOCRCORE + "path.txt", str)) {
                    return false;
                }

                resultlist.add(recognizeHandWrittingNumber());
                File f = new File(str);
                f.delete();
            }
            return true;
        } catch (Exception ex) {
            return false;
        } finally {
            this.templist.clear();
        }
    }

    private String ReadClipboard() {
        Transferable t = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
        try {
            if (t != null && t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                String text = (String) t.getTransferData(DataFlavor.stringFlavor);
                if (text.trim().equals("")) {
                    text = recognizeText();
                }
                return text.trim();
            }
        } catch (Exception e) {
            return "";
        }
        return "";
    }

    /**
     * @return the ResultRecognition
     */
    public String getResultRecognition() {
        return ReadClipboard();
    }

    /**
     *
     * @return Nếu hàm load image đưa vào một mảng các Rectangle thì hàm
     * <b>getAllResultRecognition</b> trs3 về một mảng các String tương ứng
     */
    public ArrayList<String> getAllResultRecognition() {
        return resultlist;
    }

    /**
     * @return the slideX
     */
    public int getSlideX() {
        return slideX;
    }

    /**
     * @param slideX the slideX to set
     */
    public void setSlideX(int slideX) {
        this.slideX = slideX;
    }

    /**
     * @return the slideY
     */
    public int getSlideY() {
        return slideY;
    }

    /**
     * @param slideY the slideY to set
     */
    public void setSlideY(int slideY) {
        this.slideY = slideY;
    }

    private String recognizeText() throws Exception {

        File outputFile = new File(PATHOCRCORE + "temp");
        StringBuffer strB = new StringBuffer();

        java.util.List<String> cmd = new ArrayList<String>();
        cmd.add(PATHOCRCORE + "tesseract/tesseract");
        cmd.add(""); // placeholder for inputfile
        cmd.add(outputFile.getAbsolutePath());
        cmd.add(LANG_OPTION);
        cmd.add("eng");

        File sourcefile = new File(PATHOCRCORE + "path.txt");
        FileReader fw = new FileReader(sourcefile);
        BufferedReader br = new BufferedReader(fw);
        String res = br.readLine();
        br.close();
        fw.close();

        ProcessBuilder pb = new ProcessBuilder();
        cmd.set(1, res.substring(1));
        pb.command(cmd);
        Process process = pb.start();
        int w = process.waitFor();
        if (w == 0) {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(PATHOCRCORE + "temp.txt"), "UTF-8"));
            String str;
            while ((str = in.readLine()) != null) {
                strB.append(str).append(EOL);
            }
            in.close();
        }
        new File(PATHOCRCORE + "temp.txt").delete();
        new File(PATHOCRCORE + "tesseract.log").delete();
        return strB.toString();
    }

    private String recognizeHandWrittingNumber() throws Exception {

        File outputFile = new File(PATHOCRCORE + "temp.txt");
        StringBuffer strB = new StringBuffer();

        java.util.List<String> cmd = new ArrayList<String>();
        cmd.add(PATHOCRCORE + "GOCR/gocr");
        cmd.add("-C");
        cmd.add("0-9");
        cmd.add("-m");
        cmd.add("2");
        cmd.add("-p");
        cmd.add(PATHOCRCORE + "GOCR/database/");
        cmd.add("-i");
        cmd.add("");
        cmd.add("-o");
        cmd.add(outputFile.getAbsolutePath());

        File sourcefile = new File(PATHOCRCORE + "path.txt");
        FileReader fw = new FileReader(sourcefile);
        BufferedReader br = new BufferedReader(fw);
        String res = br.readLine();
        br.close();
        fw.close();

        ProcessBuilder pb = new ProcessBuilder();
        cmd.set(8, res.substring(1));
        pb.command(cmd);
        Process process = pb.start();
        int w = process.waitFor();
        if (w == 0) {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(PATHOCRCORE + "temp.txt"), "UTF-8"));
            String str;
            while ((str = in.readLine()) != null) {
                strB.append(str).append(EOL);
            }
            in.close();
        }
        new File(PATHOCRCORE + "temp.txt").delete();
        return strB.toString();
    }
}
