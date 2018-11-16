/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import static controladores.InterfazController.relacionDebil;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author The.N
 */
public class RelacionDebilController implements Initializable {
    @FXML
    private Button canBtn;
    @FXML
    private Button aceBtn;
    @FXML
    private Text texto;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        texto.setText("¿Desea que la entidad: "+InterfazController.entidadActual.getNombre()+" dependa en esta relación?");
    }    
    @FXML
    private void si(ActionEvent event) {
        relacionDebil=true;
        
        Stage stage = (Stage) aceBtn.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void no(ActionEvent event) {
        relacionDebil=false;
        Stage stage = (Stage) canBtn.getScene().getWindow();
        stage.close();
    }
    
}
