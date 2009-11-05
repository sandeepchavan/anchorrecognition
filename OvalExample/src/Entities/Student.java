/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import net.sf.oval.constraint.Assert;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;
/**
 *
 * @author ddda
 */
public class Student {

    /*@NotNull
    @NotEmpty
    @Length(max = 50)*/
    private String name = null;

    //@Assert(expr = "(_value=='Mrs' && _this.name.contains('thi')) || (_value=='Mr' && _this.name.contains('van'))", lang = "groovy")
    private String gender = "";

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
