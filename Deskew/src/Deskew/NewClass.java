/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Deskew;

import javax.media.jai.JAI;
import javax.media.jai.KernelJAI;
import javax.media.jai.PlanarImage;
import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

/**
 *
 * @author Mr.Da
 */
public class NewClass {

    public static void main(String[] args) {
        // We need one argument - the image file name.
        // Create a constant array with the Sobel horizontal kernel.
        float[] kernelMatrix = {1, 0, -1,
            2, 0, -2,
            1, 0, -1};
        // Read the image.
        PlanarImage input = JAI.create("fileload", "EdgeDetection.jpg");
        // Create the kernel using the array.
        KernelJAI kernel = new KernelJAI(3, 3, kernelMatrix);
        // Run the convolve operator, creating the processed image.
        PlanarImage output = JAI.create("convolve", input, kernel);
        // Create a JFrame for displaying the results.
        JFrame frame = new JFrame("Sobel horizontal border of the image ");
        // Add to the JFrame's ContentPane an instance of
        // DisplayTwoSynchronizedImages, which will contain the original and
        // processed image.
        NewJPanel njp = new NewJPanel();
        njp.set(output);
        frame.getContentPane().add(new JScrollPane(njp));
        // Set the closing operation so the application is finished.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack(); // Adjust the frame size using preferred dimensions.
        frame.setVisible(true); // Show the frame.
    }
}
