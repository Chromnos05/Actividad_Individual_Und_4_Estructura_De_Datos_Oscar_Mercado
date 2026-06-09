package ejemplo.grafos;

/**
 * Representa un vertice dentro de un grafo.
 * Cada vertice tiene un nombre que lo identifica.
 */
public class Vertice {

    /** Nombre identificador del vertice */
    private String nombre;

    /**
     * Constructor: crea un vertice con el nombre dado.
     *
     * @param nombre nombre del vertice
     */
    public Vertice(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Retorna el nombre del vertice.
     *
     * @return nombre del vertice
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre del vertice.
     *
     * @param nombre nuevo nombre del vertice
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Retorna una representacion en texto del vertice.
     *
     * @return nombre del vertice
     */
    @Override
    public String toString() {
        return nombre;
    }
}
