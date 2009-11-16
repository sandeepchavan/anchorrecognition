/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JSEditor.java
 *
 * Created on Nov 11, 2009, 3:11:25 PM
 */
package JavaScriptEditor;

import JScriptIDE.SyntaxError;
import javax.swing.UIManager;

/**
 *
 * @author ddda
 */
public class JSEditor extends javax.swing.JFrame {

    private static JSEditor instance = null;

    public static JSEditor InStance() {
        if (instance == null) {
            instance = new JSEditor();
        }
        return instance;
    }

    /** Creates new form JSEditor */
    private JSEditor() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
        }
        initComponents();
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        pnlControl = new javax.swing.JPanel();
        cmdCompile = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        pnlError = new javax.swing.JPanel();
        scrError = new javax.swing.JScrollPane();
        txtError = new javax.swing.JTextArea();
        tabMain = new javax.swing.JTabbedPane();
        jseJavaScript = new JScriptIDE.JScriptEditor();
        jScriptEditor1 = new JScriptIDE.JScriptEditor();
        tbMain = new javax.swing.JToolBar();
        lblLineCaption = new javax.swing.JLabel();
        lblLine = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JS Editor");

        pnlControl.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cmdCompile.setText("Compile");
        cmdCompile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCompileActionPerformed(evt);
            }
        });

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlControlLayout = new javax.swing.GroupLayout(pnlControl);
        pnlControl.setLayout(pnlControlLayout);
        pnlControlLayout.setHorizontalGroup(
            pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlControlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmdCompile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(794, Short.MAX_VALUE))
        );
        pnlControlLayout.setVerticalGroup(
            pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlControlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdCompile)
                    .addComponent(jButton1))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pnlError.setBorder(javax.swing.BorderFactory.createTitledBorder("Error"));

        txtError.setColumns(20);
        txtError.setEditable(false);
        txtError.setLineWrap(true);
        txtError.setRows(5);
        scrError.setViewportView(txtError);

        javax.swing.GroupLayout pnlErrorLayout = new javax.swing.GroupLayout(pnlError);
        pnlError.setLayout(pnlErrorLayout);
        pnlErrorLayout.setHorizontalGroup(
            pnlErrorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlErrorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrError, javax.swing.GroupLayout.DEFAULT_SIZE, 914, Short.MAX_VALUE))
        );
        pnlErrorLayout.setVerticalGroup(
            pnlErrorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrError, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
        );

        jseJavaScript.addLineClickListener(new JScriptIDE.LineClickListener() {
            public void LineClickPerformed(JScriptIDE.LineClickEvent evt) {
                jseJavaScriptLineClickPerformed(evt);
            }
        });
        tabMain.addTab("tab1", jseJavaScript);
        tabMain.addTab("tab2", jScriptEditor1);

        tbMain.setRollover(true);

        lblLineCaption.setText("Line: ");
        tbMain.add(lblLineCaption);

        lblLine.setText("0");
        tbMain.add(lblLine);

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tabMain, javax.swing.GroupLayout.DEFAULT_SIZE, 940, Short.MAX_VALUE)
                    .addComponent(pnlControl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tbMain, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 940, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabMain, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlError, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbMain, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdCompileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCompileActionPerformed
        SyntaxError se = jseJavaScript.compileSource();
        txtError.setText(se.getMessage());
    }//GEN-LAST:event_cmdCompileActionPerformed

    private void jseJavaScriptLineClickPerformed(JScriptIDE.LineClickEvent evt) {//GEN-FIRST:event_jseJavaScriptLineClickPerformed
        lblLine.setText(String.valueOf(evt.getLine_clicking()));
    }//GEN-LAST:event_jseJavaScriptLineClickPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jseJavaScript.saveSource("source.js");
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                InStance().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdCompile;
    private javax.swing.JButton jButton1;
    private JScriptIDE.JScriptEditor jScriptEditor1;
    private JScriptIDE.JScriptEditor jseJavaScript;
    private javax.swing.JLabel lblLine;
    private javax.swing.JLabel lblLineCaption;
    private javax.swing.JPanel pnlControl;
    private javax.swing.JPanel pnlError;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JScrollPane scrError;
    private javax.swing.JTabbedPane tabMain;
    private javax.swing.JToolBar tbMain;
    private javax.swing.JTextArea txtError;
    // End of variables declaration//GEN-END:variables
}
