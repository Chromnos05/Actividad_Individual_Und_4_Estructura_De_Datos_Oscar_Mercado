package ejemplo.arboles;

import java.util.ArrayList;
import java.util.List;

public class Arbol {

    private Nodo raiz;

    public Arbol() {
        this.raiz = null;
    }

    public void insertar(int valor) {
        raiz = insertarRecursivo(raiz, valor);
    }

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

    public boolean consultar(int valor) {
        return consultarRecursivo(raiz, valor);
    }

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

    public boolean modificar(int valorViejo, int valorNuevo) {
        if (!consultar(valorViejo)) {
            return false;
        }
        eliminar(valorViejo);
        insertar(valorNuevo);
        return true;
    }

    public void eliminar(int valor) {
        raiz = eliminarRecursivo(raiz, valor);
    }

    private Nodo eliminarRecursivo(Nodo actual, int valor) {
        if (actual == null) {
            return null;
        }
        if (valor == actual.getValor()) {
            if (actual.getIzquierdo() == null && actual.getDerecho() == null) {
                return null;
            }
            if (actual.getIzquierdo() == null) {
                return actual.getDerecho();
            }
            if (actual.getDerecho() == null) {
                return actual.getIzquierdo();
            }
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

    private int encontrarMenor(Nodo raiz) {
        return raiz.getIzquierdo() == null
                ? raiz.getValor()
                : encontrarMenor(raiz.getIzquierdo());
    }

    public List<Integer> inorden() {
        List<Integer> resultado = new ArrayList<>();
        inordenRecursivo(raiz, resultado);
        return resultado;
    }

    private void inordenRecursivo(Nodo actual, List<Integer> resultado) {
        if (actual != null) {
            inordenRecursivo(actual.getIzquierdo(), resultado);
            resultado.add(actual.getValor());
            inordenRecursivo(actual.getDerecho(), resultado);
        }
    }

    public List<Integer> preorden() {
        List<Integer> resultado = new ArrayList<>();
        preordenRecursivo(raiz, resultado);
        return resultado;
    }

    private void preordenRecursivo(Nodo actual, List<Integer> resultado) {
        if (actual != null) {
            resultado.add(actual.getValor());
            preordenRecursivo(actual.getIzquierdo(), resultado);
            preordenRecursivo(actual.getDerecho(), resultado);
        }
    }

    public List<Integer> postorden() {
        List<Integer> resultado = new ArrayList<>();
        postordenRecursivo(raiz, resultado);
        return resultado;
    }

    private void postordenRecursivo(Nodo actual, List<Integer> resultado) {
        if (actual != null) {
            postordenRecursivo(actual.getIzquierdo(), resultado);
            postordenRecursivo(actual.getDerecho(), resultado);
            resultado.add(actual.getValor());
        }
    }
}
