package plataforma.grafos;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa una oferta laboral dentro de la plataforma de empleo.
 * Cada oferta tiene un titulo, una categoria y una lista de requisitos.
 */
public class Oferta {

    private String titulo;
    private String categoria;
    private List<String> requisitos;

    /**
     * Constructor: crea una oferta con titulo, categoria y requisitos.
     *
     * @param titulo     titulo de la oferta
     * @param categoria  categoria laboral de la oferta
     * @param requisitos lista de requisitos de la oferta
     */
    public Oferta(String titulo, String categoria, List<String> requisitos) {
        this.titulo = titulo;
        this.categoria = categoria;
        this.requisitos = new ArrayList<>(requisitos);
    }

    /**
     * Retorna el titulo de la oferta.
     *
     * @return titulo de la oferta
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Modifica el titulo de la oferta.
     *
     * @param titulo nuevo titulo
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Retorna la categoria de la oferta.
     *
     * @return categoria de la oferta
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Modifica la categoria de la oferta.
     *
     * @param categoria nueva categoria
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Retorna la lista de requisitos de la oferta.
     *
     * @return lista de requisitos
     */
    public List<String> getRequisitos() {
        return requisitos;
    }

    /**
     * Modifica la lista de requisitos de la oferta.
     *
     * @param requisitos nueva lista de requisitos
     */
    public void setRequisitos(List<String> requisitos) {
        this.requisitos = new ArrayList<>(requisitos);
    }

    /**
     * Representacion en texto de la oferta.
     *
     * @return titulo de la oferta
     */
    @Override
    public String toString() {
        return titulo;
    }
}
