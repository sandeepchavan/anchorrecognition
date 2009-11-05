/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import java.util.List;
import javax.swing.JOptionPane;
import net.sf.oval.configuration.xml.XMLConfigurer;
import net.sf.oval.guard.Guard;

/**
 *
 * @author ddda
 */
public class DataValidation {

    private Validator validator = null;
    private static DataValidation instance = null;

    public static DataValidation Instance() {
        if (instance == null) {
            instance = new DataValidation();
        }
        return instance;
    }

    private Validator getValidator() {
        if (validator == null) {
            validator = new Validator();
        }
        return validator;
    }

    public boolean validateData(Object obj) {
        List<ConstraintViolation> violations = getValidator().validate(obj);
        if (violations.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation cv : violations) {
                sb.append(cv.getMessage());
                sb.append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
            return false;
        }
        return true;
    }

    public boolean validateDataXML(Object obj) {
        try {
            XMLConfigurer xmlConfigurer = new XMLConfigurer();
            xmlConfigurer.fromXML(new File("OvalConfig.xml"));
            Guard guard = new Guard(xmlConfigurer);
            List<ConstraintViolation> violations = guard.validate(obj);
            if (violations.size() > 0) {
                StringBuilder sb = new StringBuilder();
                for (ConstraintViolation cv : violations) {
                    sb.append(cv.getMessage());
                    sb.append("\n");
                }
                JOptionPane.showMessageDialog(null, sb.toString());
                return false;
            }
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
}
