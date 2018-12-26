/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;



import java.awt.Point;
import java.util.ArrayList;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author The.N
 */
public class Agrupacion extends Entidad {
    Relacion relacion;

    public Agrupacion(String nombre , Relacion relacion) {
       super(nombre);
       this.relacion = relacion;
       Figura fig = new Figura();
       figura = fig;
       fig.setNombre(nombre);
       
        
    }
    public Agrupacion clon(){
        Agrupacion agrupacion = new Agrupacion(nombre, relacion);
        agrupacion.figura=figura.clon();
        for (Propiedad propiedade : propiedades) {
            agrupacion.propiedades.add(propiedade.clon());                 
        }
        return agrupacion;
    }
                
    
     public void borrarRelacion(){
         this.relacion = null;
                 
     
     }
             
    public void setFigura(){
        Figura fig = new Figura();
        figura = fig;
    }
    
    public Figura getFigura(){
        return figura;
    }
    public Relacion getRelacion() {
        return relacion;
    }
    public void movimiento(Point2D p , GraphicsContext gc){
        
        //Point2D pAux= new Point2D(cordCuadradoMov.get(2).getX()-cordCuadradoMov.get(0).getX() , cordCuadradoMov.get(2).getY()-cordCuadradoMov.get(0).getY());
        figura.obtenerCordenadas(relacion);
        Point2D pAux= figura.coordenadas.get(1);
        
        int XDistancia = (int) (p.getX() - pAux.getX());
        int YDistancia = (int) (p.getY() - pAux.getY());
            Point2D pCRAux = relacion.getFigura().getPuntoCentral();
            relacion.getFigura().setPuntoCentral(new Point2D(relacion.getFigura().getPuntoCentral().getX() + XDistancia,relacion.getFigura().getPuntoCentral().getY() +  YDistancia));
            for (Propiedad propiedade :  relacion.getPropiedades()) {
                propiedade.getElip().setPuntoCentral(new Point2D(propiedade.getElip().getPuntoCentral().getX() + XDistancia,propiedade.getElip().getPuntoCentral().getY() +  YDistancia));
                if(propiedade.tipo == Tipo.compuesto){
                            for (Propiedad prop :  propiedade.propiedades) {
                            prop.getElip().setPuntoCentral(new Point2D(prop.getElip().getPuntoCentral().getX() + XDistancia,prop.getElip().getPuntoCentral().getY() +  YDistancia));
                        }
                }
            }
            
            
            for (int i = 0; i < relacion.getComponentes().size(); i++) {
                if(relacion.getComponentes().get(i) instanceof Agrupacion){
                    //relacion.getComponente(i).getFigura().coordenadasConeccion.add(relacion.figura.puntoCentral);
                    relacion.getComponente(i).getFigura().obtenerCordenadas(((Agrupacion)relacion.getComponente(i)).getRelacion());
                    Point2D pp =relacion.getComponente(i).getFigura().coordenadas.get(1);
                    int x=(int) (-pp.getX() + figura.coordenadas.get(1).getX()) ;
                    int y=(int) (-pp.getY() + figura.coordenadas.get(1).getY());
                    ((Agrupacion)relacion.getComponente(i)).movimiento(new Point2D(p.getX() - x , p.getY() - y ), gc );
                }
                else{
                    Point2D pCAAux = relacion.getComponente(i).getFigura().getPuntoCentral();
                    relacion.getComponente(i).getFigura().setPuntoCentral(new Point2D(relacion.getComponente(i).getFigura().getPuntoCentral().getX() + XDistancia,relacion.getComponente(i).getFigura().getPuntoCentral().getY() +  YDistancia));
                    for (Propiedad propiedade :  relacion.getComponente(i).getPropiedades()) {
                        propiedade.getElip().setPuntoCentral(new Point2D(propiedade.getElip().getPuntoCentral().getX() + XDistancia,propiedade.getElip().getPuntoCentral().getY() +  YDistancia));
                        if(propiedade.tipo == Tipo.compuesto){
                            for (Propiedad prop :  propiedade.propiedades) {
                            prop.getElip().setPuntoCentral(new Point2D(prop.getElip().getPuntoCentral().getX() + XDistancia,prop.getElip().getPuntoCentral().getY() +  YDistancia));
                        }
                    }

                        
                    }
                }
            }
        
            //getFigura().dibujarMarco(gc, relacion);
            //getFigura().dibujarCuadrado(gc, figura.cordCuadradoMov);
            //pintar(gc);
            //getFigura().dibujarInterior(gc, relacion);
            
        
        
    }

    private void pintar(GraphicsContext gc) {
       figura.pintarRectangulo(gc);
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}