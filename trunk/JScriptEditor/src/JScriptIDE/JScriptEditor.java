/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JScriptEditor.java
 *
 * Created on Nov 13, 2009, 1:27:55 PM
 */
package JScriptIDE;

import java.awt.BorderLayout;

/**
 *
 * @author ddda
 */
public class JScriptEditor extends javax.swing.JPanel {

    private LineNr linenr = new LineNr();
    private SyntaxError syne = new SyntaxError();

    /** Creates new form JScriptEditor */
    public JScriptEditor() {
        initComponents();
        pnlSource.add(linenr, BorderLayout.WEST);
        pnlSource.add(linenr.scrollPane, BorderLayout.CENTER);
    }

    /**
     * Get source code java script
     * @return Source code
     */
    public String getSource() {
        linenr.txtSource.selectAll();
        return linenr.txtSource.getSelectedText();
    }

    /**
     * compile source code java script
     * @return SyntaxError Object which contains error lines and error messages
     */
    public SyntaxError compileSource() {
        syne.compileCode(getSource());
        linenr.setLine_error(syne.getLine_number_error());
        linenr.repaint();
        return syne;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlSource = new javax.swing.JPanel();

        pnlSource.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlSource.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlSource, javax.swing.GroupLayout.DEFAULT_SIZE, 786, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlSource, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnlSource;
    // End of variables declaration//GEN-END:variables
}
