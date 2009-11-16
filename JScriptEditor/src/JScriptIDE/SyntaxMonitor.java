/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JScriptIDE;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 *
 * @author ddda
 */
public class SyntaxMonitor {

    private static final String keywords = "\\babstract\\W|\\bcontinue\\W|\\bfor\\W|\\bnew\\W|" +
            "\\bswitch\\W|\\bassert\\W|\\bdefault\\W|goto\\W|\\bpackage\\W|\\bfunction\\W|" +
            "\\bsynchronized\\W|\\bboolean\\W|\\bdo\\W|\\bif\\W|\\bprivate\\W|\\bthis\\W|\\bbreak\\W|\\bdouble\\W|" +
            "\\bimplements\\W|\\bprotected\\W|\\bthrow\\W|\\bbyte\\W|\\belse\\W|\\bimport\\W|" +
            "\\bpublic\\W|\\bthrows\\W|\\bcase\\W|\\benum\\W|\\binstanceof\\W|\\breturn\\W|\\btransient\\W|\\bcatch\\W|" +
            "\\bextends\\W|\\bint\\W|\\bshort\\W|\\btry\\W|\\bchar\\W|\\bfinal\\W|\\bprint\\W|\\bprintln\\W|" +
            "\\binterface\\W|\\bstatic\\W|\\bvoid\\W|\\bclass\\W|\\bfinally\\W|\\blong\\W|\\bvolatile\\W|\\bconst\\W|\\bfloat\\W|" +
            "\\bnative\\W|\\bsuper\\W|\\bwhile\\W|\\bvar\\W";
    private static final String parenthesis = "\\(|\\)|\\[|\\]|\\{|\\}";
    private static final String words = "\\b\\w+|\\W+";
    private static final String variable = "\\s*?var\\s+\\w+";
    private static final String astring = "\"[\\s*?\\w*?\\s*?]+\"";
    private static final String java_swing = "\\b\\bJApplet\\W|\\bJButton\\W|\\bJCheckBox\\W|\\bJCheckBoxMenuItem\\W|" +
            "\\bJColorChooser\\W|\\bJComboBox\\W|\\bJComponent\\W|\\bJDesktopPane\\W|\\bJDialog\\W|\\bJEditorPane\\W|" +
            "\\bJFileChooser\\W|\\bJFormattedTextField\\W|\\bJFrame\\W|\\bJInternalFrame\\W|\\bJInternalFrame\\W|" +
            "\\bJLabel\\W|\\bJLayeredPane\\W|\\bJList\\W|\\bJMenu\\W|\\bJMenuBar\\W|\\bJMenuItem\\W|\\bJOptionPane\\W|" +
            "\\bJPanel\\W|\\bJPasswordField\\W|\\bJPopupMenu\\W|\\bJProgressBar\\W|\\bJRadioButton\\W|\\bJRadioButtonMenuItem\\W|" +
            "\\bJRootPane\\W|\\bJScrollBar\\W|\\bJScrollPane\\W|\\bJSeparator\\W|\\bJSlider\\W|\\bJSpinner\\W|\\bJSplitPane\\W|" +
            "\\bJTabbedPane\\W|\\bJTable\\W|\\bJTextArea\\W|\\bJTextField\\W|\\bJTextPane\\W|\\bJToggleButton\\W|\\bJToolBar\\W|" +
            "\\bJToolTip\\W|\\bJTree\\W|\\bJViewport\\W|\\bJWindow\\W\\b";
    private static final String comments = "//+[\\w+\\s*?\\p{L}*?()`~!@#$%\\^&*\\[\\]\"';:<>?/\\\\,.-]+\\b";
    private static SyntaxMonitor instance = null;

    protected static SyntaxMonitor Instance() {
        if (instance == null) {
            instance = new SyntaxMonitor();
        }
        return instance;
    }

    private SyntaxMonitor() {
    }

    protected void matchAll(DefaultStyledDocument dsd) {
        matchWord(dsd);
        matchJavaSwing(dsd);
        matchParenthesis(dsd);
        matchString(dsd);
        matchKeyWord(dsd);
        matchComment(dsd);
    }

