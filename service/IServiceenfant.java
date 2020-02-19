/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;

/**
 *
 * @author Khaled
 */
public interface IServiceenfant<E> {
     void insert(E e);
    List<E> displayAll();
    void delete(int id);
    void update (E e);
}
