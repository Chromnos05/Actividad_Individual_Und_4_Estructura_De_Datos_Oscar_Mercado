import java.util.Scanner;

/**
 * Punto de entrada principal de la Plataforma de Empleo.
 * Muestra un menu para elegir entre la implementacion
 * basada en Arboles (categorias) o en Grafos (candidatos-ofertas).
 */
public class main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println();
            System.out.println("=== PLATAFORMA DE EMPLEO ===");
            System.out.println("1. Arbol de Categorias");
            System.out.println("2. Grafo Candidatos-Ofertas");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1 -> menuArbol(sc);
                case 2 -> menuGrafo(sc);
                case 3 -> System.out.println("Saliendo...");
                default -> System.out.println("Opcion no valida.");
            }
        } while (opcion != 3);
        sc.close();
    }

    /**
     * Menu para operaciones del Arbol de Categorias.
     * Implementado en rama-arbol-plataforma.
     */
    private static void menuArbol(Scanner sc) {
        int sub;
        do {
            System.out.println();
            System.out.println("--- ARBOL DE CATEGORIAS ---");
            System.out.println("1. Agregar categoria");
            System.out.println("2. Buscar categoria");
            System.out.println("3. Renombrar categoria");
            System.out.println("4. Eliminar categoria");
            System.out.println("5. Asociar oferta a categoria");
            System.out.println("6. Mostrar jerarquia (preorden)");
            System.out.println("7. Listar categorias (inorden)");
            System.out.println("8. Volver al menu principal");
            System.out.print("Seleccione una opcion: ");
            sub = sc.nextInt();
            sc.nextLine();
            switch (sub) {
                case 1 -> System.out.println("[Pendiente] Agregar categoria");
                case 2 -> System.out.println("[Pendiente] Buscar categoria");
                case 3 -> System.out.println("[Pendiente] Renombrar categoria");
                case 4 -> System.out.println("[Pendiente] Eliminar categoria");
                case 5 -> System.out.println("[Pendiente] Asociar oferta");
                case 6 -> System.out.println("[Pendiente] Mostrar jerarquia");
                case 7 -> System.out.println("[Pendiente] Listar categorias");
                case 8 -> { }
                default -> System.out.println("Opcion no valida.");
            }
        } while (sub != 8);
    }

    /**
     * Menu para operaciones del Grafo Candidatos-Ofertas.
     * Implementado en rama-grafo-plataforma.
     */
    private static void menuGrafo(Scanner sc) {
        int sub;
        do {
            System.out.println();
            System.out.println("--- GRAFO CANDIDATOS-OFERTAS ---");
            System.out.println("1. Agregar candidato");
            System.out.println("2. Agregar oferta");
            System.out.println("3. Postular candidato a oferta");
            System.out.println("4. Buscar candidato / oferta");
            System.out.println("5. Modificar candidato / oferta");
            System.out.println("6. Eliminar candidato / oferta");
            System.out.println("7. Recomendar por BFS");
            System.out.println("8. Recomendar por DFS");
            System.out.println("9. Volver al menu principal");
            System.out.print("Seleccione una opcion: ");
            sub = sc.nextInt();
            sc.nextLine();
            switch (sub) {
                case 1 -> System.out.println("[Pendiente] Agregar candidato");
                case 2 -> System.out.println("[Pendiente] Agregar oferta");
                case 3 -> System.out.println("[Pendiente] Postular candidato");
                case 4 -> System.out.println("[Pendiente] Buscar");
                case 5 -> System.out.println("[Pendiente] Modificar");
                case 6 -> System.out.println("[Pendiente] Eliminar");
                case 7 -> System.out.println("[Pendiente] Recomendar BFS");
                case 8 -> System.out.println("[Pendiente] Recomendar DFS");
                case 9 -> { }
                default -> System.out.println("Opcion no valida.");
            }
        } while (sub != 9);
    }
}
