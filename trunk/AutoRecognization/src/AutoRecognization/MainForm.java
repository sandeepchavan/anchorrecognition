/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MainForm.java
 *
 * Created on Oct 8, 2009, 9:25:16 AM
 */
package AutoRecognization;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.jai.PlanarImage;
import javax.media.jai.iterator.RandomIterFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import vinaorg.library.minulog.config.Configuration;
import vinaorg.library.minulog.entity.MinuLogException;
import vinaorg.library.minulog.exception.CorruptExceptionLog;
import vinaorg.library.minulog.exception.InvalidFolderException;

/**
 *
 * @author ddda
 */
public class MainForm extends javax.swing.JFrame {

    private JFileChooser chooser = new JFileChooser();
    private String imgpathglobal = "";
    private DefaultListModel dlm = new DefaultListModel();
    private TiffImageProcessing tiff = new TiffImageProcessing();
    private StringBuilder contentOCR = new StringBuilder();
    ActionListener actionListener = new ActionListener() {

        public void actionPerformed(ActionEvent actionEvent) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                try {
                    new MinuLogException(ex, this.getClass()).writeLog();
                } catch (CorruptExceptionLog ex1) {
                    System.out.println(ex1.toString());
                }
            }
            switch (cbxType.getSelectedIndex()) {
                case 0:
                    jspImageRecognition.getVerticalScrollBar().setValue(jspImageRecognition.getVerticalScrollBar().getMaximum());
                    jspImageRecognition.getHorizontalScrollBar().setValue(jspImageRecognition.getHorizontalScrollBar().getMaximum());
                    break;
                case 1:
                    jspImageRecognition.getVerticalScrollBar().setValue(jspImageRecognition.getVerticalScrollBar().getMaximum());
                    jspImageRecognition.getHorizontalScrollBar().setValue(0);
                    break;
            }

