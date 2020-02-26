/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IIService;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Daytech
 */
public interface IServiceEvent<T> {
    void insert(T t)throws SQLException;
    List<T> displayAll()throws SQLException;
    void delete(int id)throws SQLException;
    void update (T t)throws SQLException;
    
}
