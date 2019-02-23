package estructuraDeDatos;


	
	public class NodoArbol<T> {

	    T dato;
	    NodoArbol<T> hijoDerecho;
	    NodoArbol<T> hijoIzquiero;
	    

	    public NodoArbol() {

	    }


	    public NodoArbol(T dato) {
	        this.dato = dato;
	        this.hijoDerecho = null;
	        this.hijoIzquiero = null;

	    }

	    public String toString() {
	        return dato.toString();
	    }
	}


