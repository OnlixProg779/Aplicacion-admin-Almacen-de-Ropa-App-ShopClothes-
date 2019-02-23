/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuraDeDatos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author OnlixProg
 */
public class ArbolAVL<T> {

    private Nodo<T> raiz;
private String nombre;
    private Comparator<T> comparador;
    private int i;
    private boolean encontrado;
    private T datoGet;
    private int hojas;
    private T datoMenor;
    private T menor;
    private T mayor;
    private int altura;

    public Nodo<T> getRaiz() {
        return raiz;
    }

    public String getNombre() {
        return nombre;
    }

    public void vaciar() {
    	raiz = null;
    }
    public ArbolAVL(String nombre) {
        this.nombre  = nombre;
        this.raiz = null;
    }
    
    
    public ArbolAVL() {
        this.raiz = null;
    }

    public ArbolAVL(Comparator<T> cmp) {
        this.comparador = cmp;
    }

    private int compararDato(T t1, T t2) {
        if (this.comparador == null) {
            return ((Comparable) t1).compareTo(t2);
        } else {
            return this.comparador.compare(t1, t2);
        }
    }

    public boolean add(T e) throws ClassCastException, NullPointerException, IllegalStateException {
        Nodo<T> nodo = new Nodo<T>(e);
        boolean salir = false;
        boolean der = false;
        Nodo<T> raizTmp = this.raiz;

        int altIzq, altDer;

        //no exist�a arbol
        if (raizTmp == null) {
            this.raiz = nodo;
            return true;
        } else //estaba ya en el arbol?
        if (this.contains(nodo.getDato())) {
            return false;
        } //no estaba antes en el arbol
        else {
            while (!salir) {

                //es mayor el nodo a insertar que la raiz?    				
                if (this.compararDato(nodo.getDato(), raizTmp.getDato()) > 0) {
                    if (raizTmp.getDerecha() != null) {
                        raizTmp = raizTmp.getDerecha();
                    } else {
                        salir = true;
                        der = true;
                    }

                } //el nodo es menor que la raiz
                else {
                    if (raizTmp.getIzquierda() != null) {
                        raizTmp = raizTmp.getIzquierda();
                    } else {
                        salir = true;
                    }
                }
            }

            //tengo que insertarlo a la derecha?
            if (der) {
                raizTmp.setDerecha(nodo);
            } //lo inserto a la izquierda
            else {
                raizTmp.setIzquierda(nodo);
            }

            //mientras no este equilibrado el arbol	miramos donde reestructurar
            while (equilibrado(this.raiz) < 0) {
                raizTmp = padre(raizTmp);

                if (raizTmp.getDerecha() == null) {
                    altDer = 0;
                } else {
                    altDer = raizTmp.getDerecha().getAltura();
                }

                if (raizTmp.getIzquierda() == null) {
                    altIzq = 0;
                } else {
                    altIzq = raizTmp.getIzquierda().getAltura();
                }

                Nodo<T> cambiar = estructurar(raizTmp, altIzq, altDer);
                Nodo<T> superior = padre(raizTmp);

                //si los nodos modificados tenian un padre anteriormente
                if (compararDato(superior.getDato(), raizTmp.getDato()) != 0) {
                    if (superior.getIzquierda() != null && compararDato(superior.getIzquierda().getDato(), raizTmp.getDato()) == 0) {
                        superior.setIzquierda(cambiar);
                    } else if (superior.getDerecha() != null && compararDato(superior.getDerecha().getDato(), raizTmp.getDato()) == 0) {
                        superior.setDerecha(cambiar);
                    }
                } else {
                    this.raiz = cambiar;
                }
            }
            return true;
        }
    }

