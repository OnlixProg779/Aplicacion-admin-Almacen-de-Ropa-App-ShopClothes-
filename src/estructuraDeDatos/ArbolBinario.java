package estructuraDeDatos;

import java.util.Comparator;

public class ArbolBinario<T> {

    NodoArbol<T> raiz;
    String nombre;
    Comparator<T> comparador;
    private String imprimir;
    private T datoMayor;
    private T datoMenor;
    private int contador;
    private int altura;
    private int hojas;
    private int i;
    private T datoGet;

    private int comparaDato(T t1, T t2) {
        if (comparador == null) {
            return ((Comparable) t1).compareTo(t2);
        } else {
            return this.comparador.compare(t1, t2);
        }
    }

    public ArbolBinario(String nombre) {
        raiz = null;
        this.nombre = nombre;
    }

    public String getNombreDelArbol() {
        return nombre;
    }

    public ArbolBinario() {
        raiz = null;

    }

    public void add(T dato) {
        NodoArbol<T> nuevo = new NodoArbol<T>(dato);
        if (raiz == null) {
            raiz = nuevo;
        } else {
            NodoArbol<T> aux = raiz;
            NodoArbol<T> padre;
            while (true) {
                padre = aux;
                if (comparaDato(dato, aux.dato) < 0) {
                    aux = aux.hijoIzquiero;
                    if (aux == null) {
                        padre.hijoIzquiero = nuevo;
                        return;
                    }

                } else {
                    aux = aux.hijoDerecho;
                    if (aux == null) {
                        padre.hijoDerecho = nuevo;
                        return;
                    }
                }
            }
        }
    }

    public boolean vacio() {
        return raiz == null;
    }

    public String inOrden() {
        imprimir = "";
        return inOrden(raiz);
    }

    private String inOrden(NodoArbol<T> raiz) {

        if (raiz != null) {
            inOrden(raiz.hijoIzquiero);
            imprimir = imprimir + raiz.dato.toString() + "\n";
            inOrden(raiz.hijoDerecho);
            return imprimir;
        } else {
            return imprimir;
        }
    }

    public String preOrden() {
        imprimir = "";
        return preOrden(raiz);
    }

    private String preOrden(NodoArbol<T> raiz) {

        if (raiz != null) {
            imprimir = imprimir + raiz.dato.toString() + "\n";
            preOrden(raiz.hijoIzquiero);

            preOrden(raiz.hijoDerecho);
            return imprimir;
        } else {
            return imprimir;
        }
    }

    public String posOrden() {
        imprimir = "";
        return posOrden(raiz);
    }

    public int retornarAltura() {
        altura = 0;
        retornarAltura(raiz, 1);
        return altura;
    }

    private void retornarAltura(NodoArbol<T> reco, int nivel) {
        if (reco != null) {
            retornarAltura(reco.hijoIzquiero, nivel + 1);
            if (nivel > altura) {
                altura = nivel;
            }
            retornarAltura(reco.hijoDerecho, nivel + 1);
        }
    }

    private String posOrden(NodoArbol<T> raiz) {

        if (raiz != null) {
            posOrden(raiz.hijoIzquiero);

            posOrden(raiz.hijoDerecho);
            imprimir = imprimir + raiz.dato.toString() + "\n";
            return imprimir;
        } else {
            return imprimir;
        }

    }

    public T search(T dato) {
        NodoArbol<T> aux = raiz;
        if (raiz != null) {
            while (comparaDato(dato, aux.dato) != 0) {
                if (comparaDato(dato, aux.dato) < 0) {
                    aux = aux.hijoIzquiero;
                } else {
                    aux = aux.hijoDerecho;
                }
                if (aux == null) {
                    return null;
                }
            }
            return aux.dato;
        } else {
            return null;
        }
    }

    public NodoArbol<T> searchNodo(T dato) {
        NodoArbol<T> aux = raiz;

        while (comparaDato(dato, aux.dato) != 0) {
            if (comparaDato(dato, aux.dato) < 0) {
                aux = aux.hijoIzquiero;
            } else {
                aux = aux.hijoDerecho;
            }
            if (aux == null) {
                return null;
            }
        }
        return aux;
    }

