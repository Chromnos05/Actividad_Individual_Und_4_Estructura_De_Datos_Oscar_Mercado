package plataforma.grafos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Grafo que gestiona las conexiones entre candidatos y ofertas laborales.
 * Los vertices son candidatos u ofertas. Las aristas representan
 * postulaciones con un peso basado en la coincidencia de habilidades.
 */
public class GrafoPlataforma {

    private final Map<String, Map<String, Integer>> listaAdyacencia;
    private final Map<String, Candidato> candidatos;
    private final Map<String, Oferta> ofertas;

    /**
     * Constructor: crea un grafo vacio.
     */
    public GrafoPlataforma() {
        this.listaAdyacencia = new HashMap<>();
        this.candidatos = new HashMap<>();
        this.ofertas = new HashMap<>();
    }

    /**
     * Agrega un nuevo candidato al grafo.
     *
     * @param nombre      nombre del candidato
     * @param habilidades lista de habilidades
     * @return true si se agrego correctamente, false si ya existe
     */
    public boolean agregarCandidato(String nombre, List<String> habilidades) {
        if (candidatos.containsKey(nombre) || ofertas.containsKey(nombre)) {
            return false;
        }
        candidatos.put(nombre, new Candidato(nombre, habilidades));
        listaAdyacencia.put(nombre, new HashMap<>());
        return true;
    }

    /**
     * Agrega una nueva oferta laboral al grafo.
     *
     * @param titulo     titulo de la oferta
     * @param categoria  categoria laboral
     * @param requisitos lista de requisitos
     * @return true si se agrego correctamente, false si ya existe
     */
    public boolean agregarOferta(String titulo, String categoria, List<String> requisitos) {
        if (candidatos.containsKey(titulo) || ofertas.containsKey(titulo)) {
            return false;
        }
        ofertas.put(titulo, new Oferta(titulo, categoria, requisitos));
        listaAdyacencia.put(titulo, new HashMap<>());
        return true;
    }

    /**
     * Postula un candidato a una oferta. Calcula automaticamente
     * el puntaje de afinidad basado en la coincidencia de
     * habilidades del candidato con los requisitos de la oferta.
     *
     * @param nombreCandidato nombre del candidato
     * @param tituloOferta    titulo de la oferta
     * @return true si se postulo correctamente, false si alguno no existe
     */
    public boolean postular(String nombreCandidato, String tituloOferta) {
        Candidato c = candidatos.get(nombreCandidato);
        Oferta o = ofertas.get(tituloOferta);
        if (c == null || o == null) {
            return false;
        }
        int afinidad = calcularAfinidad(c.getHabilidades(), o.getRequisitos());
        listaAdyacencia.get(nombreCandidato).put(tituloOferta, afinidad);
        listaAdyacencia.get(tituloOferta).put(nombreCandidato, afinidad);
        return true;
    }

    /**
     * Calcula el porcentaje de coincidencia entre habilidades
     * y requisitos.
     */
    private int calcularAfinidad(List<String> habilidades, List<String> requisitos) {
        if (requisitos.isEmpty()) {
            return 0;
        }
        int coincidencias = 0;
        for (String req : requisitos) {
            for (String hab : habilidades) {
                if (hab.equalsIgnoreCase(req)) {
                    coincidencias++;
                    break;
                }
            }
        }
        return (coincidencias * 100) / requisitos.size();
    }

    /**
     * Busca un candidato por su nombre.
     *
     * @param nombre nombre del candidato
     * @return el candidato, o null si no existe
     */
    public Candidato buscarCandidato(String nombre) {
        return candidatos.get(nombre);
    }

    /**
     * Busca una oferta por su titulo.
     *
     * @param titulo titulo de la oferta
     * @return la oferta, o null si no existe
     */
    public Oferta buscarOferta(String titulo) {
        return ofertas.get(titulo);
    }

    /**
     * Modifica los datos de un candidato.
     *
     * @param nombreViejo nombre actual
     * @param nombreNuevo nombre nuevo
     * @param habilidades nueva lista de habilidades
     * @return true si se modifico correctamente
     */
    public boolean modificarCandidato(String nombreViejo, String nombreNuevo,
                                      List<String> habilidades) {
        Candidato c = candidatos.get(nombreViejo);
        if (c == null || (candidatos.containsKey(nombreNuevo) || ofertas.containsKey(nombreNuevo))) {
            return false;
        }
        Map<String, Integer> aristas = listaAdyacencia.remove(nombreViejo);
        candidatos.remove(nombreViejo);
        candidatos.put(nombreNuevo, new Candidato(nombreNuevo, habilidades));
        listaAdyacencia.put(nombreNuevo, aristas);

        for (Map.Entry<String, Map<String, Integer>> entry : listaAdyacencia.entrySet()) {
            if (entry.getValue().containsKey(nombreViejo)) {
                int peso = entry.getValue().remove(nombreViejo);
                entry.getValue().put(nombreNuevo, peso);
            }
        }
        return true;
    }