    private int equilibrado(Nodo<T> n) {
        int hIzq = 0;
        int hDer = 0;

        if (n == null) {
            return 0;
        }

        hIzq = equilibrado(n.getIzquierda());

        if (hIzq < 0) {
            return hIzq;
        }

        hDer = equilibrado(n.getDerecha());

        if (hDer < 0) {
            return hDer;
        }

        //si no es equilibrado
        if (Math.abs(hIzq - hDer) > 1) {
            return -1;
        }

        //si el trozo de arbol es AVL devolvemos la altura
        return Math.max(hIzq, hDer) + 1;
    }
  public int retornarAltura() {
        altura = 0;
        retornarAltura(raiz, 1);
        return altura;
    }

    private void retornarAltura(Nodo<T> reco, int nivel) {
        if (reco != null) {
            retornarAltura(reco.getIzquierda(), nivel + 1);
            if (nivel > altura) {
                altura = nivel;
            }
            retornarAltura(reco.getDerecha(), nivel + 1);
        }
    }
    public Nodo<T> getNodo(T dato) {
        Nodo<T> raizTmp = this.raiz;

        if (this.isEmpty()) {
            return null;
        }

        while (raizTmp.getDerecha() != null || raizTmp.getIzquierda() != null) {

            if (this.compararDato(dato, raizTmp.getDato()) > 0) {
                if (raizTmp.getDerecha() != null) {
                    raizTmp = raizTmp.getDerecha();
                } else {
                    return null;
                }
            } else if (this.compararDato(dato, raizTmp.getDato()) < 0) {
                if (raizTmp.getIzquierda() != null) {
                    raizTmp = raizTmp.getIzquierda();
                } else {
                    return null;
                }
            }

            if (this.compararDato(dato, raizTmp.getDato()) == 0) {
                return raizTmp;
            }
        }

        return raizTmp;
    }

    public boolean contains(T o) throws ClassCastException, NullPointerException {
        Nodo<T> raizTmp = raiz;
        if (this.isEmpty()) {
            return false;
        }

        //si es la raiz el buscado
        if (this.compararDato((T) o, raizTmp.getDato()) == 0) {
            return true;
        }

        while (raizTmp.getDerecha() != null || raizTmp.getIzquierda() != null) {

            if (this.compararDato((T) o, raizTmp.getDato()) > 0) {
                if (raizTmp.getDerecha() != null) {
                    raizTmp = raizTmp.getDerecha();
                } else {
                    return false;
                }
            } else if (this.compararDato((T) o, raizTmp.getDato()) < 0) {
                if (raizTmp.getIzquierda() != null) {
                    raizTmp = raizTmp.getIzquierda();
                } else {
                    return false;
                }
            }

            if (this.compararDato((T) o, raizTmp.getDato()) == 0) {
                return true;
            }
        }
        return false;
    }

    private Nodo<T> rotacionSimpleIzquierda(Nodo<T> nodo) {
        Nodo<T> nodoTmp = nodo;

        nodo = nodoTmp.getDerecha(); //clone??
        nodoTmp.setDerecha(nodo.getIzquierda());

        nodo.setIzquierda(nodoTmp);

        return nodo;
    }

    /**
     * Realiza la operacion de rotacion simple derecha en el subarbol . que
     * tiene como raiz el nodo pasado por parametro.
     *
     * @param nodo raiz del subarbol a rotar.
     *
     * @return nodo nodo que pasa a ser la raiz del subarbol estructurado.
     */
    private Nodo<T> rotacionSimpleDerecha(Nodo<T> nodo) {
        Nodo<T> nodoTmp = nodo;
        nodo = nodoTmp.getIzquierda();

        nodoTmp.setIzquierda(nodo.getDerecha());
        nodo.setDerecha(nodoTmp);

        return nodo;
    }

    /**
     * Realiza la operacion de rotacion compeusta izquierda-derecha en el
     * subarbol . que tiene como raiz el nodo pasado por parametro.
     *
     * @param nodo raiz del subarbol a rotar.
     *
     * @return nodo nodo que pasa a ser la raiz del subarbol estructurado.
     */
    private Nodo<T> rotacionCompuestaIzquierda(Nodo<T> nodo) {
        Nodo<T> nodoTmp = nodo;

        nodoTmp = rotacionSimpleIzquierda(nodoTmp.getIzquierda());

        nodo.setIzquierda(nodoTmp);

        nodoTmp = rotacionSimpleDerecha(nodo);

        return nodoTmp;
    }

