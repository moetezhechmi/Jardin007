/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import Entitie.Incident;
import java.util.List;

/**
 *
 * @author Daytech
 */
public interface IService<T> {
    void insert(T t);
    List<T> displayAll();
    void delete(Incident t);
    void update (Incident t);
    List<T> triParNom();
    List<T> rechercher();
    
}
