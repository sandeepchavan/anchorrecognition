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
import javax.media.jai.PlanarImage;
import javax.media.jai.iterator.RandomIterFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

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
    private StringBuilder failcontentOCR = new StringBuilder();
    ActionListener actionListener = new ActionListener() {

        public void actionPerformed(ActionEvent actionEvent) {
            jspImageRecognition.getVerticalScrollBar().setValue(jspImageRecognition.getVerticalScrollBar().getMaximum());
            jspImageRecognition.getHorizontalScrollBar().setValue(jspImageRecognition.getHorizontalScrollBar().getMaximum());
            matchForm();
            writeResult();
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
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
        }
        initComponents();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        lstImages.setModel(dlm);
        this.setLocationRelativeTo(null);
        String[] type = Configuration.Instance().readTemplete().split("\n");
        for (String str : type) {
            cbxType.addItem(str);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cmdLoad = new javax.swing.JButton();
        chkAuto = new javax.swing.JCheckBox();
        cmdRecognize = new javax.swing.JButton();
        jspText = new javax.swing.JScrollPane();
        txtRecognization = new javax.swing.JTextArea();
        jspImageRecognition = new javax.swing.JScrollPane();
        jaiRecognitionctr = new AutoRecognization.jaiRecognition();
        jspImages = new javax.swing.JScrollPane();
        lstImages = new javax.swing.JList();
        cbxType = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cmdLoad.setText("Load");
        cmdLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdLoadActionPerformed(evt);
            }
        });

        chkAuto.setText("Auto");

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jspImageRecognition, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 792, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                            .addComponent(cmdLoad, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                            .addComponent(cmdRecognize))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jspText, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jspImages, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxType, 0, 178, Short.MAX_VALUE)
                            .addComponent(chkAuto, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jspImages, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                    .addComponent(jspText, 0, 92, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cmdLoad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdRecognize)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbxType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkAuto)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspImageRecognition, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdLoadActionPerformed
        contentOCR = new StringBuilder();
        ChooseFileImage();
        cmdRecognize.setEnabled(true);
}//GEN-LAST:event_cmdLoadActionPerformed

    private void cmdRecognizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdRecognizeActionPerformed
        timer.start();
}//GEN-LAST:event_cmdRecognizeActionPerformed

    private void lstImagesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstImagesValueChanged
        if (lstImages.getSelectedValue() != null) {
            setImage(lstImages.getSelectedValue().toString());
            timer.start();
            if (lstImages.getSelectedIndex() == (dlm.getSize() - 1)) {
                chkAuto.setSelected(false);
            }
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
        /*if (dlm.getSize() > 0) {
        jaiRecognitionctr.calculate(cbxType.getSelectedItem().toString());
        timer.start();
        }*/
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

    private void matchForm() {
        File focr = new File(lstImages.getSelectedValue().toString());
        String strpath = focr.getParentFile().getParentFile().getName() + "/" + focr.getParentFile().getName() + "/" + focr.getName();
        String ret = "";
        for (int i = 0; i < cbxType.getItemCount(); i++) {
            jaiRecognitionctr.calculate(cbxType.getItemAt(i).toString());
            ret = getOCRforMatching();
            if (ret.length() == 17) {
                txtRecognization.setForeground(Color.BLACK);
                contentOCR.append(strpath);
                contentOCR.append(";");
                contentOCR.append(ret);
                contentOCR.append("\n");
                txtRecognization.setText(ret);
                return;
            }
        }
        txtRecognization.setForeground(Color.RED);
        txtRecognization.setText(ret);
        failcontentOCR.append(strpath);
        failcontentOCR.append(";");
        failcontentOCR.append(ret);
        failcontentOCR.append("\n");
    }

    private void writeResult() {
        if (lstImages.getSelectedIndex() == (dlm.getSize() - 1)) {
            File focr = new File(lstImages.getSelectedValue().toString());
            Configuration.Instance().writeFailResult(focr.getParentFile().getParentFile().getName(), failcontentOCR.toString());
            failcontentOCR = new StringBuilder("");
            Configuration.Instance().writeCorrectResult(focr.getParentFile().getParentFile().getName(), contentOCR.toString());
            contentOCR = new StringBuilder("");
        }
    }

    private String getOCRforMatching() {
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
        return str;
    }

    /* private void recognized() {
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
    File focr = new File(lstImages.getSelectedValue().toString());
    String strpath = focr.getParentFile().getParentFile().getName() + "/" + focr.getParentFile().getName() + "/" + focr.getName();
    if (str.length() != 17) {
    String retval = matchForm();
    if (retval.length() != 17) {
    txtRecognization.setForeground(Color.RED);
    failcontentOCR.append(strpath);
    failcontentOCR.append(";");
    failcontentOCR.append(str);
    failcontentOCR.append("\n");
    } else {
    txtRecognization.setText(retval);
    txtRecognization.setForeground(Color.BLACK);
    contentOCR.append(strpath);
    contentOCR.append(";");
    contentOCR.append(str);
    contentOCR.append("\n");
    }
    } else {
    txtRecognization.setForeground(Color.BLACK);
    contentOCR.append(strpath);
    contentOCR.append(";");
    contentOCR.append(str);
    contentOCR.append("\n");
    }
    if (lstImages.getSelectedIndex() == (dlm.getSize() - 1)) {
    Configuration.Instance().writeFailResult(focr.getParentFile().getParentFile().getName(), failcontentOCR.toString());
    failcontentOCR = new StringBuilder("");
    Configuration.Instance().writeCorrectResult(focr.getParentFile().getParentFile().getName(), contentOCR.toString());
    contentOCR = new StringBuilder("");
    }
    }*/
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
    private javax.swing.JPanel jPanel1;
    private AutoRecognization.jaiRecognition jaiRecognitionctr;
    private javax.swing.JScrollPane jspImageRecognition;
    private javax.swing.JScrollPane jspImages;
    private javax.swing.JScrollPane jspText;
    private javax.swing.JList lstImages;
    private javax.swing.JTextArea txtRecognization;
    // End of variables declaration//GEN-END:variables
}
