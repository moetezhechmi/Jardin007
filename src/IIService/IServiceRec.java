/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import Entitie.Incident;
import Entitie.Reclamation;
import java.util.List;

/**
 *
 * @author Daytech
 */
public interface IServiceRec<T> {
    void insert(Reclamation t,String idJ);
    List<T> displayAll();
    void delete(Reclamation t);
    void update (Reclamation t);
    List<T> triParNom();
    List<T> rechercher();
    
}
