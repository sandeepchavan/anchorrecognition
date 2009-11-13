/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JScriptIDE;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *
 * @author ddda
 */
public class SyntaxError {

    private int line_number_error = 0;
    private String message = "";

    /**
     * get error line number
     * @return error line number
     */
    public int getLine_number_error() {
        return line_number_error;
    }

    /**
     * get error message
     * @return string error message
     */
    public String getMessage() {
        return message;
    }

    protected SyntaxError() {
    }

    protected void compileCode(String source) {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        try {
            engine.eval(source);
            this.line_number_error = 0;
            this.message = "";
        } catch (ScriptException ex) {
            this.line_number_error = ex.getLineNumber();
            this.message = ex.getMessage();
        }

    }
}