    /**
     * Realiza la operacion de rotacion compuesta derecha-izquierda en el
     * subarbol que tiene como raiz el nodo pasado por parametro.
     *
     * @param nodo raiz del subarbol a rotar.
     *
     * @return nodo nodo que pasa a ser la raiz del subarbol estructurado.
     */
    private Nodo<T> rotacionCompuestaDerecha(Nodo<T> nodo) {
        Nodo<T> nodoTmp = nodo;

        nodoTmp = rotacionSimpleDerecha(nodoTmp.getDerecha());

        nodo.setDerecha(nodoTmp);

        nodoTmp = rotacionSimpleIzquierda(nodo);

        return nodoTmp;
    }

    private int ObtenerFactorE(Nodo<T> nodo) {
        if (nodo == null) {
            return -1;
        } else {
            return nodo.factorE;
        }
    }

    public boolean isEmpty() {
        return raiz == null;
    }

    public int extraeFactorE(Nodo<T> nodo) {
        if (nodo != null) {
            return nodo.getFactorE();
        } else {
            return 0;
        }
    }

    public int size() {
        return size(raiz);
    }

    int size(Nodo<T> arbol) {
        if (arbol == null) {
            return 0;
        } else {
            return 1 + size(arbol.getIzquierda()) + size(arbol.getDerecha());
        }
    }

    public T getPreOrden(int i) {
        this.i = i;
        datoGet = null;
        getPreOrden(raiz);
        return datoGet;
    }

    private void getPreOrden(Nodo<T> aux) {
        if (aux != null) {
            if (i == 0) {
                datoGet = aux.getDato();
                return;
            } else {
                i--;
            }
            if (datoGet == null) {
                getPreOrden(aux.getIzquierda());
            } else {
                return;
            }
            if (datoGet == null) {
                getPreOrden(aux.getDerecha());
            } else {
                return;
            }

        }

    }

    public synchronized int contadorHojas() {
        if (raiz != null) {
            hojas = 0;
            datoMenor = raiz.getDato();
            return contadorHojas(raiz);
        } else {
            return 0;
        }
    }

    private synchronized int contadorHojas(Nodo<T> aux) {

        if (aux != null) {
            if (aux.getDerecha() == null && aux.getIzquierda() == null) {
                hojas++;
            }

            contadorHojas(aux.getIzquierda());
            contadorHojas(aux.getDerecha());
            return hojas;
        } else {
            return hojas;
        }

    }

    private String preOrden(Nodo<T> arbol) {
        if (arbol != null) {
            men = men + " " + arbol.getDato() + "\n";
            preOrden(arbol.getIzquierda());
            preOrden(arbol.getDerecha());
        }
        return men;
    }

    public String preOrden() {
        men = "";
        return preOrden(raiz);
    }

    private String postOrden(Nodo<T> arbol) {
        if (arbol != null) {
            postOrden(arbol.getIzquierda());
            postOrden(arbol.getDerecha());
            men = men + " " + arbol.getDato() + "\n";
        }
        return men;
    }

    public String postOrden() {
        men = "";
        return postOrden(this.raiz);
    }

    String men;

    private String inOrden(Nodo<T> arbol) {
        if (arbol != null) {
            inOrden(arbol.getIzquierda());
            men = men + " " + arbol.getDato() + "\n";
            inOrden(arbol.getDerecha());
        }
        return men;
    }

    public String inOrden() {
        men = "";
        return inOrden(this.raiz);
    }

    public T buscarMenor() {
        menor = null;
        return buscarmenor(raiz);
    }

    private T buscarmenor(Nodo<T> aux) {
        if (aux != null) {
            menor = aux.getDato();
            buscarmenor(aux.getIzquierda());
        }
        return menor;
    }

