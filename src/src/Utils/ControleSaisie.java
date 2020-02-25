/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author moetez
 */
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import Entity.Adresse;
import Entity.Jardin;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Moslah Yassine
 */
public class ControleSaisie {

    public ControleSaisie() {
    }

    public boolean controleTextFieldVide(TextField textField, String msg, Label errorLabel) {
        String chaine = textField.getText().trim();
        if (chaine.length() == 0) {
            errorLabel.setText(msg);
            textField.clear();
            return true;
        }
        return false;
    }

    public boolean controleMDPVide(JFXPasswordField textField, String msg, Label errorLabel) {
        String chaine = textField.getText().trim();
        if (chaine.length() == 0) {
            errorLabel.setText(msg);
            textField.clear();
            return true;
        }
        return false;
    }

    public void effacerControleSaisie(Label textField) {
        textField.setText("");
    }

    public boolean controleTextFieldNonNumerique(TextField textField, String msg, Label errorLabel) {
        if (!textField.getText().matches(".*[a-zA-Z].*")) {
            errorLabel.setText(msg);

            return true;
        }
        return false;
    }

    public boolean controleTextFieldOnlyLetters(TextField textField, String msg, Label errorLabel) {
        String chaine = textField.getText();
        char[] tab = chaine.toCharArray();

        boolean valide = true;

        for (int i = 0; i < tab.length; i++) {
            if (Character.isDigit(tab[i]) || tab[i] == '.' || tab[i] == ',' || tab[i] == '-' || tab[i] == '_' || tab[i] == '@') {
                valide = false;
            }
        }

        if (!valide) {
            errorLabel.setText(msg);
            return true;
        }
        return false;
    }

    public boolean controleTextFieldChiffres(TextField textField, String msg, Label errorLabel) {
        String chaine = textField.getText();
        char[] tab = chaine.toCharArray();

        boolean estUnNombre = true;
        for (int i = 0; i < tab.length; i++) {
            if (!Character.isDigit(tab[i])) {
                estUnNombre = false;
            }
        }
        if (!estUnNombre) {
            errorLabel.setText(msg);
            return true;
        }
        return false;
    }

    public boolean controleCINLongueur(TextField textField, String msg, Label errorLabel) {

        if (textField.getText().length() != 8) {
            errorLabel.setText(msg);
            return true;
        } else if (textField.getText().equals("00000000")) {
            errorLabel.setText("CIN incorrecte                  ");
            return true;
        }
        return false;
    }

    public boolean controleCPLongueur(TextField textField, String msg, Label errorLabel) {

        if (textField.getText().length() != 4) {
            errorLabel.setText(msg);
            return true;
        } else if (textField.getText().equals("0000")) {
            errorLabel.setText("Code postal incorrecte                 ");
            return true;
        }
        return false;
    }

    public boolean controleNumTelLongueur(TextField textField, String msg, Label errorLabel) {
        if (textField.getText().length() != 8) {
            errorLabel.setText(msg);
            return true;
        } else if (textField.getText().substring(0, 1) != "31" && textField.getText().charAt(0) != '2' && textField.getText().charAt(0) != '5' && textField.getText().charAt(0) != '9' && textField.getText().charAt(0) != '7') {
            errorLabel.setText("N° Tel. incorrecte                 ");
            return true;
        }
        return false;
    }

