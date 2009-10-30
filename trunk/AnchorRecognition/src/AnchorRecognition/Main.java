/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AnchorRecognition;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.neuroph.contrib.imgrec.ImageRecognitionPlugin;
import org.neuroph.core.NeuralNetwork;

/**
 *
 * @author ddda
 */
public class Main {

    /*public static void main(String[] args) {
        NeuralNetwork nn = NeuralNetwork.load("AnchorDB/AnchorDB.nnet");
        ImageRecognitionPlugin imageRecognition = (ImageRecognitionPlugin) nn.getPlugin(ImageRecognitionPlugin.IMG_REC_PLUGIN_NAME);
        HashMap<String, Double> output;
        try {
            output = imageRecognition.recognizeImage(new File("testimage/input3.jpg"));
            double max = 0.0d;
            String key = "";
            for (String freqOfWord : output.keySet()) {
                double val = output.get(freqOfWord);
                if (val > max) {
                    max = val;
                    key = freqOfWord;
                }
            }
            System.out.println(key + " :" + max);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }*/
}
