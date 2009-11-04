/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AutoRecognization;

/**
 *
 * @author ddda
 */
public class StartEntity {

    private String fullfilename = "";
    private String antragsnumber = "";

    public StartEntity(String fullfilename, String antragsnumber) {
        this.fullfilename = fullfilename;
        this.antragsnumber = antragsnumber;
    }

    public String getAntragsnumber() {
        return antragsnumber;
    }

    public void setAntragsnumber(String antragsnumber) {
        this.antragsnumber = antragsnumber;
    }

    public String getFullfilename() {
        return fullfilename;
    }

    public void setFullfilename(String fullfilename) {
        this.fullfilename = fullfilename;
    }
}
