/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import java.util.List;

/**
 *
 * @author AmiR
 */
public interface IServiceReclamation<T> {
    void insert(T t);
    List<T> displayAll();
    void delete(int id);
    void update (T t);
    List<T> triParNom();
    List<T> rechercher();
    
}
