/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JScriptIDE;

import java.util.EventObject;

/**
 *
 * @author ddda
 */
public class LineClickEvent extends EventObject {

    private int line_clicking = 0;

    public int getLine_clicking() {
        return line_clicking;
    }

    public void setLine_clicking(int line_clicking) {
        this.line_clicking = line_clicking;
    }

    public LineClickEvent(Object source) {
        super(source);
    }
}