    public T buscarMayor() {
        mayor = null;
        return buscarmayor(raiz);
    }

    private T buscarmayor(Nodo<T> aux) {
        if (aux != null) {
            mayor = aux.getDato();
            buscarmayor(aux.getDerecha());
        }
        return mayor;
    }

    public int altura(T dato) {
        Nodo<T> nodo = this.getNodo(dato);
        if (!this.contains(dato)) {
            return -1;
        }

        return nodo.getAltura();
    }

    public boolean remove(T o) throws ClassCastException, NullPointerException {
        Nodo<T> borrar = null, mirar = null, cambiar = null, nPadre = null;
        Nodo<T> raizTmp = this.raiz;
        T c_aux, d_aux;
        boolean salir = false;
        int altDer = 0;
        int altIzq = 0;
        int a = 0;

        if (this.isEmpty()) {
            return false;
        }

        //el nodo a borrar es la raiz?
        if (this.compararDato((T) o, raizTmp.getDato()) == 0) {
            salir = true;
            borrar = raizTmp;
        }

        //si no es la raiz, lo buscamos
        while (!salir && (raizTmp.getDerecha() != null || raizTmp.getIzquierda() != null)) {

            if (this.compararDato((T) o, raizTmp.getDato()) > 0) {
                if (raizTmp.getDerecha() != null) {
                    raizTmp = raizTmp.getDerecha();
                } else {
                    return false;
                }
            } else if (this.compararDato((T) o, raizTmp.getDato()) < 0) {

                if (raizTmp.getIzquierda() != null) {
                    raizTmp = raizTmp.getIzquierda();
                } else {
                    return false;
                }
            }

            if (this.compararDato((T) o, raizTmp.getDato()) == 0) {
                salir = true;
                borrar = raizTmp;
            }
        }

        //existe el nodo a borrar?
        if (salir) {
            mirar = borrar;

            //es una hoja?
            if (borrar.getIzquierda() == null && borrar.getDerecha() == null) {
                mirar = padre(borrar);
                nPadre = padre(borrar);

                //es un arbol raiz con solo un nodo raiz?
                if (this.size() == 1) {
                    this.raiz = null;
                }

                if (nPadre.getIzquierda() != null && compararDato(nPadre.getIzquierda().getDato(), borrar.getDato()) == 0) {
                    nPadre.setIzquierda(null);
                } else if (nPadre.getDerecha() != null && compararDato(nPadre.getDerecha().getDato(), borrar.getDato()) == 0) {
                    nPadre.setDerecha(null);
                }
                //nos lo cargamos
                borrar.setDato(null);
            } //solo tiene un hijo? (o 2 pero en la misma altura) entonces la altura de ese subarbol ser� 1 o 2 (altura raiz = 1)
            else if (borrar.getAltura() <= 2) {

                if (borrar.getIzquierda() != null) {
                    borrar.setDato(borrar.getIzquierda().getDato());
                    borrar.setIzquierda(null);
                } else if (borrar.getDerecha() != null) {
                    borrar.setDato(borrar.getDerecha().getDato());
                    borrar.setDerecha(null);
                }
            } //cuando no es ni un hoja ni su padre. Es decir, est� por medio del arbol.
            else {

                //buscamos el mayor de la izquierda
                if (borrar.getIzquierda() != null) {
                    cambiar = borrar.getIzquierda();

                    while (cambiar.getDerecha() != null) {
                        cambiar = cambiar.getDerecha();
                    }
                } //buscamos el menor de la derecha
                else if (borrar.getDerecha() != null) {
                    cambiar = cambiar.getDerecha();

                    while (cambiar.getIzquierda() != null) {
                        cambiar = cambiar.getIzquierda();
                    }
                }

                c_aux = cambiar.getDato();
                Nodo<T> papa = padre(cambiar);

                //si el nodo que hemos cambiado se ha quedado con alg�n hijo...
                if (cambiar.getIzquierda() != null || cambiar.getDerecha() != null) {
                    if (cambiar.getIzquierda() != null) {
                        cambiar.setDato(cambiar.getIzquierda().getDato());
                        cambiar.setIzquierda(null);
                    } else if (cambiar.getDerecha() != null) {
                        cambiar.setDato(cambiar.getDerecha().getDato());
                        cambiar.setDerecha(null);
                    }
                } //si no tiene hijos ya, lo eliminamos sin m�s
                else {
                    if (papa.getIzquierda() != null && compararDato(papa.getIzquierda().getDato(), cambiar.getDato()) == 0) {
                        papa.setIzquierda(null);
                    } else {
                        papa.setDerecha(null);
                    }
                    cambiar.setDato(borrar.getDato());
                    borrar.setDato(c_aux);
                }
            }

            while (equilibrado(this.raiz) < 0) {
                if (mirar.getDerecha() == null) {
                    altDer = 0;
                } else {
                    altDer = mirar.getDerecha().getAltura();
                }

                if (mirar.getIzquierda() == null) {
                    altIzq = 0;
                } else {
                    altIzq = mirar.getIzquierda().getAltura();
                }

                Nodo<T> cambiar2 = estructurar(mirar, altIzq, altDer);
                Nodo<T> superior = padre(mirar);

                //si los nodos modificados tenian un padre anteriormente
                if (compararDato(superior.getDato(), mirar.getDato()) != 0) {
                    if (superior.getIzquierda() != null && compararDato(superior.getIzquierda().getDato(), mirar.getDato()) == 0) {
                        superior.setIzquierda(cambiar2);
                    } else if (superior.getDerecha() != null && compararDato(superior.getDerecha().getDato(), mirar.getDato()) == 0) {
                        superior.setDerecha(cambiar2);
                    }
                } else {
                    this.raiz = cambiar2;
                }
                mirar = padre(mirar);
            }
            return true;
        }
        return false;
    }

