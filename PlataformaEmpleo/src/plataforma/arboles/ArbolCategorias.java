package plataforma.arboles;

import java.util.ArrayList;
import java.util.List;

/**
 * Arbol general de categorias laborales.
 * Cada nodo puede tener multiples hijos (arbol n-ario).
 * Permite operaciones CRUD sobre categorias y asociar ofertas.
 */
public class ArbolCategorias {

    private Categoria raiz;

    /**
     * Constructor: crea el arbol con una categoria raiz llamada "Categorias".
     */
    public ArbolCategorias() {
        this.raiz = new Categoria("Categorias");
    }

    /**
     * Agrega una nueva subcategoria bajo un padre existente.
     *
     * @param nombrePadre nombre de la categoria padre
     * @param nombreHijo  nombre de la nueva subcategoria
     * @return true si se agrego correctamente, false si el padre
     *         no existe o el hijo ya existe en el mismo nivel
     */
    public boolean agregarCategoria(String nombrePadre, String nombreHijo) {
        Categoria padre = buscarCategoria(nombrePadre);
        if (padre == null) {
            return false;
        }
        for (Categoria h : padre.getHijos()) {
            if (h.getNombre().equalsIgnoreCase(nombreHijo)) {
                return false;
            }
        }
        padre.agregarHijo(new Categoria(nombreHijo));
        return true;
    }

    /**
     * Busca una categoria por su nombre en todo el arbol.
     *
     * @param nombre nombre de la categoria a buscar
     * @return la categoria encontrada, o null si no existe
     */
    public Categoria buscarCategoria(String nombre) {
        return buscarRecursivo(raiz, nombre);
    }

    /**
     * Metodo recursivo auxiliar para buscar una categoria.
     */
    private Categoria buscarRecursivo(Categoria actual, String nombre) {
        if (actual.getNombre().equalsIgnoreCase(nombre)) {
            return actual;
        }
        for (Categoria hijo : actual.getHijos()) {
            Categoria encontrada = buscarRecursivo(hijo, nombre);
            if (encontrada != null) {
                return encontrada;
            }
        }
        return null;
    }

    /**
     * Renombra una categoria existente.
     *
     * @param nombreViejo nombre actual de la categoria
     * @param nombreNuevo nombre nuevo de la categoria
     * @return true si se renombro correctamente,
     *         false si la categoria no existe o el nuevo nombre ya esta en uso
     */
    public boolean renombrarCategoria(String nombreViejo, String nombreNuevo) {
        Categoria cat = buscarCategoria(nombreViejo);
        if (cat == null || buscarCategoria(nombreNuevo) != null) {
            return false;
        }
        cat.setNombre(nombreNuevo);
        return true;
    }

    /**
     * Elimina una categoria hoja (sin hijos) del arbol.
     *
     * @param nombre nombre de la categoria a eliminar
     * @return true si se elimino correctamente,
     *         false si no existe o tiene hijos
     */
    public boolean eliminarCategoria(String nombre) {
        if (raiz.getNombre().equalsIgnoreCase(nombre)) {
            return false;
        }
        return eliminarRecursivo(raiz, nombre);
    }

    /**
     * Metodo recursivo auxiliar para eliminar una categoria.
     */
    private boolean eliminarRecursivo(Categoria actual, String nombre) {
        for (Categoria hijo : actual.getHijos()) {
            if (hijo.getNombre().equalsIgnoreCase(nombre)) {
                if (!hijo.getHijos().isEmpty()) {
                    return false;
                }
                return actual.eliminarHijo(hijo);
            }
            if (eliminarRecursivo(hijo, nombre)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Asocia una oferta laboral a una categoria.
     *
     * @param nombreCategoria nombre de la categoria
     * @param nombreOferta    nombre de la oferta a asociar
     * @return true si se asocio correctamente, false si la categoria no existe
     */
    public boolean agregarOferta(String nombreCategoria, String nombreOferta) {
        Categoria cat = buscarCategoria(nombreCategoria);
        if (cat == null) {
            return false;
        }
        cat.agregarOferta(nombreOferta);
        return true;
    }

    /**
     * Recorrido preorden: muestra la jerarquia completa con sangria.
     *
     * @return cadena con la jerarquia formateada
     */
    public String listarPreorden() {
        StringBuilder sb = new StringBuilder();
        preordenRecursivo(raiz, 0, sb);
        return sb.toString();
    }

    /**
     * Metodo recursivo auxiliar para el recorrido preorden.
     * Agrega sangria segun la profundidad.
     */
    private void preordenRecursivo(Categoria actual, int nivel, StringBuilder sb) {
        for (int i = 0; i < nivel; i++) {
            sb.append("  ");
        }
        sb.append("- ").append(actual.getNombre());
        if (!actual.getOfertas().isEmpty()) {
            sb.append(" [Ofertas: ").append(String.join(", ", actual.getOfertas())).append("]");
        }
        sb.append("\n");
        for (Categoria hijo : actual.getHijos()) {
            preordenRecursivo(hijo, nivel + 1, sb);
        }
    }

    /**
     * Recorrido inorden: devuelve los nombres de todas las categorias
     * ordenados alfabeticamente (sin estructura jerarquica).
     *
     * @return lista de nombres ordenados
     */
    public List<String> listarInorden() {
        List<String> resultado = new ArrayList<>();
        inordenRecursivo(raiz, resultado);
        java.util.Collections.sort(resultado);
        return resultado;
    }

    /**
     * Metodo recursivo auxiliar para el recorrido inorden.
     * Recolecta todos los nombres del arbol.
     */
    private void inordenRecursivo(Categoria actual, List<String> resultado) {
        resultado.add(actual.getNombre());
        for (Categoria hijo : actual.getHijos()) {
            inordenRecursivo(hijo, resultado);
        }
    }
}
