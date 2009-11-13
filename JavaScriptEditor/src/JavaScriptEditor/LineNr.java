/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaScriptEditor;

/**
 *
 * @author ddda
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.TabSet;
import javax.swing.text.TabStop;

/**
 * A class illustrating running line number count on JTextPane. Nothing
is painted on the pane itself,
 * but a separate JPanel handles painting the line numbers.<br>
 *
 * @author Daniel Sjöblom<br>
 * Created on Mar 3, 2004<br>
 * Copyright (c) 2004<br>
 * @version 1.0<br>
 */
public class LineNr extends JPanel {
    // for this simple experiment, we keep the pane + scrollpane as members.

    JTextPane txtSource;
    JScrollPane scrollPane;
    private int line_error = -1;

    public void setLine_error(int line_error) {
        this.line_error = line_error;
    }

    public LineNr() {
        super();
        setMinimumSize(new Dimension(30, 30));
        setPreferredSize(new Dimension(30, 30));
        setMinimumSize(new Dimension(30, 30));

        txtSource = new JTextPane() {

            @Override
            public void paint(Graphics g) {
                super.paint(g);
                LineNr.this.repaint();
            }
        };
        setTabs(txtSource, 4);
        txtSource.setFont(new Font("Monospaced", Font.PLAIN, 11));

        scrollPane = new JScrollPane(txtSource);
        txtSource.addKeyListener(new java.awt.event.KeyAdapter() {

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SyntaxMonitor.Instance().matchAll((DefaultStyledDocument) txtSource.getDocument());
                insertParenthesis(evt.getKeyChar());
            }
        });
    }

    private void insertParenthesis(char c) {
        if (c == '(') {
            try {
                txtSource.getDocument().insertString(txtSource.getCaretPosition(), ")", null);
                txtSource.setCaretPosition(txtSource.getCaretPosition() - 1);
            } catch (BadLocationException ex) {
                Logger.getLogger(LineNr.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (c == '{') {
            try {
                txtSource.getDocument().insertString(txtSource.getCaretPosition(), "\n\n}", null);
                txtSource.setCaretPosition(txtSource.getCaretPosition() - 1);
            } catch (BadLocationException ex) {
                Logger.getLogger(LineNr.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (c == '"') {
            try {
                txtSource.getDocument().insertString(txtSource.getCaretPosition(), "\"", null);
                txtSource.setCaretPosition(txtSource.getCaretPosition() - 1);
            } catch (BadLocationException ex) {
                Logger.getLogger(LineNr.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if (c == '[') {
            try {
                txtSource.getDocument().insertString(txtSource.getCaretPosition(), "]", null);
                txtSource.setCaretPosition(txtSource.getCaretPosition() - 1);
            } catch (BadLocationException ex) {
                Logger.getLogger(LineNr.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void setTabs(JTextPane textPane, int charactersPerTab) {
        FontMetrics fm = textPane.getFontMetrics(textPane.getFont());
        int charWidth = fm.charWidth('w');
        int tabWidth = charWidth * charactersPerTab;

        TabStop[] tabs = new TabStop[10];

        for (int j = 0; j < tabs.length; j++) {
            int tab = j + 1;
            tabs[j] = new TabStop(tab * tabWidth);
        }

        TabSet tabSet = new TabSet(tabs);
        SimpleAttributeSet attributes = new SimpleAttributeSet();
        StyleConstants.setTabSet(attributes, tabSet);
        int length = textPane.getDocument().getLength();
        textPane.getStyledDocument().setParagraphAttributes(0, length, attributes, true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int start =
                txtSource.viewToModel(scrollPane.getViewport().getViewPosition());
        int end =
                txtSource.viewToModel(new Point(
                scrollPane.getViewport().getViewPosition().x + txtSource.getWidth(),
                scrollPane.getViewport().getViewPosition().y + txtSource.getHeight()));

        DefaultStyledDocument doc = (DefaultStyledDocument) txtSource.getDocument();
        int startline = doc.getDefaultRootElement().getElementIndex(start) + 1;
        int endline = doc.getDefaultRootElement().getElementIndex(end) + 1;

        int fontHeight = g.getFontMetrics(txtSource.getFont()).getHeight();
        int fontDesc = g.getFontMetrics(txtSource.getFont()).getDescent();
        int starting_y = -1;

        try {
            starting_y = txtSource.modelToView(start).y - scrollPane.getViewport().getViewPosition().y + fontHeight - fontDesc;
        } catch (BadLocationException e1) {
            e1.printStackTrace();
        }

        for (int line = startline, y = starting_y; line <= endline; y += fontHeight, line++) {
            if (line_error > 0 && line_error == line) {
                g.setColor(Color.red);
            } else {
                g.setColor(Color.black);
            }
            g.drawString(Integer.toString(line), 0, y);
        }

    }
}
