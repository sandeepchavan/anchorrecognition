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

    /**
     * Get line where user click on Source code
     * @return line
     */
    public int getLine_clicking() {
        return line_clicking;
    }

    protected void setLine_clicking(int line_clicking) {
        this.line_clicking = line_clicking;
    }

    public LineClickEvent(Object source) {
        super(source);
    }
}
