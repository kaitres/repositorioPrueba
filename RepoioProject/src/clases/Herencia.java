/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author IP-ROUTE
 */
public class Herencia {
    private String tipo;
    private ArrayList<Entidad> entidades = new ArrayList<>();
    private Figura figura;
    private Entidad padre;

    
    
    public Herencia(String tipo, ArrayList<Entidad> entidades, Figura figura, Entidad padre) {
        this.tipo = tipo;
        this.entidades = entidades;
        this.figura = figura;
        this.figura.nombre = tipo;
        this.padre = padre;
    }

    private Herencia() {
    }
    
    public Herencia clon(ArrayList<Entidad> entidadesClon, ArrayList<Entidad> entidadesOriginal){
        Herencia aux = new Herencia();
        aux.tipo=this.tipo;
        for (Entidad entidad : entidades) {
            int indexClon = entidadesOriginal.lastIndexOf(entidad);
            aux.entidades.add(entidadesClon.get(indexClon));
        }
        aux.setFigura(figura.clon());
        aux.setPadre(padre.clon());
        return aux;
    }
        
    public Entidad getPadre() {
        return padre;
    }

    public void setPadre(Entidad padre) {
        this.padre = padre;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
        this.figura.nombre = tipo;
    }

    public void setEntidades(ArrayList<Entidad> entidades) {
        this.entidades = entidades;
    }

    public void setFigura(Figura figura) {
        this.figura = figura;
        this.figura.nombre = this.getTipo();
    }

    public String getTipo() {
        return tipo;
    }

    public ArrayList<Entidad> getEntidades() {
        return entidades;
    }

    public Figura getFigura() {
        return figura;
    }
    public void f(GraphicsContext gc){
        this.figura.tirarLinea(gc, padre);
        this.figura.tirarLinea(gc,entidades);       
    }
    
}