    public boolean controleMailFormat(TextField textField, String msg, Label errorLabel) {
        String chaine = textField.getText();
        if (chaine.length() != 0) {
            if (chaine.charAt(chaine.length() - 1) == '.') {
                errorLabel.setText(msg);
                return true;
            } else {

                int firstIndexA = chaine.indexOf("@");
                int lastIndexA = chaine.lastIndexOf("@");
                int lastIndexPt = chaine.lastIndexOf(".");
                if (firstIndexA < 3 || firstIndexA != lastIndexA || firstIndexA > lastIndexPt || lastIndexPt - firstIndexA < 4 || chaine.substring(lastIndexPt + 1, chaine.length() - 1).length() > 3 || chaine.substring(lastIndexPt + 1, chaine.length()).length() < 2) {
                    errorLabel.setText(msg);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean controleComplexiteMDP(JFXPasswordField textField, String msg, Label errorLabel) {
        String chaine = textField.getText();
        char[] tab = chaine.toCharArray();
        boolean chiffre = false;
        boolean minus = false;
        boolean majus = false;

        for (int i = 0; i < tab.length; i++) {
            if (Character.isDigit(tab[i])) {
                chiffre = true;
            } else if (tab[i] >= 'a' && tab[i] <= 'z') {
                minus = true;
            } else if (tab[i] >= 'A' && tab[i] <= 'Z') {
                majus = true;
            }
        }

        if (chaine.length() < 8) {
            errorLabel.setText("Longueur minimal est 8 caractères                  ");
            return true;
        } else if (!(chiffre && minus && majus)) {
            errorLabel.setText(msg);
            return true;
        }
        return false;
    }

    public boolean controleComboBox(JFXComboBox<String> combo, String msg, Label errorLabel) {
        if (combo.getValue() == null) {
            errorLabel.setText(msg);
            return true;
        }
        return false;
    }

    public boolean controleTextAreaVide(JFXTextArea tArea, String msg, Label errorLabel) {
        String chaine = tArea.getText().trim();
        if (chaine.length() == 0) {
            errorLabel.setText(msg);
            tArea.clear();
            return true;
        }
        return false;
    }

    public boolean controleTextAreaNonNumerique(JFXTextArea tArea, String msg, Label errorLabel) {
        if (!tArea.getText().matches(".*[a-zA-Z].*")) {
            errorLabel.setText(msg);

            return true;
        }
        return false;
    }

    public boolean controleLabelImage(Label path, String msg, Label errorLabel) {
        if (path.getText().equals("Sélectionner une image")) {
            errorLabel.setText(msg);
            return true;
        }
        return false;
    }

    public boolean controleGPS(double latitude, double longitude, String msg, Label errorLabel) {
        if (latitude == 0 && longitude == 0) {
            errorLabel.setText(msg);
            return true;
        }
        return false;
    }

    public boolean controleTextFieldNumerique1(JFXTextField textField, String msg, Label errorLabel) {
        if (!textField.getText().matches(".*[a-zA-Z].*")) {
            errorLabel.setText(msg);

            return true;
        }
        effacerControleSaisie(errorLabel);

        return false;
    }

    public boolean controleNumTelLongueur1(JFXTextField textField, String msg, Label errorLabel) {
        if (textField.getText().length() != 8) {
            errorLabel.setText(msg);
            return true;
        } else if (textField.getText().substring(0, 1) != "31" && textField.getText().charAt(0) != '2' && textField.getText().charAt(0) != '5' && textField.getText().charAt(0) != '9' && textField.getText().charAt(0) != '7') {
            errorLabel.setText("N° Tel. incorrecte                 ");
            return true;
        }
        effacerControleSaisie(errorLabel);
        return false;
    }

    public boolean controleTextFieldVide1(JFXTextField textField, String msg, Label errorLabel) {
        String chaine = textField.getText().trim();
        if (chaine.length() == 0) {
            errorLabel.setText(msg);
            textField.clear();
            return true;
        }
        effacerControleSaisie(errorLabel);
        return false;

    }

    public boolean controleTextFieldNonNumerique1(JFXTextField textField, String msg, Label errorLabel) {
        if (textField.getText().matches(".*[a-zA-Z].*")) {
            errorLabel.setText(msg);

            return true;
        }
        effacerControleSaisie(errorLabel);

        return false;
    }

    public boolean controleTextAreaVide1(JFXTextArea tArea, String msg, Label errorLabel) {
        String chaine = tArea.getText().trim();
        if (chaine.length() == 0) {
            errorLabel.setText(msg);
            tArea.clear();
            return true;
        }
        effacerControleSaisie(errorLabel);

        return false;
    }

    public boolean controleTextAreaNonNumerique1(JFXTextArea tArea, String msg, Label errorLabel) {
        if (!tArea.getText().matches(".*[a-zA-Z].*")) {
            errorLabel.setText(msg);

            return true;
        }
        effacerControleSaisie(errorLabel);

        return false;
    }

    public boolean controleLabelImage1(Label path, String msg, Label errorLabel) {
        if (path.getText().equals("Sélectionner une image")) {
            errorLabel.setText(msg);
            return true;
        }
        effacerControleSaisie(errorLabel);

        return false;
    }

  

    public boolean controleTextFieldPrix(JFXTextField textField, String msg, Label errorLabel) {
        String chaine = textField.getText();
        char[] tab = chaine.toCharArray();

        boolean estUnNombre = true;
        for (int i = 0; i < tab.length; i++) {
            if (!Character.isDigit(tab[i]) && tab[i] != '.') {
                estUnNombre = false;
            }
        }
        if (!estUnNombre) {
            errorLabel.setText(msg);
            return true;
        }
        return false;
    }

    public boolean controleComboBoxstring(JFXComboBox<String> combo, String msg, Label errorLabel) {
        if (combo.getValue() == null) {
            errorLabel.setText(msg);
            return true;
        }
        return false;
    }
    
}  

