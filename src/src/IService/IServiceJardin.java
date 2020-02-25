package IService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Daytech
 */
public interface IServiceJardin<T> {
    void Insert(T t);
    List<T> displayAll();
    void delete(int id);
    void update (T t);
    void insert(T t)throws SQLException;
}
