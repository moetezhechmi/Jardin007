/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.util.List;

/**
 *
 * @author Daytech
 */
public interface IService<T> {
    void Insert(T t);
    List<T> displayAll();
    void delete(int id);
    void update (T t);
    
}
