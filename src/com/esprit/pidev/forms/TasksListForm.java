/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.pidev.services.TaskService;

/**
 *
 * @author aissa
 */
public class TasksListForm extends Form {

    public TasksListForm(Form previous) {
        super("Tasks list", BoxLayout.y());

        this.add(new SpanLabel(new TaskService().getAllTasks().toString()));

        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previous.showBack();
        });
    }
}
