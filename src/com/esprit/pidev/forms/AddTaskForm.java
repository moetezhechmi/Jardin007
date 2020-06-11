/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.pidev.models.Task;
import com.esprit.pidev.services.TaskService;

/**
 *
 * @author aissa
 */
public class AddTaskForm extends Form {

    public AddTaskForm(Form previous) {
        super("Ajouter nouveau avis", BoxLayout.y());
        
        Label l2 = new Label("Mettez votre commentaire et votre note :");
        TextField tfCommentaire = new TextField(null, "Mettez commentaire");
        TextField tfNote = new TextField(null, "Note");
        
        
        ComboBox com = new ComboBox();
                com.addItem("Club");
                com.addItem("Evenement");
                com.addItem("Services");
                
                com.addActionListener((evt) -> {
            Form Avis = new Form("Avis",BoxLayout.y());
            

            Label l1 = new Label("Note");
            
            Slider sl = new Slider();
            
            Button valider = new Button("valider");
            
            if(com.getSelectedItem().equals("Club"))
            {
                
            }
            if(com.getSelectedItem().equals("Evenement"))
            {
            }
            if(com.getSelectedItem().equals("Services"))
            {
            }
            
            sl.setEditable(true);
            
            sl.setMaxValue(10);
            sl.setMinValue(0);
            sl.addDataChangedListener(new  DataChangedListener() {
                @Override
                public void dataChanged(int type, int index) {
                    l1.setText("Note : "+sl.getProgress());
                }
            } );
            
            valider.addActionListener((evt1) -> {
                Dialog.show("confirmation", "vous avez attribue la note de "+
                        sl.getProgress()+" au "+com.getSelectedItem()+"", "OK",null);
            });
            
            Avis.addAll(l1,sl,valider);
            Avis.getToolbar().addCommandToLeftBar("Retour", null, e -> showBack());
            Avis.show();
        });
         
                
        Button btn = new Button("Ajouter avis");

        btn.addActionListener((evt) -> {
            if ((tfCommentaire.getText().length() == 0) || (tfNote.getText().length() == 0)) {
                Dialog.show("Alert", "Please fill all the fields", "OK", null);
            } else {
                try {
                    Task t = new Task(Integer.parseInt(tfNote.getText()), tfCommentaire.getText());
                    if (new TaskService().addTask(t)) {
                        Dialog.show("SUCCESS", "Avis envoyé", "OK", null);
                    } else {
                        Dialog.show("ERROR", "Erreur du serveur", "OK", null);
                    }
                } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "La note doit être un nombre", "OK", null);
                }

            }
        });

        this.addAll(l2,tfCommentaire, tfNote,com,btn);

        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previous.showBack();
        });
    }

}
