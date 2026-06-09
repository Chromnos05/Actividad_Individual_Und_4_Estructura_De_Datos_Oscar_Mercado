package plataforma.grafos;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un candidato dentro de la plataforma de empleo.
 * Cada candidato tiene un nombre y una lista de habilidades.
 */
public class Candidato {

    private String nombre;
    private List<String> habilidades;

    /**
     * Constructor: crea un candidato con nombre y habilidades.
     *
     * @param nombre      nombre del candidato
     * @param habilidades lista de habilidades del candidato
     */
    public Candidato(String nombre, List<String> habilidades) {
        this.nombre = nombre;
        this.habilidades = new ArrayList<>(habilidades);
    }

    /**
     * Retorna el nombre del candidato.
     *
     * @return nombre del candidato
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre del candidato.
     *
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Retorna la lista de habilidades del candidato.
     *
     * @return lista de habilidades
     */
    public List<String> getHabilidades() {
        return habilidades;
    }

    /**
     * Modifica la lista de habilidades del candidato.
     *
     * @param habilidades nueva lista de habilidades
     */
    public void setHabilidades(List<String> habilidades) {
        this.habilidades = new ArrayList<>(habilidades);
    }

    /**
     * Representacion en texto del candidato.
     *
     * @return nombre del candidato
     */
    @Override
    public String toString() {
        return nombre;
    }
}
