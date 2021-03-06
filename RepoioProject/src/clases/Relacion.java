/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import controladores.InterfazController;
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author IP-ROUTE
 */
public class Relacion {
    String nombre;
    Figura figura;
    ArrayList<Entidad> componentes = new ArrayList();
    ArrayList<Union> uniones = new ArrayList();
    ArrayList<Propiedad> propiedades = new ArrayList<>();
    ArrayList<Integer> posicionDebiles = new ArrayList<>();
    String entidad1Cardinal="";
    String entidad2Cardinal="";
    String entidad1Linea="Simple";
    String entidad2Linea="Simple";

    public String getEntidad1Linea() {
        return entidad1Linea;
    }

    public String getEntidad2Linea() {
        return entidad2Linea;
    }

    public void setEntidad1Linea(String entidad1Linea) {
        this.entidad1Linea = entidad1Linea;
    }

    public void setEntidad2Linea(String entidad2Linea) {
        this.entidad2Linea = entidad2Linea;
    }
    
    
    public void setEntidad1Cardinal(String entidad1Cardinal) {
        this.entidad1Cardinal = entidad1Cardinal;
    }

    public void setEntidad2Cardinal(String entidad2Cardinal) {
        this.entidad2Cardinal = entidad2Cardinal;
    }

    public String getEntidad1Cardinal() {
        return entidad1Cardinal;
    }

    public String getEntidad2Cardinal() {
        return entidad2Cardinal;
    }
    
    public Relacion() {
    }
    
    public ArrayList<Union> getUniones() {
        return uniones;
    }

    public void setPosicionDebiles(ArrayList<Integer> posicionDebiles) {
        this.posicionDebiles = (ArrayList<Integer>) posicionDebiles.clone();
    }
    
    /**
     * metodo que crea todas las uniones(objeto Union) del objeto Relacion 
     */
    
    public Relacion clon(ArrayList<Entidad> entidadesClon , ArrayList<Entidad> entidadesOriginal){
        Relacion aux = new Relacion(nombre);
        aux.setFigura(figura.clon());
        for (Entidad componente : componentes) {
            int indexClon = entidadesOriginal.lastIndexOf(componente);
            aux.componentes.add(entidadesClon.get(indexClon));
        }
        for (Union union : uniones) {
            aux.uniones.add(union.clon());
        }
        for (Propiedad propiedad : propiedades) {
            aux.propiedades.add(propiedad.clon());
        }
        aux.posicionDebiles=(ArrayList<Integer>) posicionDebiles.clone();
        aux.entidad1Cardinal=entidad1Cardinal;
        aux.entidad2Cardinal=entidad2Cardinal;
        aux.entidad1Linea = entidad1Linea;
        aux.entidad2Linea = entidad2Linea;
        return aux;
    }
    public void correccion(){
        int x= 0;
        while(x<posicionDebiles.size()){
            if(!componentes.get(posicionDebiles.get(x)).figura.debil){
                
                posicionDebiles.remove(x);
            }else{
                
                x++;
            }
            
        }
            
        
    }

    
    
    public void crearUniones(){
        
        uniones.clear();
        
        for (Entidad e: componentes) {
            uniones.add(new Union(this.figura , e.figura));
        }
        if (componentes.size()==1){
            for (Entidad e: componentes) {
                uniones.add(new Union(this.figura , e.figura));
            }
        }
        for(int x =0; x<posicionDebiles.size();x++){
            uniones.get(posicionDebiles.get(x)).setDebil(true);
        }
        
        
        if(uniones.size()<3 && uniones.size()>0 && this.figura.debil==false){
            if("Doble".equals(entidad1Linea)){
                uniones.get(0).setDebil(true);
            }else{
                uniones.get(0).setDebil(false);
            }
            if("Doble".equals(entidad2Linea)){
                uniones.get(1).setDebil(true);
            }else{
                uniones.get(1).setDebil(false);
            }
        }
    }
    public void eliminarDebil(Entidad c){
        int w=componentes.indexOf(c);
        
        for(int x =0; x<posicionDebiles.size();x++){
            if(w==posicionDebiles.get(x)){
                uniones.get(posicionDebiles.get(x)).setDebil(true);
                for(int y=x+1;y<posicionDebiles.size();y++){
                    posicionDebiles.set(y, ((Integer) posicionDebiles.get(y)-1));
                }
                posicionDebiles.remove(x);
                
                break;
            }
            if(w<posicionDebiles.get(x)){
                posicionDebiles.set(x, posicionDebiles.get(x)-1);
            }
        }
        

    }
    public void metamorfosear(){
        if(InterfazController.hayClave(componentes) && InterfazController.hayDebil(componentes)<componentes.size() && posicionDebiles.size()>0){
            this.figura.setDebil(true);
        }else{
            this.figura.setDebil(false);
            posicionDebiles = new ArrayList<>();
        }
        
        
    }
    public Relacion(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
        this.figura.nombre=nombre;
    }

    public void setFigura(Figura figura) {
        this.figura = figura;
        this.figura.nombre = nombre;
    }

    public Figura getFigura() {
        return figura;
    }
    
    public void setComponentes(ArrayList<Entidad> componentes) {
        ArrayList<Entidad> nuevo = new ArrayList<>();
        for (Entidad componente : componentes) {
            nuevo.add(componente);
        }
        this.componentes = nuevo;
    }

    public ArrayList<Entidad> getComponentes() {
        return componentes;
    }
    
    public Entidad getComponente(int index) {
        return componentes.get(index);
    }

    public void addComponente(Entidad componente){
        componentes.add(componente);
    }
    
    public Entidad popComponente(int index){
        Entidad ent = componentes.get(index);
        componentes.remove(index);
        return ent;
    }
    public ArrayList<Propiedad> getPropiedades() {
        return propiedades;
    }

    public void setPropiedades(ArrayList<Propiedad> propiedades) {
        this.propiedades = (ArrayList<Propiedad>) propiedades.clone();
        this.figura.propiedades = this.propiedades;
    }
    public void f(GraphicsContext gc){
        this.figura.tirarLinea(propiedades, gc);
        for (int i = 0; i < uniones.size(); i++) {
            
            if(uniones.get(i).debil){
                this.figura.tirarDobleLinea(gc, uniones.get(i));
            }else{
                
                this.figura.tirarLinea(gc, uniones.get(i));
            }
            if(i==0){
                if("".equals(entidad1Cardinal)){
                    
                }else{
                    uniones.get(0).cardinalidad(entidad1Cardinal,gc);
                }
                
            }
            if(i==1){
                if("".equals(entidad1Cardinal)){
                    
                }else{
                    uniones.get(1).cardinalidad(entidad2Cardinal,gc);
                }
                
                
            }
            
        }
        
    }

    @Override
    public String toString() {
        return nombre;
    }

    
}
