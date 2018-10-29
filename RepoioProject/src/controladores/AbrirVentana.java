/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;
import javafx.stage.Modality;

/**
 *
 * @author cmsan
 */
public class AbrirVentana {
    static public void CargarVista(URL resource) throws IOException{
        Parent root1 = (Parent) FXMLLoader.load(resource);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Creación");
        stage.setScene(new Scene(root1));  
        stage.showAndWait();
    }
}
