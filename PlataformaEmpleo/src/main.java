import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import plataforma.grafos.GrafoPlataforma;

/**
 * Punto de entrada principal de la Plataforma de Empleo.
 * Muestra un menu para elegir entre la implementacion
 * basada en Arboles (categorias) o en Grafos (candidatos-ofertas).
 */
public class main {

    private static GrafoPlataforma grafo = new GrafoPlataforma();

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
            System.out.println("9. Mostrar grafo completo");
            System.out.println("10. Volver al menu principal");
            System.out.print("Seleccione una opcion: ");
            sub = sc.nextInt();
            sc.nextLine();
            switch (sub) {
                case 1 -> {
                    System.out.print("Nombre del candidato: ");
                    String nombre = sc.nextLine();
                    System.out.print("Habilidades (separadas por coma): ");
                    List<String> habilidades = Arrays.asList(sc.nextLine().split(",\s*"));
                    boolean ok = grafo.agregarCandidato(nombre, habilidades);
                    System.out.println(ok ? "Candidato agregado." : "Error: el nombre ya existe.");
                }
                case 2 -> {
                    System.out.print("Titulo de la oferta: ");
                    String titulo = sc.nextLine();
                    System.out.print("Categoria: ");
                    String categoria = sc.nextLine();
                    System.out.print("Requisitos (separados por coma): ");
                    List<String> requisitos = Arrays.asList(sc.nextLine().split(",\s*"));
                    boolean ok = grafo.agregarOferta(titulo, categoria, requisitos);
                    System.out.println(ok ? "Oferta agregada." : "Error: el titulo ya existe.");
                }
                case 3 -> {
                    System.out.print("Nombre del candidato: ");
                    String cand = sc.nextLine();
                    System.out.print("Titulo de la oferta: ");
                    String ofer = sc.nextLine();
                    boolean ok = grafo.postular(cand, ofer);
                    System.out.println(ok ? "Postulacion registrada." : "Error: candidato u oferta no existen.");
                }
                case 4 -> {
                    System.out.print("Buscar (C para candidato, O para oferta): ");
                    String tipo = sc.nextLine();
                    System.out.print("Nombre: ");
                    String nom = sc.nextLine();
                    if (tipo.equalsIgnoreCase("C")) {
                        var c = grafo.buscarCandidato(nom);
                        if (c != null) {
                            System.out.println("Candidato: " + c.getNombre()
                                + " | Habilidades: " + c.getHabilidades());
                        } else {
                            System.out.println("Candidato no encontrado.");
                        }
                    } else {
                        var o = grafo.buscarOferta(nom);
                        if (o != null) {
                            System.out.println("Oferta: " + o.getTitulo()
                                + " | Categoria: " + o.getCategoria()
                                + " | Requisitos: " + o.getRequisitos());
                        } else {
                            System.out.println("Oferta no encontrada.");
                        }
                    }
                }
                case 5 -> {
                    System.out.print("Modificar (C para candidato, O para oferta): ");
                    String tipo = sc.nextLine();
                    System.out.print("Nombre actual: ");
                    String viejo = sc.nextLine();
                    System.out.print("Nombre nuevo: ");
                    String nuevo = sc.nextLine();
                    if (tipo.equalsIgnoreCase("C")) {
                        System.out.print("Nuevas habilidades (separadas por coma): ");
                        List<String> habs = Arrays.asList(sc.nextLine().split(",\s*"));
                        boolean ok = grafo.modificarCandidato(viejo, nuevo, habs);
                        System.out.println(ok ? "Candidato modificado." : "Error en la modificacion.");
                    } else {
                        System.out.print("Nueva categoria: ");
                        String cat = sc.nextLine();
                        System.out.print("Nuevos requisitos (separados por coma): ");
                        List<String> reqs = Arrays.asList(sc.nextLine().split(",\s*"));
                        boolean ok = grafo.modificarOferta(viejo, nuevo, cat, reqs);
                        System.out.println(ok ? "Oferta modificada." : "Error en la modificacion.");
                    }
                }
                case 6 -> {
                    System.out.print("Eliminar (C para candidato, O para oferta): ");
                    String tipo = sc.nextLine();
                    System.out.print("Nombre: ");
                    String nom = sc.nextLine();
                    boolean ok;
                    if (tipo.equalsIgnoreCase("C")) {
                        ok = grafo.eliminarCandidato(nom);
                    } else {
                        ok = grafo.eliminarOferta(nom);
                    }
                    System.out.println(ok ? "Eliminado correctamente." : "Error: no existe.");
                }
                case 7 -> {
                    System.out.print("Nombre del candidato: ");
                    String nom = sc.nextLine();
                    List<String> rec = grafo.recomendarBFS(nom);
                    if (rec.isEmpty()) {
                        System.out.println("No hay recomendaciones.");
                    } else {
                        System.out.println("Recomendaciones (BFS):");
                        for (String r : rec) {
                            System.out.println("  " + r);
                        }
                    }
                }
                case 8 -> {
                    System.out.print("Nombre del nodo inicial: ");
                    String nom = sc.nextLine();
                    List<String> rec = grafo.recomendarDFS(nom);
                    if (rec.isEmpty()) {
                        System.out.println("Nodo no encontrado.");
                    } else {
                        System.out.println("Recomendaciones (DFS):");
                        for (String r : rec) {
                            System.out.println("  " + r);
                        }
                    }
                }
                case 9 -> {
                    System.out.println("Candidatos: " + grafo.listarCandidatos());
                    System.out.println("Ofertas: " + grafo.listarOfertas());
                    System.out.println("Conexiones:");
                    grafo.mostrarGrafo();
                }
                case 10 -> { }
                default -> System.out.println("Opcion no valida.");
            }
        } while (sub != 10);
    }
}