    /**
     * Modifica los datos de una oferta.
     *
     * @param tituloViejo titulo actual
     * @param tituloNuevo titulo nuevo
     * @param categoria   nueva categoria
     * @param requisitos  nueva lista de requisitos
     * @return true si se modifico correctamente
     */
    public boolean modificarOferta(String tituloViejo, String tituloNuevo,
                                   String categoria, List<String> requisitos) {
        Oferta o = ofertas.get(tituloViejo);
        if (o == null || (candidatos.containsKey(tituloNuevo) || ofertas.containsKey(tituloNuevo))) {
            return false;
        }
        Map<String, Integer> aristas = listaAdyacencia.remove(tituloViejo);
        ofertas.remove(tituloViejo);
        ofertas.put(tituloNuevo, new Oferta(tituloNuevo, categoria, requisitos));
        listaAdyacencia.put(tituloNuevo, aristas);

        for (Map.Entry<String, Map<String, Integer>> entry : listaAdyacencia.entrySet()) {
            if (entry.getValue().containsKey(tituloViejo)) {
                int peso = entry.getValue().remove(tituloViejo);
                entry.getValue().put(tituloNuevo, peso);
            }
        }
        return true;
    }

    /**
     * Elimina un candidato y todas sus conexiones.
     *
     * @param nombre nombre del candidato a eliminar
     * @return true si se elimino correctamente
     */
    public boolean eliminarCandidato(String nombre) {
        if (!candidatos.containsKey(nombre)) {
            return false;
        }
        candidatos.remove(nombre);
        listaAdyacencia.remove(nombre);
        for (Map<String, Integer> aristas : listaAdyacencia.values()) {
            aristas.remove(nombre);
        }
        return true;
    }

    /**
     * Elimina una oferta y todas sus conexiones.
     *
     * @param titulo titulo de la oferta a eliminar
     * @return true si se elimino correctamente
     */
    public boolean eliminarOferta(String titulo) {
        if (!ofertas.containsKey(titulo)) {
            return false;
        }
        ofertas.remove(titulo);
        listaAdyacencia.remove(titulo);
        for (Map<String, Integer> aristas : listaAdyacencia.values()) {
            aristas.remove(titulo);
        }
        return true;
    }

    /**
     * Recomienda ofertas a un candidato usando BFS.
     * Muestra las ofertas conectadas ordenadas por nivel de cercania.
     *
     * @param inicio nombre del candidato
     * @return lista con resultados del recorrido
     */
    public List<String> recomendarBFS(String inicio) {
        List<String> resultado = new ArrayList<>();
        if (!listaAdyacencia.containsKey(inicio)) {
            return resultado;
        }
        Queue<String> cola = new LinkedList<>();
        List<String> visitados = new ArrayList<>();
        cola.add(inicio);
        visitados.add(inicio);
        while (!cola.isEmpty()) {
            String actual = cola.poll();
            String tipo = candidatos.containsKey(actual) ? "[Candidato]" : "[Oferta]";
            StringBuilder info = new StringBuilder(actual + " " + tipo);
            if (ofertas.containsKey(actual)) {
                info.append(" Categoria: ").append(ofertas.get(actual).getCategoria());
            }
            resultado.add(info.toString());
            for (Map.Entry<String, Integer> vecino : listaAdyacencia.get(actual).entrySet()) {
                if (!visitados.contains(vecino.getKey())) {
                    visitados.add(vecino.getKey());
                    cola.add(vecino.getKey());
                }
            }
        }
        return resultado;
    }

    /**
     * Recomienda conexiones a partir de un nodo usando DFS.
     * Explora en profundidad todas las relaciones.
     *
     * @param inicio nombre del nodo inicial
     * @return lista con resultados del recorrido
     */
    public List<String> recomendarDFS(String inicio) {
        List<String> resultado = new ArrayList<>();
        if (!listaAdyacencia.containsKey(inicio)) {
            return resultado;
        }
        List<String> visitados = new ArrayList<>();
        dfsRecursivo(inicio, visitados, resultado);
        return resultado;
    }

    /**
     * Metodo recursivo auxiliar para DFS.
     */
    private void dfsRecursivo(String actual, List<String> visitados, List<String> resultado) {
        visitados.add(actual);
        String tipo = candidatos.containsKey(actual) ? "[Candidato]" : "[Oferta]";
        StringBuilder info = new StringBuilder(actual + " " + tipo);
        if (ofertas.containsKey(actual)) {
            info.append(" Categoria: ").append(ofertas.get(actual).getCategoria());
        }
        resultado.add(info.toString());
        for (Map.Entry<String, Integer> vecino : listaAdyacencia.get(actual).entrySet()) {
            if (!visitados.contains(vecino.getKey())) {
                dfsRecursivo(vecino.getKey(), visitados, resultado);
            }
        }
    }

    /**
     * Muestra todos los candidatos registrados.
     *
     * @return lista de nombres de candidatos
     */
    public List<String> listarCandidatos() {
        return new ArrayList<>(candidatos.keySet());
    }

    /**
     * Muestra todas las ofertas registradas.
     *
     * @return lista de titulos de ofertas
     */
    public List<String> listarOfertas() {
        return new ArrayList<>(ofertas.keySet());
    }

    /**
     * Muestra la estructura completa del grafo con pesos.
     */
    public void mostrarGrafo() {
        for (Map.Entry<String, Map<String, Integer>> entry : listaAdyacencia.entrySet()) {
            String tipo = candidatos.containsKey(entry.getKey()) ? "(C)" : "(O)";
            System.out.print("  " + entry.getKey() + " " + tipo + " -> ");
            for (Map.Entry<String, Integer> arista : entry.getValue().entrySet()) {
                System.out.print(arista.getKey() + "(" + arista.getValue() + "%) ");
            }
            System.out.println();
        }
    }
}
