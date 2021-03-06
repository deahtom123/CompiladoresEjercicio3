/*
 * Ejemplo desarrollado por Erick Navarro
 * Blog: e-navarro.blogspot.com
 * Julio - 2018
 */
package arbol;

import java.util.LinkedList;

/**
 * Clase que alberga todas las funciones definidas y las variables globales
 * declaradas.
 * @author erick
 */
public class Arbol implements Instruccion{
    /**
     * Lista de las instrucciones (Funciones y declaraciones de variables globales) que componen el archivo.
     */
    private final LinkedList<Instruccion> instrucciones;
    /**
     * Constructor de la clase Arbol
     * @param a Lista de instrucciones que conforman al Arbol
     */
    public Arbol(LinkedList<Instruccion> a) {
        instrucciones=a;
    }
    /**
     * Método que ejecuta la instrucción operación, es una sobreescritura del 
     * método ejecutar que se debe programar por la implementación de la interfaz
     * instrucción
     * @param ts tabla de símbolos del ámbito padre de la sentencia
     * @return Esta instrucción retorna el valor producido por la operación que se ejecutó
     */    
    @Override
    public Object ejecutar(TablaDeSimbolos ts,Arbol ar) {
        for(Instruccion ins:instrucciones){
            if(ins instanceof Declaracion){
                Declaracion d=(Declaracion)ins;
                d.ejecutar(ts, ar);
            }
        }
        for(Instruccion ins:instrucciones){
            if(ins instanceof Function){
                Function f=(Function)ins;
                String id=f.getIdentificador();
                if("main".equals(id)){
                    f.setValoresParametros(new LinkedList<>());
                    f.ejecutar(ts, ar);
                    break;
                }
            }
        }
        return null;
    }
    /**
     * Método que retorna el sub-arbol de una función que es buscada por su 
     * identificador.
     * @param identificador Identificador de la función buscada
     * @return Sub-arbol de la función si esta existe o null en caso de que 
     * no fuera encontrada.
     */
    public Function getFunction(String identificador){
        for(Instruccion ins:instrucciones){
            if(ins instanceof Function){
                Function f=(Function)ins;
                String id=f.getIdentificador();
                if(identificador.toLowerCase().equals(id)){
                    return f;
                }
            }
        }
        return null;
    }
    
}
