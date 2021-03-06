/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;


import clases.Agrupacion;
import clases.Diagrama;
import clases.Entidad;
import clases.Figura;
import clases.Herencia;
import clases.Propiedad;
import clases.Relacion;
import static controladores.InterfazController.compRelacion;
import static controladores.InterfazController.diagrama;
import static controladores.InterfazController.entidadActual;
import static controladores.InterfazController.posicionDefaultX;
import static controladores.InterfazController.posicionDefaultY;
import static controladores.InterfazController.relacionActual;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author The.N
 */
public class DatosRelacionController implements Initializable {
    
    ArrayList <Entidad> ents;
    
    int ent;
    
    public TextField nombre;
    public ListView lista;
    ObservableList<EntidadCheck> entidadCheck = FXCollections.observableArrayList();
    
    Alert alertNombre = new Alert(Alert.AlertType.INFORMATION); 
    Alert alertEx = new Alert(Alert.AlertType.INFORMATION);
    
    @FXML private Button canBtn;
    @FXML
    private ListView<Entidad> listView;
    @FXML
    private ComboBox<Entidad> comboBox;
    @FXML
    private Button addBtn;
    @FXML
    private Button delBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        InterfazController.propiedadActual = (ArrayList<Propiedad>) relacionActual.getPropiedades().clone();
        ents = (ArrayList<Entidad>) relacionActual.getComponentes().clone();
        ObservableList<Entidad> itemLV = FXCollections.observableArrayList();
        itemLV.addAll(ents);
        listView.setItems(itemLV);
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listView.setEditable(true);
        ObservableList<Entidad> itemCB = FXCollections.observableArrayList();
        for (Entidad e : InterfazController.diagrama.getEntidades()){
            if (!relacionActual.getComponentes().contains(e)){
                if(e instanceof Agrupacion){
                    if(!relacionDentroAgrupacion((Agrupacion) e)){
                        itemCB.add(e);
                    }
                }
                else{
                    itemCB.add(e);
                }
            }
        }
        comboBox.setItems(itemCB);
        delBtn.setDisable(true);
        
        alertNombre.setTitle("Error");
        alertNombre.setHeaderText(null);
        alertNombre.setContentText("La relacion tiene el mismo nombre que otro elemento en el diagrama");
        alertEx.setTitle("Error");
        alertEx.setHeaderText(null);
        alertEx.setContentText("Haz excedido el limite de 20 caracteres");
        
        nombre.setText(relacionActual.getNombre());
        if (ents.size()>=2)
            addBtn.setDisable(true);
    }    
    
    private boolean relacionDentroAgrupacion(Agrupacion agrupacion){
        if(agrupacion.getRelacion()==relacionActual){
            return true;
        }
        for (Object componente : agrupacion.getRelacion().getComponentes()) {
            if(componente instanceof Agrupacion){
                return relacionDentroAgrupacion((Agrupacion) componente);
            }
        }
        return false;
    }
    @FXML
    public void modificar(){
        if(!(nombre.getText().equals(""))){
            if (nombre.getText().length()>20){
                alertEx.showAndWait();
            }else{
                InterfazController.nombreActual = nombre.getText();
                Figura fig = new Figura();
                if (ents.size()==1 ||
                    ents.size()==2 ||
                    ents.size()==4 ){
                    fig.crearFigura((int)relacionActual.getFigura().getPuntoCentral().getX(), 
                            (int)relacionActual.getFigura().getPuntoCentral().getY(), 20 , 4);
                } else {
                    fig.crearFigura((int)relacionActual.getFigura().getPuntoCentral().getX(), 
                            (int)relacionActual.getFigura().getPuntoCentral().getY(), 20 , ents.size());
                }   
                relacionActual.setFigura(fig);
                relacionActual.setComponentes(ents);
                relacionActual.setNombre(nombre.getText());
                relacionActual.setPropiedades((ArrayList<Propiedad>) InterfazController.propiedadActual.clone());
                Stage stage = (Stage) canBtn.getScene().getWindow();
                stage.close();
                
            } 
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("El nombre debe tener algun caracter");
            alert.showAndWait();
        }
        

    }
    @FXML
    private void cancelar(ActionEvent event) {
        Stage stage = (Stage) canBtn.getScene().getWindow();
        stage.close();
        
    }

    @FXML
    private void haciaPropiedad(ActionEvent event) throws IOException {
        if (nombre.getText().length()!=0){
            if(nombre.getText().length()>20){
                alertEx.showAndWait();
            }
        } else{
            InterfazController.nombreActual="e"+(InterfazController.diagrama.getEntidades().size()+1);
        }
        AbrirVentana.CargarVista(getClass().getResource("/fxmls/EditarPropiedad.fxml"));
    }

    @FXML
    private void aniadir(ActionEvent event) {
        if (comboBox.getValue() instanceof Entidad){
            ents.add(comboBox.getValue());
            ObservableList<Entidad> itemLV = FXCollections.observableArrayList();
            itemLV.addAll(ents);
            listView.setItems(itemLV);
            ObservableList<Entidad> itemCB = FXCollections.observableArrayList();
            for (Entidad e : InterfazController.diagrama.getEntidades()){
                if (!ents.contains(e)){
                    itemCB.add(e);
                }
            }
            comboBox.setItems(itemCB);
        }
        if (ents.size()>=2)
            addBtn.setDisable(true);
    }

    @FXML
    private void quitar(ActionEvent event) {
        ents.remove(ent);
        ObservableList<Entidad> itemLV = FXCollections.observableArrayList();
        itemLV.addAll(ents);
        listView.setItems(itemLV);
        ObservableList<Entidad> itemCB = FXCollections.observableArrayList();
        for (Entidad e : InterfazController.diagrama.getEntidades()){
            if (!ents.contains(e)){
                itemCB.add(e);
            }
        }
        comboBox.setItems(itemCB);
        delBtn.setDisable(true);
        if (ents.size()<=2)
            addBtn.setDisable(false);
    }

    @FXML
    private void seleccionar(MouseEvent event) {
        if(!ents.isEmpty()){
            try{
               ObservableList<Integer> modificado = listView.getSelectionModel().getSelectedIndices();
                ent = (int) modificado.get(0);
                delBtn.setDisable(false);
            }catch(Exception e){
                System.out.println("Error salvaje aparece, pero try catch salva el dia denuevo");
            }
            
        }
    }

    
    class EntidadCheck {
        private StringProperty nombre;
        private BooleanProperty check;

        public EntidadCheck(String nombre, boolean check) {
            this.nombre = new SimpleStringProperty(nombre);
            this.check = new SimpleBooleanProperty(check);
        }

        public StringProperty getNombre() { return nombre; }    
        public BooleanProperty getCheck() { return check; }
    }
    

    
    @FXML
    private void eliminarRelacion(){
        for (Entidad entidade : InterfazController.diagrama.entidades) {
            if(entidade instanceof Agrupacion){
                if(relacionActual.getNombre()== ((Agrupacion) entidade).getRelacion().getNombre()){
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
        InterfazController.diagrama.getRelaciones().remove(relacionActual);
        relacionActual=null;
        
        Stage stage = (Stage) canBtn.getScene().getWindow();
        stage.close();
        
                
    }
}
