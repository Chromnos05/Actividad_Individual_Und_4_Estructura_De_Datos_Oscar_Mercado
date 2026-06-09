package ejemplo.grafos;

public class Main {

    public static void main(String[] args) {
        Grafo grafo = new Grafo();

        System.out.println("=== EJEMPLO DE GRAFO ===");
        System.out.println();

        // Crear vertices
        System.out.println("--- Insercion de vertices ---");
        String[] vertices = {"A", "B", "C", "D", "E"};
        for (String v : vertices) {
            grafo.agregarVertice(v);
            System.out.println("Vertice agregado: " + v);
        }
        System.out.println();

        // Crear aristas
        System.out.println("--- Insercion de aristas ---");
        grafo.agregarArista("A", "B", 5);
        grafo.agregarArista("A", "C", 3);
        grafo.agregarArista("B", "D", 2);
        grafo.agregarArista("C", "D", 7);
        grafo.agregarArista("D", "E", 4);
        System.out.println("Aristas agregadas: A-B(5), A-C(3), B-D(2), C-D(7), D-E(4)");
        System.out.println();

        // Mostrar grafo
        System.out.println("--- Estado del grafo ---");
        grafo.mostrarGrafo();
        System.out.println();

        // Recorridos
        System.out.println("--- Recorridos desde A ---");
        System.out.println("BFS: " + grafo.bfs("A"));
        System.out.println("DFS: " + grafo.dfs("A"));
        System.out.println();

        // Consulta
        System.out.println("--- Consulta ---");
        System.out.println("Existe vertice A? " + grafo.consultarVertice("A"));
        System.out.println("Existe vertice F? " + grafo.consultarVertice("F"));
        System.out.println();

        // Modificacion
        System.out.println("--- Modificacion ---");
        System.out.println("Renombrar A por X: " + grafo.modificarVertice("A", "X"));
        System.out.println("Grafo despues de renombrar:");
        grafo.mostrarGrafo();
        System.out.println("Recorrido BFS desde X: " + grafo.bfs("X"));
        System.out.println();

        // Eliminacion de arista
        System.out.println("--- Eliminacion de arista ---");
        System.out.println("Eliminar arista X-C: " + grafo.eliminarArista("X", "C"));
        grafo.mostrarGrafo();
        System.out.println();

        // Eliminacion de vertice
        System.out.println("--- Eliminacion de vertice ---");
        System.out.println("Eliminar vertice E: " + grafo.eliminarVertice("E"));
        grafo.mostrarGrafo();
        System.out.println();

        System.out.println("=== FIN DEL EJEMPLO ===");
    }
}
