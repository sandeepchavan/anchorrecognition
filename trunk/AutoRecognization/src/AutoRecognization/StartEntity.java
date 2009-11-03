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

    private long oid = 0;
    private long id = 0;
    private long managementid = 0;
    private String filepath = "";
    private String filename1 = "";
    private String filename2 = "";
    private String fullfilename1 = "";
    private String fullfilename2 = "";
    private String comment = "";
    private String createdtime = "";
    private String antragsnummer = "";

    public StartEntity(String fullfilename1, String antragsnummer) {
        this.antragsnummer = antragsnummer;
        this.fullfilename1 = fullfilename1;
    }

    public String getAntragsnummer() {
        return antragsnummer;
    }

    public void setAntragsnummer(String antragsnummer) {
        this.antragsnummer = antragsnummer;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(String createdtime) {
        this.createdtime = createdtime;
    }

    public String getFilename1() {
        return filename1;
    }

    public void setFilename1(String filename1) {
        this.filename1 = filename1;
    }

    public String getFilename2() {
        return filename2;
    }

    public void setFilename2(String filename2) {
        this.filename2 = filename2;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getFullfilename1() {
        return fullfilename1;
    }

    public void setFullfilename1(String fullfilename1) {
        this.fullfilename1 = fullfilename1;
    }

    public String getFullfilename2() {
        return fullfilename2;
    }

    public void setFullfilename2(String fullfilename2) {
        this.fullfilename2 = fullfilename2;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getManagementid() {
        return managementid;
    }

    public void setManagementid(long managementid) {
        this.managementid = managementid;
    }

    public long getOid() {
        return oid;
    }

    public void setOid(long oid) {
        this.oid = oid;
    }
}
