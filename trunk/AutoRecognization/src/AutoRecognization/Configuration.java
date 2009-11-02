/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AutoRecognization;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Mr.Da
 */
public class Configuration {

    private static Configuration instance = null;

    public static Configuration Instance() {
        if (instance == null) {
            instance = new Configuration();
        }
        return instance;
    }

    public void writeConfig(String value) {
        try {
            Writer output = null;
            File file = new File("Config.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            output = new BufferedWriter(new FileWriter(file));
            output.write(value);
            output.close();
        } catch (IOException ex) {
        }
    }

    public void writeFailResult(String name, String value) {
        try {
            Writer output = null;
            File file = new File("FailRecognition_" + name + ".txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            output = new BufferedWriter(new FileWriter(file));
            output.write(value);
            output.close();
        } catch (IOException ex) {
        }
    }

    public void writeCorrectResult(String name, String value) {
        try {
            Writer output = null;
            File file = new File("CorrectRecognition_" + name + ".txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            output = new BufferedWriter(new FileWriter(file));
            output.write(value);
            output.close();
        } catch (IOException ex) {
        }
    }

    public String readConfig() {
        String ret = "";
        try {
            BufferedReader input = null;
            File file = new File("Config.txt");
            if (file.exists()) {
                input = new BufferedReader(new FileReader(file));
                ret = input.readLine();
                input.close();
            }
        } catch (IOException ex) {
        } finally {
            return ret;
        }
    }

    public String readTemplete() {
        String ret = "";
        try {
            BufferedReader input = null;
            StringBuilder contents = new StringBuilder();
            File file = new File("FormTemplete.txt");
            if (file.exists()) {
                input = new BufferedReader(new FileReader(file));
                while ((ret = input.readLine()) != null) {
                    contents.append(ret);
                    contents.append(System.getProperty("line.separator"));
                }
                ret = contents.toString();
                input.close();
            }
        } catch (IOException ex) {
        } finally {
            return ret;
        }
    }
}