    private Nodo<T> padre(Nodo<T> nodo) {
        Nodo<T> raizTmp = raiz;
        Stack<Nodo<T>> pila = new Stack<Nodo<T>>();
        pila.push(raizTmp);
        while (raizTmp.getDerecha() != null || raizTmp.getIzquierda() != null) {
            if (this.compararDato(nodo.getDato(), raizTmp.getDato()) > 0) {
                if (raizTmp.getDerecha() != null) {
                    raizTmp = raizTmp.getDerecha();
                }
            } else if (this.compararDato(nodo.getDato(), raizTmp.getDato()) < 0) {
                if (raizTmp.getIzquierda() != null) {
                    raizTmp = raizTmp.getIzquierda();
                }
            }
            if (this.compararDato(nodo.getDato(), raizTmp.getDato()) == 0) {
                return pila.pop();
            }

            pila.push(raizTmp);
        }
        return pila.pop();
    }

    private Nodo<T> estructurar(Nodo<T> nodo, int altIzq, int altDer) {
        if (extraeFactorE(nodo) == 2) {
            if (extraeFactorE(nodo.getDerecha()) == 1 || extraeFactorE(nodo.getDerecha()) == 0) {
                nodo = rotacionSimpleIzquierda(nodo);
            } else if (extraeFactorE(nodo.getDerecha()) == -1) {
                nodo = rotacionCompuestaDerecha(nodo);
            }
        } else if (extraeFactorE(nodo) == -2) {
            if (extraeFactorE(nodo.getIzquierda()) == -1 || extraeFactorE(nodo.getDerecha()) == 0) {
                nodo = rotacionSimpleDerecha(nodo);
            } else if (extraeFactorE(nodo.getIzquierda()) == 1) {
                nodo = rotacionCompuestaIzquierda(nodo);
            }
        }

        return nodo;
    }

