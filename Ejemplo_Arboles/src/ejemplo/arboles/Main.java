package ejemplo.arboles;

/**
 * Clase principal que demuestra el funcionamiento del
 * Arbol Binario de Busqueda con todas sus operaciones:
 * insercion, consulta, modificacion, eliminacion y recorridos.
 */
public class Main {

    /**
     * Punto de entrada del programa.
     * Crea un arbol, ejecuta cada operacion y muestra
     * los resultados por consola.
     *
     * @param args argumentos de linea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        // Crear un arbol vacio
        Arbol arbol = new Arbol();

        System.out.println("=== EJEMPLO DE ARBOL BINARIO DE BUSQUEDA ===");
        System.out.println();

        // Insercion de valores
        System.out.println("--- Insercion ---");
        int[] valores = {50, 30, 70, 20, 40, 60, 80};
        for (int v : valores) {
            arbol.insertar(v);
            System.out.println("Insertado: " + v);
        }
        System.out.println();

        // Recorridos del arbol
        System.out.println("--- Recorridos ---");
        System.out.println("Inorden:   " + arbol.inorden());
        System.out.println("Preorden:  " + arbol.preorden());
        System.out.println("Postorden: " + arbol.postorden());
        System.out.println();

        // Consulta de existencia
        System.out.println("--- Consulta ---");
        System.out.println("Existe 40? " + arbol.consultar(40));
        System.out.println("Existe 90? " + arbol.consultar(90));
        System.out.println();

        // Modificacion de un valor
        System.out.println("--- Modificacion ---");
        System.out.println("Cambiar 20 por 25: " + arbol.modificar(20, 25));
        System.out.println("Inorden despues de modificar: " + arbol.inorden());
        System.out.println();

        // Eliminacion de un valor
        System.out.println("--- Eliminacion ---");
        arbol.eliminar(70);
        System.out.println("Eliminado: 70");
        System.out.println("Inorden despues de eliminar: " + arbol.inorden());
        System.out.println("Preorden despues de eliminar: " + arbol.preorden());
        System.out.println();

        System.out.println("=== FIN DEL EJEMPLO ===");
    }
}