    public boolean remove(T dato) {
        NodoArbol<T> aux = raiz;
        NodoArbol<T> padre = raiz;
        boolean esHijoIzq = true;
        while (comparaDato(dato, aux.dato) != 0) {
            padre = aux;
            if (comparaDato(dato, aux.dato) < 0) {
                esHijoIzq = true;
                aux = aux.hijoIzquiero;
            } else {
                esHijoIzq = false;
                aux = aux.hijoDerecho;
            }
            if (aux == null) {
                return false;
            }
        }

        if (aux.hijoIzquiero == null && aux.hijoDerecho == null) {
            if (aux == raiz) {
                raiz = null;
            } else if (esHijoIzq) {
                padre.hijoIzquiero = null;
            } else {
                padre.hijoDerecho = null;
            }
        } else if (aux.hijoDerecho == null) {
            if (aux == raiz) {
                raiz = raiz.hijoIzquiero;
            } else if (esHijoIzq) {
                padre.hijoIzquiero = aux.hijoIzquiero;
            } else {
                padre.hijoDerecho = aux.hijoIzquiero;
            }
        } else if (aux.hijoIzquiero == null) {
            if (aux == raiz) {
                raiz = aux.hijoDerecho;
            } else if (esHijoIzq) {
                padre.hijoIzquiero = aux.hijoDerecho;
            } else {
                padre.hijoDerecho = aux.hijoDerecho;
            }
        } else {
            NodoArbol<T> reemplazo = obternerReemplazo(aux);
            if (aux == raiz) {
                raiz = reemplazo;
            } else if (esHijoIzq) {
                padre.hijoIzquiero = reemplazo;
            } else {
                padre.hijoDerecho = reemplazo;
            }
            reemplazo.hijoIzquiero = aux.hijoIzquiero;
        }
        return true;
    }

    private NodoArbol<T> obternerReemplazo(NodoArbol<T> nodoReemp) {
        NodoArbol<T> reemplazarPadre = nodoReemp;
        NodoArbol<T> reemplazo = nodoReemp;
        NodoArbol<T> aux = nodoReemp.hijoDerecho;
        while (aux != null) {
            reemplazarPadre = reemplazo;
            reemplazo = aux;
            aux = aux.hijoIzquiero;
        }
        if (reemplazo != nodoReemp.hijoDerecho) {
            reemplazarPadre.hijoIzquiero = reemplazo.hijoDerecho;
            reemplazo.hijoDerecho = nodoReemp.hijoDerecho;
        }
        return reemplazo;
    }

    public synchronized T datoMayor() {
        if (raiz != null) {
            datoMayor = raiz.dato;
            return mayor(raiz);
        } else {
            return null;
        }
    }

    private synchronized T mayor(NodoArbol<T> aux) {
        if (aux != null) {

            if (comparaDato(aux.dato, datoMayor) > 0) {
                datoMayor = aux.dato;
            }
            mayor(aux.hijoIzquiero);
            mayor(aux.hijoDerecho);
            return datoMayor;
        } else {
            return datoMayor;
        }

    }

    public synchronized T datoMenor() {
        if (raiz != null) {
            datoMenor = raiz.dato;
            return menor(raiz);
        } else {
            return null;
        }
    }

    private synchronized T menor(NodoArbol<T> aux) {
        if (aux != null) {

            if (comparaDato(aux.dato, datoMenor) < 0) {
                datoMenor = aux.dato;
            }
            menor(aux.hijoIzquiero);
            menor(aux.hijoDerecho);
            return datoMenor;
        } else {
            return datoMenor;
        }

    }

    public synchronized int contadorElementos() {
        if (raiz != null) {
            contador = 0;
            datoMenor = raiz.dato;
            return contadorElementos(raiz);
        } else {
            return 0;
        }
    }

    private synchronized int contadorElementos(NodoArbol<T> aux) {
        if (aux != null) {

            contador++;
            contadorElementos(aux.hijoIzquiero);
            contadorElementos(aux.hijoDerecho);
            return contador;
        } else {
            return contador;
        }

    }

    public synchronized int contadorHojas() {
        if (raiz != null) {
            hojas = 0;
            datoMenor = raiz.dato;
            return contadorHojas(raiz);
        } else {
            return 0;
        }
    }

    private synchronized int contadorHojas(NodoArbol<T> aux) {

        if (aux != null) {
            if (aux.hijoDerecho == null && aux.hijoIzquiero == null) {
                hojas++;
            }

            contadorHojas(aux.hijoIzquiero);
            contadorHojas(aux.hijoDerecho);
            return hojas;
        } else {
            return hojas;
        }

    }

    public T getPreOrden(int i) {
        this.i = i;
        datoGet = null;
        getPreOrden(raiz);
        return datoGet;
    }

    private void getPreOrden(NodoArbol<T> aux) {
        if (aux != null) {
            if (i == 1) {
                datoGet = aux.dato;
                return;
            } else {
                i--;
            }
            if (datoGet == null) {
                getPreOrden(aux.hijoIzquiero);
            } else {
                return;
            }
            if (datoGet == null) {
                getPreOrden(aux.hijoDerecho);
            } else {
                return;
            }

        }

    }
}