    public boolean editarNodo(T buscarDato, T datoNuevo) {
        encontrado = true;
        if (getNodo(buscarDato) == null) {
            return false;
        } else {
            if (this.raiz.getIzquierda() != null) {
                if (compararDato(this.raiz.getIzquierda().getDato(), datoNuevo) < 0) {
                    if (this.raiz.getDerecha() != null) {
                        if (compararDato(this.raiz.getDerecha().getDato(), datoNuevo) > 0) {
                            getNodo(buscarDato).setDato(datoNuevo);

                        }
                    } else {
                        getNodo(buscarDato).setDato(datoNuevo);
                    }
                }

            } else {
                if (this.raiz.getDerecha() != null) {
                    if (compararDato(this.raiz.getDerecha().getDato(), datoNuevo) > 0) {
                        getNodo(buscarDato).setDato(datoNuevo);
                    }
                } else {
                    getNodo(buscarDato).setDato(datoNuevo);
                }
            }
            System.out.println("No se puede realizar el cambio");
        }
        return encontrado;
    }

    public Nodo<T> eliminarNodosHijos(Nodo<T> arbol, Nodo<T> arbol2) {
        Nodo<T> resul;
        if (arbol.getDerecha() != null) {
            resul = arbol;
            arbol.setDerecha(eliminarNodosHijos(arbol.getDerecha(), arbol2));
        } else {
            arbol2.setDato(arbol.getDato());
            resul = arbol.getIzquierda();
        }
        return resul;

    }
    public List<T> inOrdenList(){
		List<T> lista = new ArrayList<T>();
    	Nodo<T> nodo = this.getRaiz();  	
    	Stack<Nodo<T>> pila = new Stack<Nodo<T>>();
     	
     	while((nodo!=null &&nodo.getDato()!=null)|| !pila.empty()){
     		if(nodo!=null){
     			pila.push(nodo);
     			nodo = nodo.getIzquierda();
     		}else{
     			nodo = pila.pop();
     			lista.add(nodo.getDato());
     			nodo = nodo.getDerecha();
     		}
     	} 	
    	
    	return lista;
    }
    
    
    /**
     * Este m�todo recorre el �rbol mediante el recorrido PREORDEN y almacena cada
     * elemento en una lista que se devuelve al terminar el recorrido.
     * @return lista Lista en preorden con el contenido del arbol.
     */
    public List<T> preOrdenList(){
    	List<T> lista = new ArrayList<T>();
    	Nodo<T> nodo = this.getRaiz();  	
    	Stack<Nodo<T>> pila = new Stack<Nodo<T>>();

     	while((nodo!=null && nodo.getDato()!=null) || !pila.empty()){
     		if(nodo!=null){
     			lista.add(nodo.getDato());
     			pila.push(nodo);
     			nodo = nodo.getIzquierda();
     		}else{
     			nodo = pila.pop();
     			nodo = nodo.getDerecha();
     		}
     	} 	
    	
    	return lista;
    }
    

    /**
     * Este m�todo recorre el �rbol mediante el recorrido POSTORDEN y almacena cada
     * elemento en una lista que se devuelve al terminar el recorrido.
     * @return lista Lista en postOrden con el contenido del arbol.
     */
    public List<T> postOrdenList(){
    	List<T> lista = new ArrayList<T>();
    	Nodo<T> nodo = this.getRaiz();  	
    	Stack<Nodo<T>> pila1 = new Stack<Nodo<T>>();
    	Stack<Boolean> pila2 = new Stack<Boolean>();
    	
    	while((nodo!=null && nodo.getDato()!=null) || !pila1.empty()){
    		
    		if(nodo!=null){
    			pila1.push(nodo);
    			pila2.push(true);
    			nodo = nodo.getIzquierda();
    		}else{
    			nodo = pila1.pop();
    			if(pila2.pop()){
    				pila1.push(nodo);
    				pila2.push(false);
    				nodo = nodo.getDerecha();
    			}else{
    				lista.add(nodo.getDato());
    				nodo = null;
    			}
    		}
    	}
    	
    	return lista;
    }
}
