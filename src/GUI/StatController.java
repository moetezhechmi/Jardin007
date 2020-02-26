/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import service.ClubService;

/**
 * FXML Controller class
 *
 * @author Daytech
 */
public class StatController implements Initializable {

    @FXML
    private PieChart PieChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ClubService cs=new ClubService();

           
        PieChart.getData().clear();
        PieChart.setData(cs.StatNbrParticipant());
        PieChart.setAnimated(true);
        PieChart.setVisible(true);
    }    

    @FXML
    private void chart(ActionEvent event) {
        ClubService cs=new ClubService();
        cs.StatNbrParticipant();
    }
    
}
