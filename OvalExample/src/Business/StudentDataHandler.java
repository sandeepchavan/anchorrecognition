/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Entities.Student;
import javax.swing.JOptionPane;

/**
 *
 * @author ddda
 */
public class StudentDataHandler {

    private static StudentDataHandler instance = null;

    public static StudentDataHandler Instance() {
        if (instance == null) {
            instance = new StudentDataHandler();
        }
        return instance;
    }

    public void saveStudent(Student std){
        if(DataValidation.Instance().validateDataXML(std)){
            JOptionPane.showMessageDialog(null, "Save Student Successfully!");
        }
    }
}