    private void matchKeyWord(DefaultStyledDocument dsd) {
        Pattern p = null;
        SimpleAttributeSet attr = new SimpleAttributeSet();
        String source = "";
        Matcher matcher = null;
        try {
            p = Pattern.compile(keywords);
            source = dsd.getText(0, dsd.getLength());
            matcher = p.matcher(source);
            while (matcher.find()) {
                attr.addAttribute(StyleConstants.Bold, new Boolean(true));
                StyleConstants.setForeground(attr, Color.blue);
                dsd.setCharacterAttributes(matcher.start(), matcher.group().length(), attr, false);
            }
        } catch (BadLocationException ex) {
            Logger.getLogger(SyntaxMonitor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void matchJavaSwing(DefaultStyledDocument dsd) {
        Pattern p = null;
        SimpleAttributeSet attr = new SimpleAttributeSet();
        String source = "";
        Matcher matcher = null;
        try {
            p = Pattern.compile(java_swing);
            source = dsd.getText(0, dsd.getLength());
            matcher = p.matcher(source);
            while (matcher.find()) {
                attr.addAttribute(StyleConstants.Bold, new Boolean(true));
                StyleConstants.setForeground(attr, new java.awt.Color(0xC0, 0xC0, 0x00));
                dsd.setCharacterAttributes(matcher.start(), matcher.group().length(), attr, false);
            }
        } catch (BadLocationException ex) {
            Logger.getLogger(SyntaxMonitor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void matchComment(DefaultStyledDocument dsd) {
        Pattern p = null;
        SimpleAttributeSet attr = new SimpleAttributeSet();
        String source = "";
        Matcher matcher = null;
        try {
            p = Pattern.compile(comments);
            source = dsd.getText(0, dsd.getLength());
            matcher = p.matcher(source);
            while (matcher.find()) {
                attr.addAttribute(StyleConstants.Bold, new Boolean(false));
                StyleConstants.setForeground(attr, Color.lightGray);
                dsd.setCharacterAttributes(matcher.start(), matcher.group().length(), attr, false);
            }
        } catch (BadLocationException ex) {
            Logger.getLogger(SyntaxMonitor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void matchParenthesis(DefaultStyledDocument dsd) {
        Pattern p = null;
        SimpleAttributeSet attr = new SimpleAttributeSet();
        String source = "";
        Matcher matcher = null;
        try {
            p = Pattern.compile(parenthesis);
            source = dsd.getText(0, dsd.getLength());
            matcher = p.matcher(source);
            while (matcher.find()) {
                attr.addAttribute(StyleConstants.Bold, new Boolean(false));
                StyleConstants.setForeground(attr, Color.red);
                dsd.setCharacterAttributes(matcher.start(), matcher.group().length(), attr, false);
            }
        } catch (BadLocationException ex) {
            Logger.getLogger(SyntaxMonitor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void matchWord(DefaultStyledDocument dsd) {
        Pattern p = null;
        SimpleAttributeSet attr = new SimpleAttributeSet();
        String source = "";
        Matcher matcher = null;
        try {
            p = Pattern.compile(words);
            source = dsd.getText(0, dsd.getLength());
            matcher = p.matcher(source);
            while (matcher.find()) {
                attr.addAttribute(StyleConstants.Bold, new Boolean(false));
                StyleConstants.setForeground(attr, Color.black);
                dsd.setCharacterAttributes(matcher.start(), matcher.group().length(), attr, false);
            }
        } catch (BadLocationException ex) {
            Logger.getLogger(SyntaxMonitor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void matchString(DefaultStyledDocument dsd) {
        Pattern p = null;
        SimpleAttributeSet attr = new SimpleAttributeSet();
        String source = "";
        Matcher matcher = null;
        try {
            p = Pattern.compile(astring);
            source = dsd.getText(0, dsd.getLength());
            matcher = p.matcher(source);
            while (matcher.find()) {
                attr.addAttribute(StyleConstants.Bold, new Boolean(false));
                StyleConstants.setForeground(attr, Color.red);
                dsd.setCharacterAttributes(matcher.start(), matcher.group().length(), attr, false);
            }
        } catch (BadLocationException ex) {
            Logger.getLogger(SyntaxMonitor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void matchingVariable(DefaultStyledDocument dsd) {
        Pattern p = null;
        SimpleAttributeSet attr = new SimpleAttributeSet();
        String source = "";
        Matcher matcher = null;
        String regexvariable = "\\s+\\w+\\s+";
        try {
            p = Pattern.compile(variable);
            source = dsd.getText(0, dsd.getLength());
            matcher = p.matcher(source);
            while (matcher.find()) {
                Pattern p2 = null;
                Matcher matcher2 = null;
                String mtvar = matcher.group();
                p2 = Pattern.compile(regexvariable);
                matcher2 = p2.matcher(mtvar);
                while (matcher2.find()) {
                    StyleConstants.setForeground(attr, new Color(0, 128, 0));
                    dsd.setCharacterAttributes(matcher.start() + matcher2.start(), matcher2.group().length(), attr, false);
                }
            }
        } catch (BadLocationException ex) {
            Logger.getLogger(SyntaxMonitor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void findString(String value, JTextPane txtSource, Boolean isCaseInsensitive) {
        Pattern p = null;
        SimpleAttributeSet attr = new SimpleAttributeSet();
        String source = "";
        Matcher matcher = null;
        try {
            if (isCaseInsensitive) {
                p = Pattern.compile(value, Pattern.CASE_INSENSITIVE);
            } else {
                p = Pattern.compile(value);
            }
            txtSource.selectAll();
            source = txtSource.getSelectedText();
            matcher = p.matcher(source);
            while (matcher.find()) {
                txtSource.setSelectionStart(matcher.start());
                txtSource.setSelectionEnd(matcher.end());
            }
        } catch (Exception ex) {
            Logger.getLogger(SyntaxMonitor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
