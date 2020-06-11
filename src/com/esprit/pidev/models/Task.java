/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.models;

import com.codename1.ui.TextSelection;

/**
 *
 * @author aissa
 */
public class Task {

    private int id;
    private int note;
    private String commentaire;
    

    public Task(int id, int note, String commentaire) {
        this.id = id;
        this.note = note;
        this.commentaire = commentaire;
        
    }

    public Task(int note, String commentaire) {
        this.note = note;
        this.commentaire = commentaire;
        
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
    

    @Override
    public String toString() {
        return "Task{" + "id=" + id + ", note=" + note + ", commentaire=" + commentaire +'}';
    }
}
