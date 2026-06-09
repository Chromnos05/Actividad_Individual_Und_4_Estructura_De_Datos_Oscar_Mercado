import java.util.Scanner;
import plataforma.arboles.ArbolCategorias;

/**
 * Punto de entrada principal de la Plataforma de Empleo.
 * Muestra un menu para elegir entre la implementacion
 * basada en Arboles (categorias) o en Grafos (candidatos-ofertas).
 */
public class main {

    private static ArbolCategorias arbol = new ArbolCategorias();

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
                case 1 -> {
                    System.out.print("Nombre de la categoria padre: ");
                    String padre = sc.nextLine();
                    System.out.print("Nombre de la nueva categoria: ");
                    String hijo = sc.nextLine();
                    boolean ok = arbol.agregarCategoria(padre, hijo);
                    System.out.println(ok ? "Categoria agregada." : "Error: padre no existe o hijo duplicado.");
                }
                case 2 -> {
                    System.out.print("Nombre de la categoria a buscar: ");
                    String nombre = sc.nextLine();
                    var cat = arbol.buscarCategoria(nombre);
                    System.out.println(cat != null ? "Categoria encontrada: " + cat.getNombre() : "Categoria no encontrada.");
                }
                case 3 -> {
                    System.out.print("Nombre actual de la categoria: ");
                    String viejo = sc.nextLine();
                    System.out.print("Nombre nuevo: ");
                    String nuevo = sc.nextLine();
                    boolean ok = arbol.renombrarCategoria(viejo, nuevo);
                    System.out.println(ok ? "Categoria renombrada." : "Error: no existe o el nuevo nombre ya esta en uso.");
                }
                case 4 -> {
                    System.out.print("Nombre de la categoria a eliminar: ");
                    String nombre = sc.nextLine();
                    boolean ok = arbol.eliminarCategoria(nombre);
                    System.out.println(ok ? "Categoria eliminada." : "Error: no existe o tiene subcategorias.");
                }
                case 5 -> {
                    System.out.print("Nombre de la categoria: ");
                    String cat = sc.nextLine();
                    System.out.print("Nombre de la oferta: ");
                    String oferta = sc.nextLine();
                    boolean ok = arbol.agregarOferta(cat, oferta);
                    System.out.println(ok ? "Oferta asociada a la categoria." : "Error: categoria no encontrada.");
                }
                case 6 -> {
                    System.out.println("Jerarquia de categorias:");
                    System.out.println(arbol.listarPreorden());
                }
                case 7 -> {
                    System.out.println("Categorias ordenadas: " + arbol.listarInorden());
                }
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