            jaiRecognitionctr.calculate(jaiRecognition.TypeNumer.valueOf(cbxType.getSelectedItem().toString()));
            recognized();
            if (chkAuto.isSelected() && lstImages.getSelectedIndex() >= dlm.getSize() - 1) {
                timer.stop();
            }
            if (chkAuto.isSelected()) {
                lstImages.setSelectedIndex(lstImages.getSelectedIndex() + 1);
                lstImages.ensureIndexIsVisible(lstImages.getSelectedIndex());
            } else {
                timer.stop();
            }
        }
    };
    javax.swing.Timer timer = new javax.swing.Timer(50, actionListener);

    /** Creates new form MainForm */
    public MainForm() {
        initComponents();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        lstImages.setModel(dlm);
        this.setLocationRelativeTo(null);
        try {
            Configuration.Instance().setLogInfopath("AutoRecognizationLog");
        } catch (InvalidFolderException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmdLoad = new javax.swing.JButton();
        cmdRecognize = new javax.swing.JButton();
        jspText = new javax.swing.JScrollPane();
        txtRecognization = new javax.swing.JTextArea();
        jspImageRecognition = new javax.swing.JScrollPane();
        jaiRecognitionctr = new AutoRecognization.jaiRecognition();
        jspImages = new javax.swing.JScrollPane();
        lstImages = new javax.swing.JList();
        cbxType = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        chkAuto = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cmdLoad.setText("Load");
        cmdLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdLoadActionPerformed(evt);
            }
        });

        cmdRecognize.setText("Recognize");
        cmdRecognize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdRecognizeActionPerformed(evt);
            }
        });

        txtRecognization.setColumns(20);
        txtRecognization.setFont(new java.awt.Font("Arial", 1, 18));
        txtRecognization.setLineWrap(true);
        txtRecognization.setRows(5);
        jspText.setViewportView(txtRecognization);

        javax.swing.GroupLayout jaiRecognitionctrLayout = new javax.swing.GroupLayout(jaiRecognitionctr);
        jaiRecognitionctr.setLayout(jaiRecognitionctrLayout);
        jaiRecognitionctrLayout.setHorizontalGroup(
            jaiRecognitionctrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 780, Short.MAX_VALUE)
        );
        jaiRecognitionctrLayout.setVerticalGroup(
            jaiRecognitionctrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 518, Short.MAX_VALUE)
        );

        jspImageRecognition.setViewportView(jaiRecognitionctr);

        lstImages.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstImagesValueChanged(evt);
            }
        });
        lstImages.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lstImagesKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lstImagesKeyReleased(evt);
            }
        });
        jspImages.setViewportView(lstImages);

        cbxType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "RightBottom", "LeftBottom", " " }));
        cbxType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxTypeItemStateChanged(evt);
            }
        });

        jButton1.setText("Get");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        chkAuto.setText("Auto");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jspImageRecognition, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 782, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                            .addComponent(cmdLoad, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                            .addComponent(cmdRecognize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jspText, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jspImages, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(chkAuto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxType, 0, 128, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jspImages, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                    .addComponent(jspText, 0, 81, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmdLoad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdRecognize)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbxType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkAuto)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspImageRecognition, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdLoadActionPerformed
        contentOCR = new StringBuilder();
        ChooseFileImage();
}//GEN-LAST:event_cmdLoadActionPerformed

    private void cmdRecognizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdRecognizeActionPerformed
        timer.start();

}//GEN-LAST:event_cmdRecognizeActionPerformed

    private void lstImagesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstImagesValueChanged
        if (lstImages.getSelectedValue() != null) {
            setImage(lstImages.getSelectedValue().toString());
            jaiRecognitionctr.calculate(jaiRecognition.TypeNumer.valueOf(cbxType.getSelectedItem().toString()));
            timer.start();
        }
}//GEN-LAST:event_lstImagesValueChanged

    private void lstImagesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lstImagesKeyReleased
    }//GEN-LAST:event_lstImagesKeyReleased

    private void lstImagesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lstImagesKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            lstImages.setSelectedIndex(lstImages.getSelectedIndex() + 1);
            lstImages.ensureIndexIsVisible(lstImages.getSelectedIndex());
        }
        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            cbxType.setSelectedIndex(1);
        }
        if (evt.getKeyCode() == KeyEvent.VK_F3) {
            cbxType.setSelectedIndex(0);
        }
    }//GEN-LAST:event_lstImagesKeyPressed

    private void cbxTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxTypeItemStateChanged
        jaiRecognitionctr.calculate(jaiRecognition.TypeNumer.valueOf(cbxType.getSelectedItem().toString()));
        timer.start();
    }//GEN-LAST:event_cbxTypeItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        txtRecognization.setText(contentOCR.toString());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void ChooseFileImage() {
        chooser.setCurrentDirectory(new File("D:\\EBook\\OCR\\Image\\new Project"));
        dlm.clear();
        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            imgpathglobal = chooser.getSelectedFile().getAbsolutePath();
            File folder = new File(imgpathglobal);
            for (File f : folder.listFiles()) {
                if (f.getAbsolutePath().endsWith(".tif")) {
                    dlm.addElement(f.getAbsolutePath());
                }
            }
            if (dlm.capacity() > 0) {
                lstImages.setSelectedIndex(0);
            }
        }
    }

    private void recognized() {

        String str = jaiRecognitionctr.RecognizeICRImage().get(0).replace("\n", "A");

        char[] carr = str.toCharArray();
        for (char c : carr) {
            if (!Character.isLetterOrDigit(c) && c != 'A') {
                str = str.replace(String.valueOf(c), "");
            }
        }

        String arr[] = str.split("A");

        for (String temp : arr) {
            if (temp.length() >= 17) {
                str = temp.replace(" ", "");
                break;
            }
        }
        str = str.replace("A", "");
        txtRecognization.setText(str);
        if (str.length() != 17) {
            txtRecognization.setForeground(Color.RED);
            timer.stop();
        } else {
            txtRecognization.setForeground(Color.BLACK);
            contentOCR.append(lstImages.getSelectedValue());
            contentOCR.append(";");
            contentOCR.append(str);
            contentOCR.append("\n");
        }
    }

    private void setImage(String filename) {
        PlanarImage image = null;
        this.repaint();
        try {
            try {
                image = tiff.readImage(filename, 0, 100, 0);
                RandomIterFactory.create(image, null);
            } catch (Exception ex) {
                System.out.println(ex);
            }
            jaiRecognitionctr.img = new ImageIcon(image.getAsBufferedImage()).getImage();
            jaiRecognitionctr.set(image);
            jaiRecognitionctr.repaint();
        } catch (Exception ex) {
            try {
                new MinuLogException(ex, this.getClass()).writeLog();
            } catch (CorruptExceptionLog ex1) {
                System.out.println(ex1.toString());
            }
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbxType;
    private javax.swing.JCheckBox chkAuto;
    private javax.swing.JButton cmdLoad;
    private javax.swing.JButton cmdRecognize;
    private javax.swing.JButton jButton1;
    private AutoRecognization.jaiRecognition jaiRecognitionctr;
    private javax.swing.JScrollPane jspImageRecognition;
    private javax.swing.JScrollPane jspImages;
    private javax.swing.JScrollPane jspText;
    private javax.swing.JList lstImages;
    private javax.swing.JTextArea txtRecognization;
    // End of variables declaration//GEN-END:variables
}
