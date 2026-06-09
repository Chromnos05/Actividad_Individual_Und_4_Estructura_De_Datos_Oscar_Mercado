package ejemplo.arboles;

public class Main {

    public static void main(String[] args) {
        Arbol arbol = new Arbol();

        System.out.println("=== EJEMPLO DE ARBOL BINARIO DE BUSQUEDA ===");
        System.out.println();

        // Insertar
        System.out.println("--- Insercion ---");
        int[] valores = {50, 30, 70, 20, 40, 60, 80};
        for (int v : valores) {
            arbol.insertar(v);
            System.out.println("Insertado: " + v);
        }
        System.out.println();

        // Recorridos
        System.out.println("--- Recorridos ---");
        System.out.println("Inorden:   " + arbol.inorden());
        System.out.println("Preorden:  " + arbol.preorden());
        System.out.println("Postorden: " + arbol.postorden());
        System.out.println();

        // Consulta
        System.out.println("--- Consulta ---");
        System.out.println("Existe 40? " + arbol.consultar(40));
        System.out.println("Existe 90? " + arbol.consultar(90));
        System.out.println();

        // Modificacion
        System.out.println("--- Modificacion ---");
        System.out.println("Cambiar 20 por 25: " + arbol.modificar(20, 25));
        System.out.println("Inorden despues de modificar: " + arbol.inorden());
        System.out.println();

        // Eliminacion
        System.out.println("--- Eliminacion ---");
        arbol.eliminar(70);
        System.out.println("Eliminado: 70");
        System.out.println("Inorden despues de eliminar: " + arbol.inorden());
        System.out.println("Preorden despues de eliminar: " + arbol.preorden());
        System.out.println();

        System.out.println("=== FIN DEL EJEMPLO ===");
    }
}
