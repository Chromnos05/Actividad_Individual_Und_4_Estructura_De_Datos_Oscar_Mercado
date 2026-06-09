package ejemplo.grafos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Implementacion de un Grafo no dirigido con lista de adyacencia.
 * Cada vertice se asocia con un mapa de vecinos y pesos de aristas.
 * Permite operaciones CRUD y recorridos BFS y DFS.
 */
public class Grafo {

    /**
     * Estructura principal: mapa de vertices donde cada clave es
     * el nombre del vertice y su valor es un mapa de vecino -> peso.
     */
    private final Map<String, Map<String, Integer>> listaAdyacencia;

    /**
     * Constructor: crea un grafo vacio.
     */
    public Grafo() {
        this.listaAdyacencia = new HashMap<>();
    }

    /**
     * Agrega un nuevo vertice al grafo.
     *
     * @param nombre nombre del vertice a agregar
     * @return true si se agrego correctamente,
     *         false si el vertice ya existia
     */
    public boolean agregarVertice(String nombre) {
        if (listaAdyacencia.containsKey(nombre)) {
            return false;
        }
        listaAdyacencia.put(nombre, new HashMap<>());
        return true;
    }

    /**
     * Agrega una arista no dirigida entre dos vertices existentes.
     * La arista se registra en ambos sentidos con el mismo peso.
     *
     * @param origen  vertice de origen
     * @param destino vertice de destino
     * @param peso    peso de la arista
     * @return true si se agrego correctamente,
     *         false si algun vertice no existe
     */
    public boolean agregarArista(String origen, String destino, int peso) {
        if (!listaAdyacencia.containsKey(origen) || !listaAdyacencia.containsKey(destino)) {
            return false;
        }
        listaAdyacencia.get(origen).put(destino, peso);
        listaAdyacencia.get(destino).put(origen, peso);
        return true;
    }

    /**
     * Consulta si un vertice existe en el grafo.
     *
     * @param nombre nombre del vertice a consultar
     * @return true si existe, false en caso contrario
     */
    public boolean consultarVertice(String nombre) {
        return listaAdyacencia.containsKey(nombre);
    }

    /**
     * Modifica (renombra) un vertice del grafo.
     * Actualiza todas las referencias en las aristas
     * de los demas vertices.
     *
     * @param nombreViejo nombre actual del vertice
     * @param nombreNuevo nombre nuevo del vertice
     * @return true si se renombro correctamente,
     *         false si el viejo no existe o el nuevo ya existe
     */
    public boolean modificarVertice(String nombreViejo, String nombreNuevo) {
        if (!listaAdyacencia.containsKey(nombreViejo) || listaAdyacencia.containsKey(nombreNuevo)) {
            return false;
        }
        // Extraer las aristas del vertice viejo
        Map<String, Integer> aristas = listaAdyacencia.remove(nombreViejo);
        // Insertar el vertice con el nuevo nombre
        listaAdyacencia.put(nombreNuevo, aristas);
        // Actualizar referencias en los demas vertices
        for (Map.Entry<String, Map<String, Integer>> entry : listaAdyacencia.entrySet()) {
            if (entry.getValue().containsKey(nombreViejo)) {
                int peso = entry.getValue().remove(nombreViejo);
                entry.getValue().put(nombreNuevo, peso);
            }
        }
        return true;
    }

    /**
     * Elimina un vertice y todas sus aristas del grafo.
     *
     * @param nombre nombre del vertice a eliminar
     * @return true si se elimino correctamente,
     *         false si el vertice no existe
     */
    public boolean eliminarVertice(String nombre) {
        if (!listaAdyacencia.containsKey(nombre)) {
            return false;
        }
        // Eliminar el vertice y sus aristas
        listaAdyacencia.remove(nombre);
        // Eliminar referencias en los demas vertices
        for (Map<String, Integer> aristas : listaAdyacencia.values()) {
            aristas.remove(nombre);
        }
        return true;
    }

    /**
     * Elimina una arista especifica entre dos vertices.
     *
     * @param origen  vertice origen de la arista
     * @param destino vertice destino de la arista
     * @return true si se elimino correctamente,
     *         false si algun vertice no existe
     */
    public boolean eliminarArista(String origen, String destino) {
        if (!listaAdyacencia.containsKey(origen) || !listaAdyacencia.containsKey(destino)) {
            return false;
        }
        boolean ok1 = listaAdyacencia.get(origen).remove(destino) != null;
        boolean ok2 = listaAdyacencia.get(destino).remove(origen) != null;
        return ok1 && ok2;
    }

    /**
     * Recorrido BFS (Breadth-First Search) desde un vertice inicial.
     * Visita los vertices por niveles, usando una cola.
     *
     * @param inicio vertice desde el cual comenzar el recorrido
     * @return lista con los vertices en orden de visita
     */
    public List<String> bfs(String inicio) {
        List<String> resultado = new ArrayList<>();
        if (!listaAdyacencia.containsKey(inicio)) {
            return resultado;
        }
        // Cola para el recorrido por niveles
        Queue<String> cola = new LinkedList<>();
        List<String> visitados = new ArrayList<>();
        cola.add(inicio);
        visitados.add(inicio);
        while (!cola.isEmpty()) {
            String actual = cola.poll();
            resultado.add(actual);
            // Visitar todos los vecinos no visitados
            for (String vecino : listaAdyacencia.get(actual).keySet()) {
                if (!visitados.contains(vecino)) {
                    visitados.add(vecino);
                    cola.add(vecino);
                }
            }
        }
        return resultado;
    }

    /**
     * Recorrido DFS (Depth-First Search) desde un vertice inicial.
     * Visita los vertices en profundidad, usando recursion.
     *
     * @param inicio vertice desde el cual comenzar el recorrido
     * @return lista con los vertices en orden de visita
     */
    public List<String> dfs(String inicio) {
        List<String> resultado = new ArrayList<>();
        if (!listaAdyacencia.containsKey(inicio)) {
            return resultado;
        }
        List<String> visitados = new ArrayList<>();
        dfsRecursivo(inicio, visitados, resultado);
        return resultado;
    }

    /**
     * Metodo recursivo auxiliar para el recorrido DFS.
     * Marca el vertice como visitado, lo agrega al resultado
     * y continua con sus vecinos no visitados.
     *
     * @param actual   vertice actual en la recursion
     * @param visitados lista de vertices ya visitados
     * @param resultado lista acumulada del recorrido
     */
    private void dfsRecursivo(String actual, List<String> visitados, List<String> resultado) {
        visitados.add(actual);
        resultado.add(actual);
        for (String vecino : listaAdyacencia.get(actual).keySet()) {
            if (!visitados.contains(vecino)) {
                dfsRecursivo(vecino, visitados, resultado);
            }
        }
    }

    /**
     * Muestra la estructura del grafo en consola.
     * Imprime la lista de adyacencia de cada vertice
     * con sus vecinos y pesos correspondientes.
     */
    public void mostrarGrafo() {
        System.out.println("Grafo (lista de adyacencia):");
        for (Map.Entry<String, Map<String, Integer>> entry : listaAdyacencia.entrySet()) {
            System.out.print("  " + entry.getKey() + " -> ");
            for (Map.Entry<String, Integer> arista : entry.getValue().entrySet()) {
                System.out.print(arista.getKey() + "(" + arista.getValue() + ") ");
            }
            System.out.println();
        }
    }
}
