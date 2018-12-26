/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import clases.Agrupacion;
import clases.Entidad;
import clases.Herencia;
import static controladores.InterfazController.relacionActual;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author IP-ROUTE
 */
public class DatosAgregacionController implements Initializable {

    @FXML
    private Button canBtn;
    @FXML
    private TextField textFieldNombre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textFieldNombre.setText(InterfazController.entidadActual.getNombre());
        // TODO
    }    

    @FXML
    private void cancelar(ActionEvent event) {
        Stage stage = (Stage) canBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void eliminar(ActionEvent event) {
        for (Entidad entidade : InterfazController.diagrama.entidades) {
            if(entidade instanceof Agrupacion){
                if(InterfazController.entidadActual.getNombre()== ((Agrupacion) entidade).getRelacion().getNombre()){
                    ArrayList <Herencia> eliminar2 = new ArrayList();
                    for (Herencia herencia : InterfazController.diagrama.getHerencias()) {
                        if(herencia.getEntidades().contains(entidade)){
                            herencia.getEntidades().remove(entidade);
                        }
                        if(herencia.getPadre().getNombre() == null ? entidade.getNombre() == null :
                                herencia.getPadre().getNombre().equals(entidade.getNombre())){
                            eliminar2.add(herencia);
                        }
                        if(herencia.getEntidades().size()<1){
                            eliminar2.add(herencia);
                        }
                    }
                    InterfazController.diagrama.herencias.removeAll(eliminar2);
                }
            }
        }
        InterfazController.diagrama.getEntidades().remove(InterfazController.entidadActual);
        
        Stage stage = (Stage) canBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void modificar(ActionEvent event) {
        InterfazController.entidadActual.setNombre(textFieldNombre.getText());
        Stage stage = (Stage) canBtn.getScene().getWindow();
        stage.close();
    }
    
}
