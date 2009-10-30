/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ImageDisplayer.java
 *
 * Created on Oct 30, 2009, 2:11:41 PM
 */
package AnchorRecognition;

import com.sun.media.jai.widget.DisplayJAI;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author ddda
 */
public class ImageDisplayer extends DisplayJAI {

    private Point OldPoint = new Point(0, 0);
    private Point NewPoint = new Point(0, 0);
    private boolean completeDrag = false;
    private Rectangle rec;
    private String config = "";

    public String getConfig() {
        return config;
    }

    public Rectangle getRec() {
        return rec;
    }

    /** Creates new form ImageDisplayer */
    public ImageDisplayer() {
        initComponents();
    }

    private void createRectangle(int x, int y, int width, int height) {
        rec = new Rectangle();
        rec.x = x;
        rec.y = y;
        rec.width = width;
        rec.height = height;
    }

    @Override
    public synchronized void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (completeDrag) {
            g.setColor(new Color(255, 0, 0, 128));
            g.fillRect(rec.x, rec.y, rec.width, rec.height);
            int relateX = this.getWidth() - rec.x + rec.width;
            int relateY = this.getHeight() - rec.y + rec.height;
            StringBuilder sb = new StringBuilder();
            sb.append(rec.width);
            sb.append(", ");
            sb.append(rec.height);
            sb.append(", ");
            sb.append(relateX);
            sb.append(", ");
            sb.append(relateY);
            config=sb.toString();
            AnchorRecognitionForm.Instance().setTextAxis("Axis: " + sb.toString());
        } else {
            g.setColor(new Color(255, 0, 0, 128));
            if (NewPoint.x > OldPoint.x && NewPoint.y > OldPoint.y) {
                g.fillRect(OldPoint.x, OldPoint.y, Math.abs(NewPoint.x - OldPoint.x), Math.abs(NewPoint.y - OldPoint.y));
            } else if (NewPoint.x < OldPoint.x && NewPoint.y > OldPoint.y) {
                g.fillRect(NewPoint.x, OldPoint.y, Math.abs(NewPoint.x - OldPoint.x), Math.abs(NewPoint.y - OldPoint.y));
            } else if (NewPoint.x > OldPoint.x && NewPoint.y < OldPoint.y) {
                g.fillRect(OldPoint.x, NewPoint.y, Math.abs(NewPoint.x - OldPoint.x), Math.abs(NewPoint.y - OldPoint.y));
            } else {
                g.fillRect(NewPoint.x, NewPoint.y, Math.abs(NewPoint.x - OldPoint.x), Math.abs(NewPoint.y - OldPoint.y));
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        OldPoint.x = evt.getX();
        OldPoint.y = evt.getY();
        completeDrag = false;
    }//GEN-LAST:event_formMousePressed

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        NewPoint.x = evt.getX();
        NewPoint.y = evt.getY();
        createRectangle(OldPoint.x, OldPoint.y, Math.abs(NewPoint.x - OldPoint.x), Math.abs(NewPoint.y - OldPoint.y));
        completeDrag = true;
        repaint();
    }//GEN-LAST:event_formMouseReleased

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        NewPoint.x = evt.getX();
        NewPoint.y = evt.getY();
        completeDrag = false;
        repaint();
    }//GEN-LAST:event_formMouseDragged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
