package plataforma.arboles;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa una categoria laboral dentro del arbol.
 * Puede contener subcategorias (hijos) y ofertas asociadas.
 */
public class Categoria {

    private String nombre;
    private List<Categoria> hijos;
    private List<String> ofertas;

    /**
     * Constructor: crea una categoria con el nombre dado.
     *
     * @param nombre nombre de la categoria
     */
    public Categoria(String nombre) {
        this.nombre = nombre;
        this.hijos = new ArrayList<>();
        this.ofertas = new ArrayList<>();
    }

    /**
     * Retorna el nombre de la categoria.
     *
     * @return nombre de la categoria
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre de la categoria.
     *
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Retorna la lista de subcategorias.
     *
     * @return lista de hijos
     */
    public List<Categoria> getHijos() {
        return hijos;
    }

    /**
     * Agrega una subcategoria como hijo.
     *
     * @param hijo subcategoria a agregar
     */
    public void agregarHijo(Categoria hijo) {
        this.hijos.add(hijo);
    }

    /**
     * Elimina una subcategoria de la lista de hijos.
     *
     * @param hijo subcategoria a eliminar
     * @return true si se elimino correctamente
     */
    public boolean eliminarHijo(Categoria hijo) {
        return this.hijos.remove(hijo);
    }

    /**
     * Retorna la lista de ofertas asociadas a esta categoria.
     *
     * @return lista de nombres de ofertas
     */
    public List<String> getOfertas() {
        return ofertas;
    }

    /**
     * Agrega una oferta a esta categoria.
     *
     * @param oferta nombre de la oferta a agregar
     */
    public void agregarOferta(String oferta) {
        this.ofertas.add(oferta);
    }

    /**
     * Representacion en texto de la categoria.
     *
     * @return nombre de la categoria
     */
    @Override
    public String toString() {
        return nombre;
    }
}
