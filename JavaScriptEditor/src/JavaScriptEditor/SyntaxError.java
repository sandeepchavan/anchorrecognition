/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaScriptEditor;

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
    private static SyntaxError instance = null;

    public static SyntaxError Instance() {
        if (instance == null) {
            instance = new SyntaxError();
        }
        return instance;
    }

    public int getLine_number_error() {
        return line_number_error;
    }

    public String getMessage() {
        return message;
    }

    private SyntaxError() {
    }

    public void compileCode(String source) {
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
