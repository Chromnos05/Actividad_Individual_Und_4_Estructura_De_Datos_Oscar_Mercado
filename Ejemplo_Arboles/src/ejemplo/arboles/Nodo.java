package ejemplo.arboles;

/**
 * Representa un nodo individual dentro de un arbol binario.
 * Cada nodo contiene un valor numerico y referencias a sus
 * hijos izquierdo y derecho.
 */
public class Nodo {

    /** Valor numerico almacenado en el nodo */
    private int valor;

    /** Referencia al hijo izquierdo */
    private Nodo izquierdo;

    /** Referencia al hijo derecho */
    private Nodo derecho;

    /**
     * Constructor: crea un nodo con un valor dado.
     * Los hijos se inicializan como null.
     *
     * @param valor valor numerico del nodo
     */
    public Nodo(int valor) {
        this.valor = valor;
        this.izquierdo = null;
        this.derecho = null;
    }

    /**
     * Retorna el valor almacenado en el nodo.
     *
     * @return valor del nodo
     */
    public int getValor() {
        return valor;
    }

    /**
     * Modifica el valor almacenado en el nodo.
     *
     * @param valor nuevo valor del nodo
     */
    public void setValor(int valor) {
        this.valor = valor;
    }

    /**
     * Retorna el hijo izquierdo del nodo.
     *
     * @return nodo izquierdo, o null si no existe
     */
    public Nodo getIzquierdo() {
        return izquierdo;
    }

    /**
     * Asigna el hijo izquierdo del nodo.
     *
     * @param izquierdo nodo a asignar como hijo izquierdo
     */
    public void setIzquierdo(Nodo izquierdo) {
        this.izquierdo = izquierdo;
    }

    /**
     * Retorna el hijo derecho del nodo.
     *
     * @return nodo derecho, o null si no existe
     */
    public Nodo getDerecho() {
        return derecho;
    }

    /**
     * Asigna el hijo derecho del nodo.
     *
     * @param derecho nodo a asignar como hijo derecho
     */
    public void setDerecho(Nodo derecho) {
        this.derecho = derecho;
    }
}
