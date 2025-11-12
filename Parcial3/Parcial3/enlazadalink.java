import java.util.LinkedList;
import java.util.Scanner;

public class enlazadalink {
    private static LinkedList<Integer> lista = new LinkedList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n=== MENÚ LISTA ENLAZADA ===");
            System.out.println("1. Insertar elemento");
            System.out.println("2. Insertar elemento en posición específica");
            System.out.println("3. Insertar elemento al inicio");
            System.out.println("4. Eliminar elemento");
            System.out.println("5. Modificar elemento");
            System.out.println("6. Mostrar lista");
            System.out.println("7. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    insertarElemento();
                    break;
                case 2:
                    insertarElementoEnPosicion();
                    break;
                case 3:
                    insertarElementoAlInicio();
                    break;
                case 4:
                    eliminarElemento();
                    break;
                case 5:
                    modificarElemento();
                    break;
                case 6:
                    mostrarLista();
                    break;
                case 7:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta nuevamente.");
            }
        } while (opcion != 7);
        sc.close();
    }

    private static void insertarElemento() {
        System.out.print("Ingresa un número para agregar: ");
        int valor = sc.nextInt();
        lista.add(valor);
        System.out.println("Elemento agregado correctamente.");
    }

    private static void insertarElementoEnPosicion() {
        System.out.print("Ingresa el número a agregar: ");
        int valor = sc.nextInt();
        System.out.print("Ingresa la posición donde deseas agregar el elemento (0-" + lista.size() + "): ");
        int posicion = sc.nextInt();
        if (posicion >= 0 && posicion <= lista.size()) {
            lista.add(posicion, valor);
            System.out.println("Elemento agregado correctamente en la posición " + posicion + ".");
        } else {
            System.out.println("Posición no válida.");
        }
    }

    private static void insertarElementoAlInicio() {
        System.out.print("Ingresa el número a agregar al inicio: ");
        int valor = sc.nextInt();
        lista.addFirst(valor);
        System.out.println("Elemento agregado correctamente al inicio.");
    }

    private static void eliminarElemento() {
        if (lista.isEmpty()) {
            System.out.println("La lista está vacía.");
            return;
        }
        System.out.println("Contenido de la lista:");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i) + ": " + lista.get(i));
        }
        System.out.print("Ingresa el número de posición del elemento que deseas eliminar (0-" + (lista.size() - 1) + "): ");
        int posicion = sc.nextInt();
        if (posicion >= 0 && posicion < lista.size()) {
            lista.remove(posicion);
            System.out.println("Elemento eliminado correctamente.");
        } else {
            System.out.println("Posición no válida.");
        }
    }

    private static void modificarElemento() {
        if (lista.isEmpty()) {
            System.out.println("La lista está vacía.");
            return;
        }
        System.out.println("Contenido de la lista:");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i) + ": " + lista.get(i));
        }
        System.out.print("Ingresa el número de posición del elemento que deseas modificar (0-" + (lista.size() - 1) + "): ");
        int posicion = sc.nextInt();
        if (posicion >= 0 && posicion < lista.size()) {
            System.out.print("Ingresa el nuevo valor: ");
            int nuevoValor = sc.nextInt();
            lista.set(posicion, nuevoValor);
            System.out.println("Elemento modificado correctamente.");
        } else {
            System.out.println("Posición no válida.");
        }
    }

    private static void mostrarLista() {
        System.out.println("Contenido de la lista:");
        if (lista.isEmpty()) {
            System.out.println("La lista está vacía.");
        } else {
            for (int i = 0; i < lista.size(); i++) {
                System.out.print(lista.get(i) + " -> ");
            }
            System.out.println("null");
        }
    }
}