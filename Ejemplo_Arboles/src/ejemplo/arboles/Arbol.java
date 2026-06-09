package ejemplo.arboles;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementacion de un Arbol Binario de Busqueda (BST).
 * Los valores menores se colocan a la izquierda y los
 * mayores a la derecha. No permite valores duplicados.
 */
public class Arbol {

    /** Raiz del arbol */
    private Nodo raiz;

    /**
     * Constructor: crea un arbol vacio.
     */
    public Arbol() {
        this.raiz = null;
    }

    /**
     * Inserta un nuevo valor en el arbol.
     * Si el valor ya existe, no hace nada.
     *
     * @param valor valor a insertar
     */
    public void insertar(int valor) {
        raiz = insertarRecursivo(raiz, valor);
    }

    /**
     * Metodo recursivo auxiliar para insertar un valor.
     * Busca la posicion correcta segun la propiedad del BST.
     *
     * @param actual nodo actual en la recursion
     * @param valor  valor a insertar
     * @return nodo actual (posiblemente modificado)
     */
    private Nodo insertarRecursivo(Nodo actual, int valor) {
        if (actual == null) {
            return new Nodo(valor);
        }
        if (valor < actual.getValor()) {
            actual.setIzquierdo(insertarRecursivo(actual.getIzquierdo(), valor));
        } else if (valor > actual.getValor()) {
            actual.setDerecho(insertarRecursivo(actual.getDerecho(), valor));
        }
        return actual;
    }

    /**
     * Consulta si un valor existe en el arbol.
     *
     * @param valor valor a buscar
     * @return true si existe, false en caso contrario
     */
    public boolean consultar(int valor) {
        return consultarRecursivo(raiz, valor);
    }

    /**
     * Metodo recursivo auxiliar para buscar un valor.
     * Aprovecha la propiedad del BST para decidir
     * hacia donde descendr.
     *
     * @param actual nodo actual en la recursion
     * @param valor  valor a buscar
     * @return true si se encuentra el valor, false si no
     */
    private boolean consultarRecursivo(Nodo actual, int valor) {
        if (actual == null) {
            return false;
        }
        if (valor == actual.getValor()) {
            return true;
        }
        return valor < actual.getValor()
                ? consultarRecursivo(actual.getIzquierdo(), valor)
                : consultarRecursivo(actual.getDerecho(), valor);
    }

    /**
     * Modifica un valor del arbol reemplazandolo por otro.
     * Elimina el valor viejo e inserta el nuevo.
     *
     * @param valorViejo valor existente a reemplazar
     * @param valorNuevo nuevo valor a insertar
     * @return true si la modificacion fue exitosa, false si no
     *         se encontro el valor viejo
     */
    public boolean modificar(int valorViejo, int valorNuevo) {
        if (!consultar(valorViejo)) {
            return false;
        }
        eliminar(valorViejo);
        insertar(valorNuevo);
        return true;
    }

    /**
     * Elimina un valor del arbol.
     * Si el valor no existe, no hace nada.
     *
     * @param valor valor a eliminar
     */
    public void eliminar(int valor) {
        raiz = eliminarRecursivo(raiz, valor);
    }

    /**
     * Metodo recursivo auxiliar para eliminar un valor.
     * Maneja tres casos:
     * - Sin hijos: se elimina directamente.
     * - Un hijo: se reemplaza por ese hijo.
     * - Dos hijos: se reemplaza por el menor del subarbol derecho.
     *
     * @param actual nodo actual en la recursion
     * @param valor  valor a eliminar
     * @return nodo actual (posiblemente modificado o null)
     */
    private Nodo eliminarRecursivo(Nodo actual, int valor) {
        if (actual == null) {
            return null;
        }
        if (valor == actual.getValor()) {
            // Caso 1: sin hijos (hoja)
            if (actual.getIzquierdo() == null && actual.getDerecho() == null) {
                return null;
            }
            // Caso 2: solo hijo derecho
            if (actual.getIzquierdo() == null) {
                return actual.getDerecho();
            }
            // Caso 2: solo hijo izquierdo
            if (actual.getDerecho() == null) {
                return actual.getIzquierdo();
            }
            // Caso 3: dos hijos
            int menorValor = encontrarMenor(actual.getDerecho());
            actual.setValor(menorValor);
            actual.setDerecho(eliminarRecursivo(actual.getDerecho(), menorValor));
            return actual;
        }
        if (valor < actual.getValor()) {
            actual.setIzquierdo(eliminarRecursivo(actual.getIzquierdo(), valor));
        } else {
            actual.setDerecho(eliminarRecursivo(actual.getDerecho(), valor));
        }
        return actual;
    }

    /**
     * Encuentra el valor mas pequeno a partir de un nodo dado.
     * Se usa en la eliminacion con dos hijos.
     *
     * @param raiz nodo desde el cual buscar el minimo
     * @return valor minimo encontrado
     */
    private int encontrarMenor(Nodo raiz) {
        return raiz.getIzquierdo() == null
                ? raiz.getValor()
                : encontrarMenor(raiz.getIzquierdo());
    }

    /**
     * Recorrido inorden (izquierdo - raiz - derecho).
     * Devuelve los valores en orden ascendente.
     *
     * @return lista con los valores en inorden
     */
    public List<Integer> inorden() {
        List<Integer> resultado = new ArrayList<>();
        inordenRecursivo(raiz, resultado);
        return resultado;
    }

    /**
     * Metodo recursivo auxiliar para el recorrido inorden.
     */
    private void inordenRecursivo(Nodo actual, List<Integer> resultado) {
        if (actual != null) {
            inordenRecursivo(actual.getIzquierdo(), resultado);
            resultado.add(actual.getValor());
            inordenRecursivo(actual.getDerecho(), resultado);
        }
    }

    /**
     * Recorrido preorden (raiz - izquierdo - derecho).
     * Util para copiar o serializar el arbol.
     *
     * @return lista con los valores en preorden
     */
    public List<Integer> preorden() {
        List<Integer> resultado = new ArrayList<>();
        preordenRecursivo(raiz, resultado);
        return resultado;
    }

    /**
     * Metodo recursivo auxiliar para el recorrido preorden.
     */
    private void preordenRecursivo(Nodo actual, List<Integer> resultado) {
        if (actual != null) {
            resultado.add(actual.getValor());
            preordenRecursivo(actual.getIzquierdo(), resultado);
            preordenRecursivo(actual.getDerecho(), resultado);
        }
    }

    /**
     * Recorrido postorden (izquierdo - derecho - raiz).
     * Util para eliminar o liberar el arbol.
     *
     * @return lista con los valores en postorden
     */
    public List<Integer> postorden() {
        List<Integer> resultado = new ArrayList<>();
        postordenRecursivo(raiz, resultado);
        return resultado;
    }

    /**
     * Metodo recursivo auxiliar para el recorrido postorden.
     */
    private void postordenRecursivo(Nodo actual, List<Integer> resultado) {
        if (actual != null) {
            postordenRecursivo(actual.getIzquierdo(), resultado);
            postordenRecursivo(actual.getDerecho(), resultado);
            resultado.add(actual.getValor());
        }
    }
}
