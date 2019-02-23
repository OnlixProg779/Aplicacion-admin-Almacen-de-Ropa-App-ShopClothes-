/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuraDeDatos;

/**
 *
 * @author OnlixProg
 */
public class Nodo<T> {
     private T dato;

    private Nodo<T> izquierda;

    private Nodo<T> derecha;

    int factorE;

    public Nodo() {
        dato = null;
        izquierda = null;
        derecha = null;
        factorE = 0;
    }

    public Nodo(T dato) {
        this.dato = dato;
        izquierda = null;
        derecha = null;
        factorE = 0;
    }

    public Nodo<T> getIzquierda() {
        return izquierda;
    }

    public Nodo<T> getDerecha() {
        return derecha;
    }

    public T getDato() {
        return dato;
    }

    public void setDerecha(Nodo<T> derecha) {
        this.derecha = derecha;
    }

    public void setIzquierda(Nodo<T> izquierda) {
        this.izquierda = izquierda;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public int getFactorE() {
        int altDer = 0;
        int altIzq = 0;
        if (this.getDerecha() != null) {
            altDer = this.getDerecha().getAltura();
        }
        if (this.getIzquierda() != null) {
            altIzq = this.getIzquierda().getAltura();
        }

        return (altDer - altIzq);
    }

    public void setFactorE(int fe) {
        this.factorE = fe;
    }

    public int getAltura() {
        int hIzq = 0;
        int hDer = 0;

        if (this.getDato() == null) {
            return 0;
        }

        if (this.getIzquierda() != null) {
            hIzq = this.getIzquierda().getAltura();
        } else {
            hIzq = 0;
        }

        if (this.getDerecha() != null) {
            hDer = this.getDerecha().getAltura();
        } else {
            hDer = 0;
        }
        return Math.max(hIzq, hDer) + 1;
    }

}
