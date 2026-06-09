package ejemplo.grafos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Grafo {

    private final Map<String, Map<String, Integer>> listaAdyacencia;

    public Grafo() {
        this.listaAdyacencia = new HashMap<>();
    }

    public boolean agregarVertice(String nombre) {
        if (listaAdyacencia.containsKey(nombre)) {
            return false;
        }
        listaAdyacencia.put(nombre, new HashMap<>());
        return true;
    }

    public boolean agregarArista(String origen, String destino, int peso) {
        if (!listaAdyacencia.containsKey(origen) || !listaAdyacencia.containsKey(destino)) {
            return false;
        }
        listaAdyacencia.get(origen).put(destino, peso);
        listaAdyacencia.get(destino).put(origen, peso);
        return true;
    }

    public boolean consultarVertice(String nombre) {
        return listaAdyacencia.containsKey(nombre);
    }

    public boolean modificarVertice(String nombreViejo, String nombreNuevo) {
        if (!listaAdyacencia.containsKey(nombreViejo) || listaAdyacencia.containsKey(nombreNuevo)) {
            return false;
        }
        Map<String, Integer> aristas = listaAdyacencia.remove(nombreViejo);
        listaAdyacencia.put(nombreNuevo, aristas);
        for (Map.Entry<String, Map<String, Integer>> entry : listaAdyacencia.entrySet()) {
            if (entry.getValue().containsKey(nombreViejo)) {
                int peso = entry.getValue().remove(nombreViejo);
                entry.getValue().put(nombreNuevo, peso);
            }
        }
        return true;
    }

    public boolean eliminarVertice(String nombre) {
        if (!listaAdyacencia.containsKey(nombre)) {
            return false;
        }
        listaAdyacencia.remove(nombre);
        for (Map<String, Integer> aristas : listaAdyacencia.values()) {
            aristas.remove(nombre);
        }
        return true;
    }

    public boolean eliminarArista(String origen, String destino) {
        if (!listaAdyacencia.containsKey(origen) || !listaAdyacencia.containsKey(destino)) {
            return false;
        }
        boolean ok1 = listaAdyacencia.get(origen).remove(destino) != null;
        boolean ok2 = listaAdyacencia.get(destino).remove(origen) != null;
        return ok1 && ok2;
    }

    public List<String> bfs(String inicio) {
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
            resultado.add(actual);
            for (String vecino : listaAdyacencia.get(actual).keySet()) {
                if (!visitados.contains(vecino)) {
                    visitados.add(vecino);
                    cola.add(vecino);
                }
            }
        }
        return resultado;
    }

    public List<String> dfs(String inicio) {
        List<String> resultado = new ArrayList<>();
        if (!listaAdyacencia.containsKey(inicio)) {
            return resultado;
        }
        List<String> visitados = new ArrayList<>();
        dfsRecursivo(inicio, visitados, resultado);
        return resultado;
    }

    private void dfsRecursivo(String actual, List<String> visitados, List<String> resultado) {
        visitados.add(actual);
        resultado.add(actual);
        for (String vecino : listaAdyacencia.get(actual).keySet()) {
            if (!visitados.contains(vecino)) {
                dfsRecursivo(vecino, visitados, resultado);
            }
        }
    }

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
